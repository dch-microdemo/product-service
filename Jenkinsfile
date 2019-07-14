pipeline {
    agent any
    stages {
        stage('probar docker') {
            steps {                
                sh '& minikube docker-env | Invoke-Expression'
                sh 'docker version'
            }
        }
    }
}