pipeline {
    agent any
    stages {
        stage('probar docker') {
            steps {                
                bat '& minikube docker-env | Invoke-Expression'
            }
        }
    }
}