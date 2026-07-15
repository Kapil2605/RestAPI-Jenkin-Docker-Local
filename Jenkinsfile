pipeline {
    agent any

    environment {
        APP_IMAGE = "restapi-github"
        APP_CONTAINER = "restapi-container"

        MYSQL_IMAGE = "mysql:8.0"
        MYSQL_CONTAINER = "mysql-container"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Kapil2605/RestAPI-Jenkin-Docker-Local.git'
            }
        }

        stage('Start MySQL') {
            steps {
                bat '''
                docker stop %MYSQL_CONTAINER%
                docker rm %MYSQL_CONTAINER%
                exit /b 0
                '''

                bat '''
                docker run -d ^
                --name %MYSQL_CONTAINER% ^
                -p 3307:3306 ^
                -e MYSQL_ROOT_PASSWORD=fighter ^
                -e MYSQL_DATABASE=spring_RestAPI_2 ^
                %MYSQL_IMAGE%
                '''
            }
        }

        stage('Wait for MySQL') {
		    steps {
		        sleep(time: 20, unit: 'SECONDS')
		    }
		}

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Verify JAR') {
            steps {
                bat 'dir target'
            }
        }

        stage('Build App Docker Image') {
            steps {
                bat 'docker build -t %APP_IMAGE% .'
            }
        }

        stage('Remove Existing App Container') {
            steps {
                bat '''
                docker stop %APP_CONTAINER%
                docker rm %APP_CONTAINER%
                exit /b 0
                '''
            }
        }

        stage('Run App Container') {
            steps {
                bat '''
                docker run -d ^
                --name %APP_CONTAINER% ^
                -p 8081:8080 ^
                %APP_IMAGE%
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'docker ps'
            }
        }
    }

    post {
        success {
            echo 'MySQL and Spring Boot application deployed successfully!'
            echo 'Application URL: http://localhost:8081'
        }

        failure {
            echo 'Pipeline execution failed!'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}