node{
	def app
	stage('Clone repository') {
	git 'https://github.com/ProjectDevelopment3/Croffle-server.git'
	}
	stage('Build image'){
	app = docker.build("juu924/croffle-backend")
	}
	stage('Test image'){
	app.inside {
	   echo 'Test image...'
	  }
	}
   	stage('Push image'){
	   docker.withRegistry('https://hub.docker.com/', 'juu924'){
	     app.push("${env.BUILD_NUMBER}")
             app.push("latest")
           }
	 }
    }
