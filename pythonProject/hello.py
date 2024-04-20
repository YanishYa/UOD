from flask import Flask

app = Flask(__name__)

@app.route('/users')
def hello_world():
  return "Hello"

app.run(debug=True)