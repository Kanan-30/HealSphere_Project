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
                        def services = ['login_service', 'self_discovery_service', 'coping_with_services', 'self_help_service', 'letter_service', 'frontend']
                        for (svc in services) {
                            def imageName = "${DOCKER_HUB_USER}/${svc}"
                            sh "docker tag ${svc} ${imageName}:${IMAGE_TAG}"
                            sh "docker push ${imageName}:${IMAGE_TAG}"
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
