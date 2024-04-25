from flask import Flask

app = Flask(__name__)

@app.route('/users')
def hello_world():
  return "Hello"

@app.route('/hi')
def hi():
  return "hi"

app.run(debug=True)