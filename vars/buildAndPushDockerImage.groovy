#!usr/bin/env groovy
def call(String dockerHubCredentialsID, imageName, BUILD_NUMBER) {

        // Build Docker image
        echo "Building Docker image..."
        sh "docker build -t ${imageName} ."
  
  	    // Log in to DockerHub 
	      withCredentials([usernamePassword(credentialsId: "${dockerHubCredentialsID}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
		    sh "docker login -u ${USERNAME} -p ${PASSWORD}"
        }
        // Push Docker image
        echo "Pushing Docker image..."
        sh "docker push ${imageName}:${BUILD_NUMBER}"	
}
