#!/bin/bash

username="admin"
password="qwer#1234"
jboss_as_dir="$HOME/jboss-eap-6.4/"

$jboss_as_dir"bin/add-user.sh" $username $password

cd $jboss_as_dir"standalone/deployments"
wget s01.yyz.redhat.com/dcheung/mass-bugzilla-modifier.war                       
cd -

$jboss_as_dir"bin/standalone.sh" &

mvn exec:java -Dexec.mainClass="SmokeTest"

for i in `ps -aux | grep Standalone | gawk '{ print $2 }'`; do kill -9 $i; done
for i in `ps -aux | grep standalone | gawk '{ print $2 }'`; do kill -9 $i; done
for i in `ps -aux | grep Domain | gawk '{ print $2 }'`; do kill -9 $i; done
for i in `ps -aux | grep domain | gawk '{ print $2 }'`; do kill -9 $i; done
