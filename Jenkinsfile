pipeline {
    agent any
    environment {
        PROJECT_ID = 'silent-octagon-344103'
        CLUSTER_NAME = 'k8s'
        LOCATION = 'asia-northeast3-a'
        CREDENTIALS_ID = 'gke'
    }
    stages {
        stage("Checkout code") {
            steps {
                checkout scm
                sh 'cp /var/jenkins_home/workspace/application-oauth.properties  /var/jenkins_home/workspace/docker-pipeline_main/src/main/resources/application-oauth.properties'
            }
        }
        stage("build gradle") {
            steps {
                sh 'chmod +x gradlew'
                sh  './gradlew clean bootJar'
            }
            post {
                success {
                    echo 'gradle build success'
                }
                failure {
                    echo 'gradle build failed'
                }
            }
        }
        stage('Build image'){
            steps {
                script {
                    app = docker.build("juu924/croffle:${env.BUILD_ID}", "--no-cache .")
                }
            }
        }
        stage("Push image") {
            steps {
                script{
                    docker.withRegistry('https://registry.hub.docker.com','docker-juu924'){
                        app.push("latest")
                        app.push("${env.BUILD_ID}")
                    }
                }
            }
        }
        stage('Deploy to GKE'){
            when {
                branch 'main'
            }
            steps{
                sh "sed -i 's/croffle:latest/croffle:${env.BUILD_ID}/g' deployment.yaml"
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            }
        }
    }
}
