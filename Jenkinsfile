pipeline{
  agent any
   environment {
       
        SONAR_TOKEN = credentials('sonar')
    }
    tools{
        maven 'mvn'
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

    stage("build Artifact")
     {
        when{
        branch 'develop'
        }
        steps{
         sh "mvn clean package -DskipTests=true"
         archive 'target/*.jar'
        }


    }
          stage("SonarQube Scan") {
               when{ branch 'develop'}
            steps {
                withSonarQubeEnv('sonar') {  
                    sh '''
                    
                     mvn clean compile test-compile sonar:sonar \
                            -Dsonar.projectKey=esprit-portail-entreprise_absence \
                            -Dsonar.projectName="esprit-portail-entreprise_absence - ${BRANCH_NAME}" \
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
     stage("SonarQube Scan for qa") {
               when{ branch 'qa'}
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
    stage("Checkout code prod")
     {
        when{
        branch 'master'
        }
        steps{
             checkout scm

        }


    }
    stage("SonarQube Scan for prod") {
               when{ branch 'master'}
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

  }

}