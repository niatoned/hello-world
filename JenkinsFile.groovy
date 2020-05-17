node{
      def mvnHome = tool name: 'M2_HOME', type: 'maven' 
      stage('Checkout'){
         echo "KOU Checkout"
         git 'https://github.com/khalidOubelque/hello-world.git'
       
      }  
      stage('Build'){
         echo "KOU Maven Build"
        sh "${mvnHome}/bin/mvn clean package -Dmaven.test.skip=true"
      }
     stage ('Test-JUnit'){
          echo "KOU UT"
         sh "'${mvnHome}/bin/mvn' test surefire-report:report"
      }  
     stage('Deploy') { 
            echo "KOU Deploy"
              sshpass -p "khalidoubelque" sh 'scp -r -o StrictHostKeyChecking=no webapp/target/*.war dockeradmin@172.31.35.165:/home/dockeradmin/webapp/target'
     }
      
    
      
 }