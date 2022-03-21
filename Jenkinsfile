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
      when {
        expression {
          BRANCH_NAME == "master"
        }
      }
      steps {
        echo "Running on master branch"
      }
    }

   stage("Build Jenkins") {
     when {
        expression {
          BRANCH_NAME == "jenkins"
        }
      }
      steps {
        echo "Running on jenkins branch"
      }
   }
    
  }
}
