pipeline {
    parameters {
        string( name: 'pcfToken', description: 'CF access token used to deploy the app')
    }
    agent any 
    stages {
        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }
        stage('Assemble') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Check') {
            steps {
                sh './gradlew check'
            }
        }
        stage('Deploy') {
            steps {
                sh 'python deploy.py ${params.pcfToken}'
            }
        }
    }
}