pipeline {
    agent any
    stages {
        stage('probar docker') {
            steps {                
                powershell '& minikube docker-env | Invoke-Expression'
            }
        }
    }
}