pipeline {
    agent any
    tools {
        maven 'apache maven 3.6.3'
        jdk 'JDK 9'
    }
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
   stage ('Short Tests') {
      steps {
         sh 'mvn package'
         archiveArtifacts artifacts: 'src/**/*.java'
         archiveArtifacts artifacts: 'target/*.jar'
      }
  }

}