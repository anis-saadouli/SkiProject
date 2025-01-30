pipeline {
    agent any

    environment {
        SONAR_SCANNER_HOME = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    }

    stages {
        // Stage 1: Récupération du projet depuis GitHub/GitLab
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/anis-saadouli/SkiProject'
            }
        }

        // Stage 2: Exécution des tests unitaires avec l'utilisation de Mockito et JUnit
        stage('Build and Test') {
            steps {
                sh 'mvn clean install' // For Maven projects
            }
        }

        // Stage 3: Évaluation de la qualité du code grâce à l'outil SonarQube
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=your-project-key \
                        -Dsonar.projectName=YourProjectName \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.junit.reportPaths=target/surefire-reports \
                        -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml \
                        -Dsonar.host.url=http://192.168.56.10:9000 \
                        -Dsonar.login=sqa_3d07d0b9ee32ef3cdad5d3552332bcd0dddbfba8
                    """
                }
            }
        }

        // Stage 4: Dépôt du livrable sur Nexus
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }

        // Stage 5: Construction de l'image Docker (pour la partie Spring) à partir du fichier Dockerfile
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker-compose build'
                }
            }
        }

        // Stage 6: Publication de l'image créée sur Docker Hub ou Nexus
        stage('Push Docker Images') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker-hub-token', variable: 'DOCKER_HUB_TOKEN')]) {
                        // Log in to Docker Hub using the access token
                        sh 'echo $DOCKER_HUB_TOKEN | docker login -u anisxo --password-stdin'

                        // Tag the Docker image
                        sh 'docker tag station-ski-app anisxo/station-ski-app'

                        // Push the Docker image to Docker Hub
                        sh 'docker push anisxo/station-ski-app'
                    }
                }
            }
        }

        // Stage 7: Démarrage simultané de l'image contenant le livrable Spring et de l'image MySQL en utilisant docker-compose
        stage('Start Containers with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}