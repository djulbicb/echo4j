#!/usr/bin/env.groovy
library identifier: 'shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource', remote: 'https://github.com/djulbicb/jenkins-shared.git', credentialsId: 'github']
)
@Library('shared-library')
def gv
pipeline {
    agent any
    tools {
        maven 'maven-installation'
    }
    stages {
        stage ("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage ("build") {
            steps {
                script {
                    // ovo je funkcija iz shared lib
                    buildImage("docker-hub", "djulb/echo-ci-test:1.0")
                }
            }
        }
    }
}