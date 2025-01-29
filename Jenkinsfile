pipeline {
    agent any  // Runs on any available Jenkins agent

    stages {
        // Stage 1: Checkout Code
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/anis-saadouli/SkiProject'
            }
        }

        // Stage 2: Build
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install -B -U'  // Builds the project
                }
            }
        }
    }

    post {
        success {
            echo '✅ Checkout and Build completed successfully!'
        }
        failure {
            echo '❌ Checkout or Build failed!'
        }
    }
}
