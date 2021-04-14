def call(String repoUrl) {
      echo "Hello Vanshika welcome to MavenBuild shared library"
   
       node {
           stage("Tools initialization") {
                   sh "mvn --version"
                   sh "java -version"
           }
           stage("Checkout Code") {
                   git branch: 'master',
                       url: "${repoUrl}"
           }
	   stage("Cleaning workspace") {
                   sh "mvn clean"
           }
           stage("Running Testcase") {
                   sh "mvn test"
           }
           stage("Packing Application") {
                   sh "mvn package -DskipTests"
           }
       }
   }
