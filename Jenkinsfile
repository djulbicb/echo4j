#!/usr/bin/env.groovy
pipeline {
    agent any
    tools {
        maven 'maven-installation'
    }
    stages {
        stage('increment version') {
            steps {
                script {
                    echo "Incrementing app version..."
                    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}-SNAPSHOT versions:commit"
                    def matcher = readFile('pom.xml') =~ '<version>(.*)</version>'
                    def version = matcher[0][1]
                    matcher = null // throws exception Not seriazable regex. This cuts the link between

                    IMAGE_VERSION = "${version}-${BUILD_NUMBER}"
                    echo "version name is ${IMAGE_VERSION}"
                }
            }
        }
        stage('build app') {
            steps {
                script {
                    echo "Building the application...${IMAGE_VERSION}"
                    sh "mvn clean package"
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    echo "Building the application..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t djulb/echo4j:$IMAGE_VERSION ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push djulb/echo4j:$IMAGE_VERSION"
                    }
                }
            }
        }
        stage("deploy") {
            steps{
                script{
                    echo "deploying docker image to EC2..."
                }
            }
        }
        stage("Version bump") {
            steps{
                script{
                    // Created token as username as credentials
                    withCredentials([usernamePassword(credentialsId: 'gitwithtoken', passwordVariable: 'GIT_TOKEN', usernameVariable: 'GIT_USERNAME')]) {
                        //  For github:  https://stackoverflow.com/questions/19922435/how-to-push-changes-to-github-after-jenkins-build-completes
                        def encodedPassword = URLEncoder.encode("$GIT_PASSWORD",'UTF-8')
                        sh "git config user.email djulb@example.com"
                        sh "git config user.name djulbicb"
                        sh "git add ."
                        sh "git commit -m 'Triggered Build: ${env.BUILD_NUMBER}'"
                        sh "git remote set-url origin https://${GIT_TOKEN}@github.com/${GIT_USERNAME}/echo4j.git"
                        sh "git push origin HEAD:maven-versioning"
                    }
                }
            }
        }
    }
}