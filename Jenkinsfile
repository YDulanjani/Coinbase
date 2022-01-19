pipeline {
    agent any
       environment {
       //once you sign up for Docker hub, use that user_id here
       registry = "ydulanjani/coinbase"
       //- update your credentials ID after creating credentials for connecting to Docker Hub
       registryCredential = 'dockerhub'
       dockerImage = ''
    }
    stages {

        stage ('checkout') {
            steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/dev']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/YDulanjani/Coinbase']]])
            }
        }

        stage ('Run Test Suite') {
            steps {
                script {
                sh 'mvn clean install -DCoinbaseTestSuite'
                }
            }
        }

        stage ('Build docker image') {
            steps {
                script {
                dockerImage = docker.build registry
                }
            }
        }

         // Uploading Docker images into Docker Hub
        stage('Upload Image') {
         steps{
             script {
                docker.withRegistry('', 'dockerhub') {
                dockerImage.push()
                }
            }
          }
        }

        stage ('K8S Deploy') {
            steps {
                script {
                    kubernetesDeploy(
                        configs: 'kube/Coinbase.yaml',
                        kubeconfigId: 'mykubeconfig',
                        enableConfigSubstitution: true
                        )
                    bat 'kubectl apply -f kube'

                }
            }
        }

    }
}
