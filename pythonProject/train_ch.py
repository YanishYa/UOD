import pandas as pd
from sklearn import preprocessing, __all__
from sklearn.feature_extraction import DictVectorizer
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from sklearn.model_selection import train_test_split, learning_curve
from sklearn import linear_model
import dill as pickle

import warnings

warnings.filterwarnings("ignore")
