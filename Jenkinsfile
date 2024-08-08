// def dockerRepoUrl = "harbor.norsys-afrique.ma"
// def dockerImageName = "ssi-school-api"
// def dockerImageTag = "${dockerRepoUrl}/ssi-school/${dockerImageName}:latest"
//
// def rancherHost = "192.168.1.235"

pipeline {
   agent any
   stages {
      stage('Tests') {
         steps {
            sh("mvn clean test")
         }
      }
      stage('build') {
         steps {
            sh("mvn clean install -DskipTests")
         }
      }
//       stage('Docker Build and Tag') {
//          steps {
//             sh("docker build -f docker/Dockerfile -t ${dockerImageName} .")
//          }
//       }
//       stage('Push') {
//          environment {
//             DOCKER = credentials("naf-docker-registry")
//          }
//          steps {
//             sh("docker tag ${dockerImageName} ${dockerImageTag}")
//             sh("docker login -u $DOCKER_USR -p $DOCKER_PSW ${dockerRepoUrl}")
//
//             sh("docker push ${dockerImageTag}")
//          }
//       }

      }

   }

}
