pipeline {
    agent any
    stages {
        stage('probar docker') {
            steps {                
                bat 'powershell & minikube docker-env | Invoke-Expression'
            }
        }
    }
}