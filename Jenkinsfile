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
        stage('send to sonar') {
            steps {
                powershell 'mvn sonar:sonar'
            }
        }
        stage('security check') {
            steps {
                powershell 'mvn verify'
            }
        }
        stage('create image') {
            steps {
                powershell 'docker build -t diegochavezcarro/product-app:1.0.0 .'
            }
        }
        stage('deploy k8s and hpa') {
            steps {
                powershell 'kubectl create -f product-app-k8s-template.yaml'
                powershell 'kubectl create -f product-hpa.yaml'
            }
        }        
    }
}