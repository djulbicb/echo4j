pipeline {
  agent any
  tools {
    maven "maven-installation"
  }
  stages {
     stage("Build jar") {
      steps {
        script {
          echo "Building the application"
          sh "mvn clean package"
        }
      }
    }

    stage("Build image") {
        steps {
          script {
            echo "Building the docker image"
            withCredentials([ usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                              sh 'docker build -t djulb/echo4j:3.0 .'
                              sh "echo $PASS | docker login -u $USER --password-stdin"
                              sh 'docker push djulb/echo4j:3.0'
                          }
          }
        }
      }
  }
}
