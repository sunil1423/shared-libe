
def call(Map pipeParams) {
    pipeline {
        agent any
 
	environment {
	    REPO_URL = "${pipeParams.scmUrl}"
	    BRANCH_NAME = "${pipeParams.branch}"
	    GIT_CRED = "${pipeParams.gitCredId}"
	    APP_NAME = "${pipeParams.appName}"
	}
 
        stages {
            stage('Initialize') {
                steps {
                    script {
                        echo "Initializing Git Repository"
                        git branch: BRANCH_NAME, credentialsId: GIT_CRED, url: REPO_URL
                    }
                }
            }
 
            stage('Build') {
                steps {
                    script {
                        echo "Building ${APP_NAME} application of ${BRANCH_NAME} branch"
                    }
                }
            }
 
            stage('Test') {
                steps {
                    script {
                        echo "Testing ${APP_NAME} application of ${BRANCH_NAME} branch"
                    }
                }
            }
        }
 
        post {
            success {
                echo "CI Pipeline executed successfully for ${BRANCH_NAME} branch"
		build job: "CD-pipeline/${BRANCH_NAME}", wait: true
            }
 
            failure {
                echo "CI Pipeline execution failed for ${BRANCH_NAME} branch"
            }
        }
    }
}
