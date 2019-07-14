pipeline {
    agent any
    stages {
        stage('probar docker') {
            steps {                
                bat '@FOR /f "tokens=*" %i IN ('minikube docker-env') DO @%i'
            }
        }
    }
}