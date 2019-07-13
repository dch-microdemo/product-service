pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build win bat') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}