pipeline {
    agent any
    stages {
        stage('enviroment settings') {
            steps {                
                powershell '& minikube docker-env | Invoke-Expression'
            }
        }
        stage('Build') {
            steps {
                powershell 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') { 
            steps {
                powershell 'mvn test' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
        stage('create image') {
            steps {
                powershell 'docker build -t diegochavezcarro/product-app:1.0.0 .'
            }
        }
    }
}