apiVersion: v1
kind: Pod
metadata:
  name: maven-pod
spec:
  containers:  # list of containers that you want present for your build, you can define a default container in the Jenkinsfile
    - name: maven
      image: maven:3.5.4-jdk-8-slim
      command: ["tail", "-f", "/dev/null"]  # this or any command that is bascially a noop is required, this is so that you don't overwrite the entrypoint of the base container
      imagePullPolicy: Always # use cache or pull image for agent

def call(String script) {
      echo "Hello Vanshika welcome to MavenBuild shared library"
   
       node ('maven-pod') {
           stage("Tools initialization") {
                   sh "mvn --version"
                   sh "java -version"
           }
           stage("Checkout Code") {
                   git branch: 'master',
                       url: script.env.GIT_SOURCE_URL
           }
	   stage("Cleaning workspace") {
                   sh "mvn clean"
           }
	   stage("Compling") {
                   sh "mvn compile"
           }
           stage("Running Testcase") {
                   sh "mvn test"
           }
           stage("Packing Application") {
                   sh "mvn package -DskipTests"
           }
       }
   }
