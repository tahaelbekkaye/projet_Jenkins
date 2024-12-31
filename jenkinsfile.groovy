pipeline {
    agent any
    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-creds'
        DOCKER_IMAGE = 'elbekkayetaha/spring-crud-app'
        DOCKER_TAG = 'latest'
    }
    stages {
        stage('Cloner le projet') {
            steps {
                git url: 'https://github.com/tahaelbekkaye/projet_Jenkins.git', branch: 'master'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Tests unitaires') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Créer une image Docker') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
            }
        }
        stage('Push vers Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKER_CREDENTIALS_ID"]) {
                    sh 'docker push $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline terminé avec succès !'
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
    }
}
