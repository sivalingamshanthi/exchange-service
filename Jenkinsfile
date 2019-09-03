pipeline {
    agent any 
    stages {
        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew check'
            }
        }
        stage('Javadoc') {
            steps {
                sh './gradlew javadoc'
            }
        }
        stage('Deploy to PCF'){
            steps{
                pushToCloudFoundry
                cloudSpace: 'sshanthi_cnt',
                credentials: '8c180327bc9648b98ab5352293eb2e4f',
                organization: 'solstice-org',
                target: 'api.run.pivotal.io'
            }
        }
    }
}