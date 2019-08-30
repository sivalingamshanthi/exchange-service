pipeline {
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
        stage('Javadoc') {
            steps {
                sh './gradlew javadoc'
            }
        }
    }
}