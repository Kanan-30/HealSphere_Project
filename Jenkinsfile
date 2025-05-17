pipeline {
    agent any // Ensure this agent has Docker, kubectl, Ansible, and k8s collection installed

    triggers {
        githubPush()
    }

    environment {
        // IMPORTANT: Use the Docker Hub user specified in your K8s manifests
        DOCKER_HUB_USER = 'kanang'
        // REPO_NAME seems unused now, but keeping if needed elsewhere
        // REPO_NAME = 'healsphere_project'
        IMAGE_TAG = 'latest'
        // Define the Kubernetes namespace used in your manifests
        K8S_NAMESPACE = 'mindnotes'
        // Define the path to your K8s manifests within the repo checkout
        K8S_MANIFEST_PATH = 'k8s' // Adjusted based on your 'tree' output
    }

    stages {
        stage('Checkout') {
            steps {
                // Assuming Jenkinsfile is at the root of the repo, adjust path if needed
                // If Jenkinsfile is inside MIndNotes, checkout '.'
                git branch: 'main', url: 'https://github.com/Kanan-30/HealSphere_Project.git'
            }
        }

        stage('Build Microservices') {
            steps {
                script {
                    // Map service names (as used in k8s/dockerhub) to their build context paths
                    def servicesToBuild = [
                        'login-service': 'login_service',
                        'self-discovery-service': 'self_discovery_service',
                        'coping-with-crisis-service': 'Coping_with_services', // Corrected path from compose file context
                        'self-help-service': 'selfHelp',           // Corrected path from compose file context
                        'letter-service': 'letters_Service',     // Corrected path from compose file context
                        'frontend': 'mentalhealthapp'         // Corrected path from compose file context
                    ]

                    for (serviceName in servicesToBuild.keySet()) {
                        def contextPath = servicesToBuild[serviceName]
                        def fullImageName = "${DOCKER_HUB_USER}/${serviceName}:${IMAGE_TAG}"
                        // Use --tag directly during build for simplicity
                        sh "docker build -t ${fullImageName} ${contextPath}"
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    // Use the same service names as the build stage keys
                    def servicesToPush = [
                        'login-service',
                        'self-discovery-service',
                        'coping-with-crisis-service',
                        'self-help-service',
                        'letter-service',
                        'frontend'
                    ]

                    // Assuming DockerHubCred is setup in Jenkins Credentials
                    docker.withRegistry('https://index.docker.io/v1/', 'DockerHubCred') {
                        for (serviceName in servicesToPush) {
                          def fullImageName = "${DOCKER_HUB_USER}/${serviceName}:${IMAGE_TAG}"
                          sh "docker push ${fullImageName}"
                        }
                    }
                }
            }
        }

        


        stage('Deploy to Kubernetes via Ansible') {
            steps {
                // Force UTF-8 locale AND specify collections path (singular)
                withEnv([
                    "LANG=en_US.UTF-8",
                    "LC_ALL=en_US.UTF-8",
                    "ANSIBLE_COLLECTIONS_PATH=/usr/share/ansible/collections" // Corrected to singular
                ]) {
                    script {
                        echo "--- Running Ansible for App and ELK deployments ---"
                        def absoluteAppManifestPath = "${env.WORKSPACE}/${env.APP_K8S_MANIFEST_PATH}"
                        def absoluteElkManifestPath = "${env.WORKSPACE}/${env.ELK_K8S_MANIFEST_PATH}"

                        echo "App Manifest path: ${absoluteAppManifestPath}"
                        echo "ELK Manifest path: ${absoluteElkManifestPath}"
                        echo "App Namespace: ${env.APP_K8S_NAMESPACE}"
                        echo "ELK Namespace: ${env.ELK_K8S_NAMESPACE}"

                        
                    // --- BEGIN DEBUG ---
                    echo "Listing contents of ELK manifest directory: ${absoluteElkManifestPath}"
                    sh "ls -la ${absoluteElkManifestPath}" // <<< ADD THIS LINE BACK
                    // --- END DEBUG ---


                        ansiblePlaybook(
                            playbook: 'deploy-k8s.yml',
                            inventory: 'inventory-k8s',
                            extras: "-e app_k8s_manifest_path=${absoluteAppManifestPath} -e app_k8s_namespace=${env.APP_K8S_NAMESPACE} -e elk_k8s_manifest_path=${absoluteElkManifestPath} -e elk_k8s_namespace=${env.ELK_K8S_NAMESPACE}"
                        )
                    }
                    // script {
                    //     echo "--- Running Ansible with forced UTF-8 locale and collections path ---"
                    //     def absoluteManifestPath = "${env.WORKSPACE}/k8s"
                    //     echo "Manifest path being passed to Ansible: ${absoluteManifestPath}"

                    //     ansiblePlaybook(
                    //         playbook: 'deploy-k8s.yml',
                    //         inventory: 'inventory-k8s',
                    //         extras: "-e k8s_manifest_path=${absoluteManifestPath} -e k8s_namespace=${env.K8S_NAMESPACE}"
                    //     )
                    // }
                } // End withEnv
            }
        }
    }

    post {
        success {
            mail to: 'kanan.gupta@iiitb.ac.in', // Keep your notification email
                subject: "✅ [K8s] HealSphere Deployment SUCCESS",
                body: "The HealSphere app and ELK stack were deployed successfully!"
        }
        failure {
            mail to: 'kanan.gupta@iiitb.ac.in', // Keep your notification email
                subject: "❌ [K8s] HealSphere Deployment FAILED",
                body: "The HealSphere Kubernetes deployment failed. Please check Jenkins logs (${env.BUILD_URL})."
        }
        always {
            cleanWs()
        }
    }
}
