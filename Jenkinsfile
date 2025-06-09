pipeline{
   agent any
   environment {
       
        SONAR_TOKEN = credentials('sonar')
        SNYK_TOKEN = credentials('snyk-auth-token') 
        DEPENDENCY_CHECK_HOME = '/tmp/dependency-check'
         DOCKER_REGISTRY ='harbor.pixelslabs.com'
    }
    tools{
        maven 'mvn'
        dockerTool 'docker'
    }
    
  stages{
    stage("Checkout code develop") {
    when {
        branch 'develop'
    }
    steps {
        checkout scm
        
        script {
            // Get Git commit hash
            env.GIT_COMMIT = sh(
                returnStdout: true, 
                script: 'git rev-parse HEAD'
            ).trim()
            
            // Get branch name (with fallback)
            env.BRANCH_NAME = env.BRANCH_NAME ?: sh(
                returnStdout: true,
                script: 'git rev-parse --abbrev-ref HEAD'
            ).trim()
            
            // Get repository URL and extract repo name
            def gitUrl = sh(
                returnStdout: true,
                script: 'git config --get remote.origin.url'
            ).trim()
            
            // Extract repository name from URL
            def repoName = gitUrl.replaceFirst('.+(/|:)', '')
                               .replaceFirst('.git$', '')
            
           
            env.REPO_NAME = repoName
            
            
            echo "Repository: ${repoName}"
            echo "Branch: ${env.BRANCH_NAME}"
            echo "Commit: ${env.GIT_COMMIT}"
        }
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

    environment {
        SNYK_TOKEN = credentials('snyk-auth-token')
    }
    when{
        branch 'develop'
        }
    steps {
        sh '''
            # Download and install Snyk CLI
            curl -L https://static.snyk.io/cli/latest/snyk-linux -o snyk
            chmod +x snyk
            
            # Authenticate with Snyk
            ./snyk auth $SNYK_TOKEN
            
            # Test for vulnerabilities
            ./snyk test --all-projects --org=ahmed1616-bo --severity-threshold=medium || true
            
            # Monitor project (sends results to Snyk dashboard)
            ./snyk monitor --all-projects --org=ahmed1616-bo
        '''
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

    stage('Docker Build') {
            when {
                 
                    branch 'develop'
                      
            }
            steps {
                script {

                     def imageTag = "${env.REPO_NAME}:${env.GIT_COMMIT.substring(0, 7)}"
                  
                    sh """
                        docker build -t ${imageTag} .
                        echo "Successfully built image: ${imageTag}"
                    """
                    
                    
                    
                    
                    
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