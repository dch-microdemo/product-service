pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build win bat 2') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}