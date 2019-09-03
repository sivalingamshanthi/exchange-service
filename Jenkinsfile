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
        stage('Deploy'){
            steps{
                withCredentials([[
                    $class              : 'UsernamePasswordMultiBinding',
                    credentialsId       : 'PCF_LOGIN',
                    usernameVariable    : 'USERNAME',
                    passwordVariable    : 'PASSWORD'
                    ]]){
                        sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                        sh '/usr/local/bin/cf target -o solstice-org -s sshanthi_cnt'
                        sh '/usr/local/bin/cf push exchange-service -p build/libs/exchange-service-0.0.1-SNAPSHOT.jar -b java_buildpack --random-route'
                }
            }
        }
    }
}