#!/usr/bin/env.groovy
@Library('shared-library') // ovo je ime deklarisanog shared lib projekta u jenkinsu
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
                    gv.buildFunction()
                }
            }
        }
        stage ("library") {
            steps {
                // Ovo je funkcija iz shared library projekta
                buildJar()
            }
        }
    }
}