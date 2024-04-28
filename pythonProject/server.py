import json
import pandas as pd
import dill as pickle
from flask import Flask, jsonify, request

app = Flask(__name__)



@app.route('/predict',  methods=['POST'])
def apicall():
    """API Call

    Pandas dataframe (sent as a payload) from API Call
    """
    try:
        test_str = request.get_data(as_text=True)
        # test_json = request.get_json()
        test_s = json.loads(test_str)
        test = pd.DataFrame(test_s)
        # test = pd.read_json(test_json, orient='records')
        # test = pd.read_json('time-interval.json')
        test_X = test.loc[:, test.columns != "timestamp"]

    except Exception as e:
        raise e

    clf = 'model_v1.pk'

    if test.empty:
        return (bad_request())
    else:
        # Load the saved model
        print("Loading the model...")
        loaded_model = None
        with open('./models/' + clf, 'rb') as f:
            loaded_model = pickle.load(f)

        print("The model has been loaded...doing predictions now...")
        predictions = loaded_model.predict(test_X)

        final_predictions = test.assign(target=predictions)

        responses = jsonify(final_predictions.to_json(orient="records", date_format='iso'))
        responses.status_code = 200

        return (responses)

@app.route('/predictch', methods=['POST'])
def apicall_ch():
    """API Call

    Pandas dataframe (sent as a payload) from API Call
    """
    try:
        test_str = request.get_data(as_text=True)
        # test_json = request.get_json()
        test_s = json.loads(test_str)
        test = pd.DataFrame(test_s)
        # test = pd.read_json(test_json, orient='records')
        # test = pd.read_json('time-interval.json')

        test_X_ch0 = test.loc[:, test.columns == "ch0"]
        test_X_ch1 = test.loc[:, test.columns == "ch1"]
        test_X_ch2 = test.loc[:, test.columns == "ch2"]
        test_X_ch3 = test.loc[:, test.columns == "ch3"]
        test_X_ch4 = test.loc[:, test.columns == "ch4"]
        test_X_ch5 = test.loc[:, test.columns == "ch5"]
        test_X_ch6 = test.loc[:, test.columns == "ch6"]
        test_X_temp = test.loc[:, test.columns == "temp"]

    except Exception as e:
        raise e

    ch0 = 'model_ch0.pk'
    ch1 = 'model_ch1.pk'
    ch2 = 'model_ch2.pk'
    ch3 = 'model_ch3.pk'
    ch4 = 'model_ch4.pk'
    ch5 = 'model_ch5.pk'
    ch6 = 'model_ch6.pk'
    temp = 'model_temp.pk'

    if test.empty:
        return (bad_request())
    else:
        # Load the saved model
        print("Loading the model...")
        loaded_model_ch0 = None
        loaded_model_ch1 = None
        loaded_model_ch2 = None
        loaded_model_ch3 = None
        loaded_model_ch4 = None
        loaded_model_ch5 = None
        loaded_model_ch6 = None
        loaded_model_temp = None

        with open('./models/' + ch0, 'rb') as f_ch0:
            loaded_model_ch0 = pickle.load(f_ch0)

        with open('./models/' + ch1, 'rb') as f_ch1:
            loaded_model_ch1 = pickle.load(f_ch1)

        with open('./models/' + ch2, 'rb') as f_ch2:
            loaded_model_ch2 = pickle.load(f_ch2)

        with open('./models/' + ch3, 'rb') as f_ch3:
            loaded_model_ch3 = pickle.load(f_ch3)

        with open('./models/' + ch4, 'rb') as f_ch4:
            loaded_model_ch4 = pickle.load(f_ch4)

        with open('./models/' + ch5, 'rb') as f_ch5:
            loaded_model_ch5 = pickle.load(f_ch5)

        with open('./models/' + ch6, 'rb') as f_ch4:
            loaded_model_ch6 = pickle.load(f_ch4)

        with open('./models/' + temp, 'rb') as f_temp:
            loaded_model_temp = pickle.load(f_temp)

        print("The model has been loaded...doing predictions now...")
        predictions_ch0 = loaded_model_ch0.predict(test_X_ch0)
        predictions_ch1 = loaded_model_ch1.predict(test_X_ch1)
        predictions_ch2 = loaded_model_ch2.predict(test_X_ch2)
        predictions_ch3 = loaded_model_ch3.predict(test_X_ch3)
        predictions_ch4 = loaded_model_ch4.predict(test_X_ch4)
        predictions_ch5 = loaded_model_ch5.predict(test_X_ch5)
        predictions_ch6 = loaded_model_ch6.predict(test_X_ch6)
        predictions_temp = loaded_model_temp.predict(test_X_temp)

        final_predictions = test.assign(target_ch0=predictions_ch0, target_ch1=predictions_ch1, target_ch2=predictions_ch2, target_ch3=predictions_ch3, target_ch4=predictions_ch4, target_ch5=predictions_ch5, target_ch6=predictions_ch6, target_temp=predictions_temp)

        responses = jsonify(final_predictions.to_json(orient="records", date_format='iso'))
        responses.status_code = 200

        # with open('result.json', 'w') as outfile:
        #     outfile.write(final_predictions.to_json(orient="records", date_format='iso'))

        return (responses)

@app.errorhandler(400)
def bad_request(error=None):
    message = {
        'status': 400,
        'message': 'Bad Request: ' + request.url + '--> Please check your data payload...',
    }
    resp = jsonify(message)
    resp.status_code = 400

    return resp
app.run(debug=True)