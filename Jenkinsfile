pipeline {
    agent any

    stages {
        stage('Initialize') {
            steps {
                echo 'Initializing CI/CD Pipeline'
    ***REMOVED***
***REMOVED***

        stage('Stop Old Backend') {
            steps {
                sh '''pid=`ps -ef | grep photoly | grep -v grep | awk \'{print $2***REMOVED***\'`
                if [ -n "$pid" ]
                then
                   kill -9 $pid
                fi'''
    ***REMOVED***
***REMOVED***

        stage('Deploy New Backend') {
            steps {
                sh 'clean package -Dmaven.test.skip=true'
                sh 'mv -f /root/.jenkins/workspace/photoly/api/target/photoly.war /home/ubuntu/photoly_b/photoly.war'
                sh 'chmod 777 /home/ubuntu/photoly_b/photoly.war'
                sh 'nohup java -jar /home/ubuntu/photoly_b/photoly.war > /home/ubuntu/photoly_b/photoly.log 2>1&'
    ***REMOVED***
***REMOVED***

        stage('Finish') {
            steps {
                echo 'Finished.'
    ***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
