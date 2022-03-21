pipeline {
  agent any
  tools {
    maven "maven-installation"
  }
  stages {
     stage("Init") {
      steps {
        script {
          echo "Testing the application"
          echo "Execute pipeline for ${BRANCH_NAME}"
        }
      }
    }

   stage("Build Master") {
      steps {
        when {
          expression {
            BRANCH_NAME == "master"
          }
        }
        echo "Running on master branch"
      }
    }

     stage("Build Jenkins") {
      steps {
        when {
          expression {
            BRANCH_NAME == "jenkins"
          }
        }
        echo "Running on jenkins branch"
      }
    }
    
  }
}
