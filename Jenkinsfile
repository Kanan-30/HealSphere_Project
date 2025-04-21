pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        DOCKER_HUB_USER = 'kanang'
        REPO_NAME = 'healsphere_project'
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kanan-30/HealSphere_Project.git'
            }
        }

        stage('Build Microservices') {
            steps {
                script {
                    sh 'docker compose build'
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        def images = [
                          'healsphere-login-service': 'login_service',
                          'healsphere-self-discovery-service': 'self_discovery_service',
                          'healsphere-coping-with-crisis-service': 'coping_with_services',
                          'healsphere-self-help-service': 'self_help_service',
                          'healsphere-letter-service': 'letter_service',
                          'healsphere-frontend': 'frontend'
                        ]
                        
                        for (actualImage in images.keySet()) {
                          def dockerHubName = "${DOCKER_HUB_USER}/${images[actualImage]}"
                          sh "docker tag ${actualImage}:latest ${dockerHubName}:${IMAGE_TAG}"
                          sh "docker push ${dockerHubName}:${IMAGE_TAG}"
                        }

                    }
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        ansiblePlaybook(
                            playbook: 'deploy.yml',
                            inventory: 'inventory'
                        )
                    }
                }
            }
        }
    }

    post {
        success {
            mail to: 'kanan.gupta@iiitb.ac.in',
                subject: "✅ HealSphere Deployment SUCCESS",
                body: "The HealSphere app was deployed successfully!"
        }
        failure {
            mail to: 'kanan.gupta@iiitb.ac.in',
                subject: "❌ HealSphere Deployment FAILED",
                body: "The HealSphere deployment failed. Please check Jenkins logs."
        }
        always {
            cleanWs()
        }
    }
}
