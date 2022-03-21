pipeline {
  agent any
  tools {
    maven "maven-installation"
  }
  stages("Build jar") {
    script {
      echo "Building the application"
      sh "mvn clean package"
    }
  }
  stages("Build image") {
    script {
      echo "Building the docker image"
      withCredentials([ usernamePassword(credentialsId: 'github', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t djulb/echo4j:3.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push djulb/echo4j:3.0'
                    }
    }
  }
}
