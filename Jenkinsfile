pipeline {
    agent any

    environment {
        SONAR_SCANNER_HOME = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    }

    stages {
        // Stage 1: Checkout Code
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/anis-saadouli/SkiProject'
            }
        }

        // Stage 2: Build and Test
        stage('Build and Test') {
            steps {
                sh 'mvn clean install' // For Maven projects
            }
        }

        // Stage 3: SonarQube Analysis
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

        // Stage 4: Deploy to Nexus
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }

        // Stage 5: Build and Deploy with Docker Compose
        stage('Docker Compose') {
            steps {
                script {
                    // Build Docker images
                    sh 'docker-compose build'

                    // Start containers
                    sh 'docker-compose up -d'
                }
            }
        }

        // Stage 6: Push Docker Images to Docker Hub
        stage('Push Docker Images') {
            steps {
                script {
                    sh 'docker login -u anisxo -p dckr_pat_sRRvdJM73D4FHJSYXzdZEaAWhxQ'
                    // Tag the Docker image
                    sh 'docker tag station-ski-app anisxo/station-ski-app'
                    sh 'docker push anisxo/station-ski-app'
                }
            }
        }
    }

    resultss {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}