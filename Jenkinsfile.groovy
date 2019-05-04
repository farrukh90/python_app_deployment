node {
    properties([parameters([string(defaultValue: 'IP', description: 'Where to build e.g IP', name: 'ENV', trim: true)])])
    stage("Clone repo"){
        git "git@github.com:farrukh90/flask-examples.git"
    }
    stage("Install Requirements"){
        sh "scp -r *   ec2-user@${ENV}:/tmp"
        sh "ssh ec2-user@${ENV} virtualenv /tmp/venv"
        sh "ssh ec2-user@${ENV} ./tmp/venv/activate"
        sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirements.txt"
    }
    stage("Start python app"){
        sh "ssh ec2-user@${ENV} python /tmp/01-hello-world/hello.py"
    }
}