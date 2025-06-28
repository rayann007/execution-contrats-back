pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-creds'  // ID des credentials Jenkins
        DOCKERHUB_USER = 'rayan007'
    }

    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/rayan007/git@github.com:rayann007/execution-contrats-back.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'ng build'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKERHUB_USER/myapp-backend ./backend'
                sh 'docker build -t $DOCKERHUB_USER/myapp-frontend ./frontend'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
                    sh 'docker push $DOCKERHUB_USER/myapp-backend'
                    sh 'docker push $DOCKERHUB_USER/myapp-frontend'
                }
            }
        }

    }

    post {
        success {
            echo '✅ Build and push completed successfully!'
        }
        failure {
            echo '❌ Build or push failed!'
        }
    }
}
