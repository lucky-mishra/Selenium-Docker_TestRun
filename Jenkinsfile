pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Image') {
            steps {
                //sh
                bat "docker build -t mayankluckym/selenium-docker -f ./Dockerfile2.txt ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push mayankluckym/selenium-docker:latest"
			    }
            }
        }
		stage('Pull Image')
		{
		  steps{
		    bat "docker pull mayankluckym/selenium_2:latest"
		  }
		}
		stage("Start Grid"){
			steps{
				bat "docker-compose up -d hub chrome firefox"
			}
		}
		stage("Run Test"){
			steps{
				bat "docker-compose up selenium-framework"
			}
		}
	    stage('Report'){
		    steps{
			    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'coverage', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'Coverage Report', useWrapperFileDirectly: true])
		    }
	    }
	}
	post{
		always{
			bat "docker-compose down"

		}
	}
}
