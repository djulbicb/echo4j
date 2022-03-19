def getCurrentBranch () {
    return sh (
            script: 'git rev-parse --abbrev-ref HEAD',
            returnStdout: true
    ).trim()
}
def testFunction () {
    echo 'building the application'
    echo "deploy ${params.VERSION}"
}
def buildFunction () {
    echo 'building the application'
}
return this