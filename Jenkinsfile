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
//             jdk 'jdk8'
    }
    stages {
         stage ('InitializeD') {
                    steps {
                       def dockerHome = tool 'MyDocker'
                       env.PATH = "${dockerHome}/bin:${M2_HOME}/bin:${env.PATH}"
                    }
                }


        stage ('Initializee') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                '''
            }
        }

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
