pipeline{
  agent any
   environment {
       
        SONAR_TOKEN = credentials('sonar')
        SNYK_TOKEN = credentials('snyk-auth-token') 
        DEPENDENCY_CHECK_HOME = '/tmp/dependency-check'
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
    stage('Snyk Security Scan') {
     when{ branch 'develop'}
    steps {
        snykSecurity(
            snykInstallation: 'Snyk-Latest',
            snykTokenId: 'snyk-auth-token',
            severity: 'medium',
            failOnIssues: false,
            additionalArguments: '--all-projects --file=pom.xml'
        )
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
    stage('OWASP Dependency-Check Vulnerabilities') {
  when {
    branch 'develop'
  }
  
  steps {
    withCredentials([string(credentialsId: 'NVD_API_KEY', variable: 'NVD_API_KEY')]) {
      sh '''
        ${DEPENDENCY_CHECK_HOME}/bin/dependency-check.sh \
        --nvdApiKey $NVD_API_KEY \
        --project esprit-portail-entreprise_absence \
        --scan . \
        --out ./ \
        --format ALL \
        --prettyPrint
      '''
    }
    dependencyCheckPublisher pattern: 'dependency-check-report.xml'
  }
}

stage('Security Scan - SpotBugs') {
  when {
    branch 'develop'
  }
    steps {
        sh 'mvn spotbugs:spotbugs'
    }
    post {
        always {
            recordIssues(
                enabledForFailure: true,
                tools: [spotBugs(pattern: '**/target/spotbugsXml.xml')]
            )
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