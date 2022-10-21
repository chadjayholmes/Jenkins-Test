pipeline {
    environment {
        registry = "chadjayholmes/calculator"
        registryCredential = 'dockerhub'
        dockerImage=''
    }
    agent any
    tools {
        maven 'apache maven 3.6.3'
        jdk 'JDK 9'
    }
    stages {
        stage ('Clean') {
            steps {
               sh 'mvn clean'
            }
        }
        stage ('Build') {
            steps {
               sh 'mvn compile'
            }
        }
       stage ('Short Tests') {
           steps {
              sh 'mvn -Dtest=CalculatorTest test'
           }
       }

       stage ('Long Tests') {
          steps {
             sh 'mvn -Dtest=CalculatorTest test'
          }
          post {
            success {
                junit 'target/surefire-reports/**/*.xml'
            }
          }
       }
       stage ('Package') {
          steps {
             sh 'mvn package'
             archiveArtifacts artifacts: 'src/**/*.java'
             archiveArtifacts artifacts: 'target/*.jar'
          }
       }
       stage ('Building image') {
            steps {
                script {
                    dockerImage = docker.build. registry + ":$BUILD_NUMBER"
               }
           }
       }
       stage ('Deploy Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential)
                    {
                        dockerImage.push()
                    }
                }
            }
       }
       stage ('Remove unused docker image') {
            steps {
                sh 'docker rmi $registry:$BUILD_NUMBER'
            }
       }
    }

    post {
        failure{
            mail to: 'chadjayholmes@gmail.com',
            subject: "Failed pipeline: ${currentBuild.fullDisplayName}",
            body: "Something is wrong with ${env.BUILD_URL}"
        }
    }

}
