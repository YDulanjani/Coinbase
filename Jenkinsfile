pipeline {
    agent any
       environment {
       //once you sign up for Docker hub, use that user_id here
       registry = "ydulanjani/coinbase"
       //- update your credentials ID after creating credentials for connecting to Docker Hub
       registryCredential = 'dockerhub'
       dockerImage = ''
    }

    tools {
            maven 'Maven 3.8.4'
            dockerTool 'MyDocker'
    }
    stages {
//          stage ('InitializeD') {
//
//                    def dockerHome = tool 'MyDocker'
//                    env.PATH = "${dockerHome}/bin:${M2_HOME}/bin:${env.PATH}"
//
//                 }


        stage ('Initializee') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "JAVA_OPTS = ${JAVA_OPTS}"
                '''
            }
        }

        stage ('checkout') {
            steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/dev']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/YDulanjani/Coinbase']]])
//              script {
//                   env.TAG_ON_DOCKER_HUB = input message: 'User input required',
//                       parameters: [choice(name: 'Tag on Docker Hub', choices: 'no\nyes', description: 'Choose "yes" if you want to deploy this build')]
//              }

            }


        }

        stage ('Run Test Suite') {
//             when {
//                  environment name: 'TAG_ON_DOCKER_HUB', value: 'yes'
//             }

            input{
                message "Do you want to Run Test Suite?"
            }
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
                        kubeconfigId: 'KubeConfigYam',
                        enableConfigSubstitution: true
                        )
                }
            }
        }

    }
}
