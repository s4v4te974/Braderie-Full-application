// jenkins top level mandatory
pipeline{
    // determine in which environement we run (multi cluster or else)
    agent any

    // determine all the step of our build
    stages{
        stage("build"){
            steps {
                echo "build"
            }
        }
        stage("test"){
             steps {
                echo "test"
            }
        }
        stage("deploy"){
              steps {
                echo "deploy"
            }
        }
    }

}
