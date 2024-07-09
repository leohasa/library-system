pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Iniciando la fase de build...'
                sh './mvnw clean package'
                echo 'Build completado.'
            }
        }
        stage('Test') {
            steps {
                echo 'Iniciando la fase de test...'
                sh './mvnw test'
                echo 'Test completado.'
            }
        }
        stage('Package') {
            steps {
                echo 'Iniciando la fase de empaquetado...'
                sh './mvnw package'
                echo 'Empaquetado completado.'
            }
        }
        stage('Deploy') {
                    steps {
                        sshPublisher(publishers: [
                            sshPublisherDesc(
                                configName: 'backend-aws',
                                verbose: true,
                                transfers: [
                                    sshTransfer(
                                        sourceFiles: 'target/**/*',
                                        removePrefix: 'target',
                                        remoteDirectory: '',
                                        execCommand: '''
                                            sudo pkill -f "java -jar /home/ubuntu/backend/LibrarySystem-0.0.1-SNAPSHOT.jar"
                                            sudo java -jar /home/ubuntu/backend/LibrarySystem-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > /dev/null 2>&1 &
                                        '''
                                    )
                                ],
                                usePromotionTimestamp: false,
                                sshRetry: [retries: 2, retryDelay: 30000]
                            )
                        ])
                    }
                }
    }
    post {
        success {
            echo 'Pipeline completado con éxito.'
        }
        failure {
            echo 'Pipeline falló.'
        }
    }
}
