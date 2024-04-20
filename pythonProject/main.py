import json
from flask import Flask
import numpy as np
import pandas as pd
from sklearn import preprocessing, __all__
from sklearn.feature_extraction import DictVectorizer
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from sklearn.model_selection import train_test_split, learning_curve
from sklearn import linear_model
import dill as pickle

import warnings

warnings.filterwarnings("ignore")


def Build_and_Train():
    data = pd.read_json('time-interval_target.json')
    data.set_index('timestamp', inplace=True)
    data.sort_index(inplace=True)

    featureConCols = ['ch0', 'ch1', 'ch2', 'ch3', 'ch4', 'ch5', 'ch6', 'temp']
    # Split the database into training and testing set
    df = data.loc[:, data.columns != "timestamp"]
    df_target = df['target']
    X_train, X_test, y_train, y_test = train_test_split(df, df_target, test_size=0.2)

    # Data preprocessing to transfer all variables into array
    scaler = preprocessing.StandardScaler()
    vec = DictVectorizer(sparse=False)

    def Data_preprocessing(X, y):
        # 1. Numerical vars into vector
        X_dataFeatureCon = X[featureConCols]
        X_dictCon = X_dataFeatureCon.T.to_dict().values()

        # 2. Keep a dataframe of concat database before scaling
        X_all = X_dataFeatureCon

        # 3. Vectorizing and scaling the nummerical variabled into range [-1,1]
        X_vec_con = vec.fit_transform(X_dictCon)
        X_vec_con = scaler.fit_transform(X_vec_con)
        X_vec = X_vec_con

        # 5. Conver target into array
        y_vec = y.to_numpy()

        return X_vec, y_vec, X_all

    # Apply the Data_preprocessing function on training set and testing set
    X_train_vec, y_train_vec, X_train_frame = Data_preprocessing(X_train, y_train)
    X_test_vec, y_test_vec, X_test_frame = Data_preprocessing(X_test, y_test)

    # 1. Create counts
    count_class_0, count_class_1 = df_target.value_counts()

    df_class_0 = data[data['target'] == 0]
    df_class_1 = data[data['target'] == 1]

    df_class_0_under = df_class_0.sample(count_class_1)
    df_test_under = pd.concat([df_class_0_under, df_class_1], axis=0)

    # Model 2: Simple logistic regression with l2 regularization on under sampling data
    # 1. Get under sampling training set and testing set
    df_u = df_test_under.loc[:, df_test_under.columns != "timestamp"]
    df_u_target = df_u['target']
    X_train_under, X_test_under, y_train_under, y_test_under = train_test_split(df_u, df_u_target, test_size=0.2)

    X_train_under_vec, y_train_under_vec, X_train_under_frame = Data_preprocessing(X_train_under, y_train_under)
    X_test_under_vec, y_test_under_vec, X_test_under_frame = Data_preprocessing(X_test_under, y_test_under)

    # 2. Model parameters
    model2 = linear_model.LogisticRegression(C=1.0, penalty='l2', tol=1e-6)
    model2.fit(X_train_under_vec, y_train_under_vec)

    y_pred = model2.predict(X_test_under_vec)
    accuracy = accuracy_score(y_test_under_vec, y_pred)
    print("Accuracy: %.2f%%" % (accuracy * 100.0))

    report = classification_report(y_test_under_vec, y_pred)
    print(report)

    return model2

if __name__ == '__main__':
	model = Build_and_Train()

	filename = 'models/model_v1.pk'
	with open('../pythonProject/'+filename, 'wb') as file:
		pickle.dump(model, file)