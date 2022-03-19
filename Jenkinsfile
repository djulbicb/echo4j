pipeline {
    agent any

    stages {
        stage('Build') {
            when {
                expression {
                    JOB_NAME == "gittest"
                }
            }
            steps {
               echo 'Building the applcation'
            }

            post {
                success {
                    echo 'Build was success'
                }
            }
        }
        stage('Testing') {
            when {
                expression {
                    JOB_NAME != "gittest"
                }
            }
            steps {
               echo "${JOB_NAME}"
               echo "${env.JOB_NAME}"
               echo '${env.JOB_NAME} - single quote stampa samo characters'
            }
        }
    }
    post {
        always {
        }
        failure {
        }
    }
}
