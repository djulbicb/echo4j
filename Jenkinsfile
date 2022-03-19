pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               echo "Branch is ${branchName}"
               echo '> Building the applcation'
            }

            post {
                success {
                    echo '> Build was success'
                }
            }
        }
        stage('Testing') {
            steps {
               echo "${JOB_NAME}"
               echo "${env.JOB_NAME}"
               echo '${env.JOB_NAME}'
            }
        }
    }
}
