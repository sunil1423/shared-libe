def call(Map cdPipeParams) {
    pipeline {
        agent any
 
	environment {
	    REPO_URL = "${cdPipeParams.scmUrl}"
	    BRANCH_NAME = "${cdPipeParams.branch}"
	    GIT_CRED = "${cdPipeParams.gitCredId}"
	    APP_NAME = "${cdPipeParams.appName}"
	}
 
        stages {
            stage('Deploy to container') {
                steps {
                    script {
                        echo "Deploying the ${APP_NAME} application of ${BRANCH_NAME} branch in container"
                    }
                }
            }
        }
 
        post {
            success {
                echo "CD Pipeline executed successfully for ${BRANCH_NAME} branch"
            }
 
            failure {
                echo "CD Pipeline execution failed for ${BRANCH_NAME} branch"
            }
        }
    }
}
