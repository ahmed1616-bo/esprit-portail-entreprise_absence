pipeline{
  agent any
   environment {
       
        SONAR_TOKEN = credentials('sonar')
    }
  stages{
    stage("Checkout code develop")
     {
        when{
        branch 'develop'
        }
        steps{
             checkout scm

        }


    }
          stage("SonarQube Scan") {
               when{ branch 'develop'}
            steps {
                withSonarQubeEnv('sonar') {  
                    sh '''
                    
                    mvn sonar:sonar \
                        -Dsonar.projectKey=absence} \
                        -Dsonar.projectName="absence - ${BRANCH_NAME}" \
                        -Dsonar.login=${SONAR_TOKEN}
                    
                  
                    '''
                }
            }
        }


    stage("Checkout code QA")
     {
        when{
        branch 'qaulite'
        }
        steps{
             checkout scm

        }

     }
    stage("Checkout code prod")
     {
        when{
        branch 'master'
        }
        steps{
             checkout scm

        }


    }
  }

}