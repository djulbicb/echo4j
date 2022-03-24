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
                    withCredentials([usernamePassword(credentialsId: 'gitgit', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        def encodedPassword = URLEncoder.encode("$PASS",'UTF-8')

                        sh 'git config user.email "djulb@example.com"'
                        sh 'git config --global user.name "Global for all projects"'

                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'

                        //sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/djulb/echo4j.git"

                        //  For github:  https://stackoverflow.com/questions/19922435/how-to-push-changes-to-github-after-jenkins-build-completes

                        sh "git add ."
                        sh 'git commit -m "ci: version bump"'
                        sh 'git push origin HEAD:maven-versioning'

                        sh "git push https://${USER}:${encodedPassword}@github.com/${USER}/echo4j.git"
                    }
                }
            }
        }
    }
}