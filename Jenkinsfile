pipeline{
  agent any
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