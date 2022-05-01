pipeline {
    agent any
    stages {
        stage ('Build') {
            withMaven(
        		
        		maven: 'maven', // (1)

        		mavenLocalRepo: '.repository', // (2)
		        
        		mavenSettingsConfig: 'my-maven-settings' // (3)
    			)	
    			
    			sh 'mvn clean verify'
            
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}
