node {
    echo "Building ------> build${env.BUILD_NUMBER}"
    def giturl = "https://github.com/dunc101/osdemo3.git"
    def pomdirectory = "osdemo3"
    def app = "osdemo3"
    def readinessprobe = "http://localhost:8080/health"
    def livenessprobe = "http://localhost:8080/health"
    def replicas = "3"
    
    stage 'Checkout and Build'
    build job: 'demo-checkoutandbuild', parameters: [[$class: 'StringParameterValue', name: 'GITURL', value: "$giturl"], [$class: 'StringParameterValue', name: 'POMDIRECTORY', value: "$pomdirectory"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"]]
    
    stage 'Push to Dev'
    build job: 'demo-dev', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"]]
    
    stage 'Deploy to Test'
    build job: 'demo-test', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"]]
    
    stage 'Request Authorization to Promote to Stage'
    def changelogs=readFile("/tmp/${app}/revisionlogs")
      input message: "Please approve the promotion to the Stage environment.  All tests and builds have passed.  The change logs are as follows: \n" + 
                    "--------------------------------------------------------------------\n" +
                    "${changelogs}" +
                    "--------------------------------------------------------------------", ok: 'Approve'

    stage 'Deploying to stage'
    build job: 'demo-stage', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"], [$class: 'StringParameterValue', name: 'REPLICAS', value: "$replicas"]]
}
