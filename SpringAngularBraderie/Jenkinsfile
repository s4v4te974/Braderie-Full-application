// jenkins top level mandatory
pipeline{
    // determine in which environement we run (multi cluster or else)
    agent any
    //define environment
    environment{
        // define version of build
        NEW_VERSION = '1.3.0'
    }
    // parameter quand on a des parametres exterieurs 
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description:'')
        booleanParam(name: 'executTests', defaultValue: true, description:'')
    }
    // determine all the step of our build
    stages{
        stage("build"){
             // imagine you want to run tests only on your branch
            steps {
                // echo pour afficher quelque chose
                echo "build"
                echo "building version ${NEW_VERSION}"
            }
        }
        stage("test"){
            // imagine you want to run tests only on your branch
            steps {
                echo "test"
            }
        }
        stage("deploy"){
                when{
                    expression{
                        params.executeTests
                    }
                }
              steps {
                echo "deploy"
            }
        }
    }
}
