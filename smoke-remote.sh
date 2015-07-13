#!/bin/bash
#Emmett Underhill July 7 2015
#This is the script that gets run on the vm
#during a smoke testing session. I won't comment
#on what it does exactly because that is outlined
#clearly in the main function

username="admin"
password="qwer#1234"
jboss_as_dir="/usr/share/jbossas/"
jar_file="original-smokes-1.0-SNAPSHOT.jar"

function main() {
    get_packages
    add_jbossas_user
    get_deployment_app
    get_smoke_jar
    test_jbossas
    test_jbossas_domain
    print_logs
    kill_firefox
}

function get_packages() {
    yum -y groupinstall "X Window System" Desktop
    yum -y install firefox
    yum -y install figlet
}
function add_jbossas_user() {
    $jboss_as_dir"bin/add-user.sh" $username $password
}

function get_deployment_app() {
    wget s01.yyz.redhat.com/dcheung/mass-bugzilla-modifier.war -O $jboss_as_dir"standalone/deployments/mass-bugzilla-modifier.war"
}

function get_smoke_jar() {
    wget s01.yyz.redhat.com/eunderhi/$jar_file -O $HOME/$jar_file
}

function test_jbossas() {
    service jbossas start && java -jar -DSERVER=standalone $jar_file
    service jbossas stop
}
function test_jbossas_domain() {
    service jbossas-domain start && java -jar $jar_file
    service jbossas-domain stop
}

function print_logs() {
    cd "/var/log/jbossas"
    figlet !!!ERRORS!!!
    echo "Hey, nice job if you're reading this then there aren't any errors!"
    echo "However if there's stuff after this message then there ARE errors"
    echo "in which case I revoke the nice job."
    grep -RHEin "ERROR|FATAL|EXCEPT" . | grep -v "DEBUG"
}
function kill_firefox() {
    killall firefox
}

main
