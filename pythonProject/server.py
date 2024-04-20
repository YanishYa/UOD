import pandas as pd
import dill as pickle
from flask import Flask, jsonify, request

app = Flask(__name__)



@app.route('/predict', methods=['POST'])
def apicall():
    """API Call

    Pandas dataframe (sent as a payload) from API Call
    """
    try:
        test_json = request.get_json()
        test = pd.read_json(test_json, orient='records')
        #test = pd.read_json('time-interval.json')

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

        responses = jsonify(final_predictions.to_json(orient="records"))
        responses.status_code = 200

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