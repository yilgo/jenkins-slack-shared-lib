@Library('jenkins-shared-lib') _

pipeline {
    agent any
    stages {
        stage ('Example') {
            steps {
                script {
                   slack2.notify(channel="ocp-alerts", message="*Hallo Welt <${BUILD_URL}|Click here>*")
                }
            }
        }
    }
}
