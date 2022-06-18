pipeline {
    agent any
    environment {
       PROJECT_ID = 'silent-octagon-344103'
       CLUSTER_NAME = 'k8s'
       LOCATION = 'asia-northeast3-a'
       CREDENTIALS_ID = 'juu924'
    }
    stages {
            stage('Checkout code') {
                steps {
                    checkout scm
                }
            }
            stage('build gradle') {
                steps {
                        sh 'chmod +x gradlew'
                        sh  './gradlew clean build'
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
                   croffle = docker.build("juu924/croffle")
                }
            }
            stage("Push image") {
                steps {
                    script{
                      docker.withRegistry('https://registry.hub.docker.com','docker hub'){
                       croffle.push("latest")
                      }
                    }
                }
            }
            stage('Deploy to GKE'){
              when {
                branch 'main'
              }
              steps{
                sh

              }

            }
        }
  }
