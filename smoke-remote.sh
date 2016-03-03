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
    test_jbossas
    test_jbossas_domain
    install_figlet_from_source
    print_logs
    kill_firefox
}

function get_packages() {
    yum -y groupinstall "X Window System" Desktop
    yum -y install firefox
}
function add_jbossas_user() {
    $jboss_as_dir"bin/add-user.sh" $username $password
}

function get_deployment_app() {
    wget s01.yyz.redhat.com/dcheung/mass-bugzilla-modifier.war -O $jboss_as_dir"standalone/deployments/mass-bugzilla-modifier.war"
}

function setup_hosts_file() {
    echo 127.0.0.1 localhost localhost.localdomain > /etc/hosts
}

function test_jbossas() {
    service jbossas start && firefox http://localhost:9990 && firefox http://localhost:8080/mass-bugzilla-modifier
    service jbossas stop
}
function test_jbossas_domain() {
    service jbossas-domain start && firefox http://localhost:9990
    service jbossas-domain stop
}
function install_figlet_from_source() {
    wget -N ftp://ftp.figlet.org/pub/figlet/program/unix/figlet-2.2.5.tar.gz
    tar xzf figlet*.tar.gz
    cd figlet*
    sed -i 's/$(prefix)\/share\/figlet/fonts/g' Makefile
    make figlet
}

function print_logs() {
    cd $HOME/figlet*
    ./figlet '!!!ERRORS!!!'
    cd "/var/log/jbossas"
    echo "Hey, nice job if you're reading this then there aren't any errors!"
    echo "However if there's stuff after this message then there ARE errors"
    echo "in which case I revoke the nice job."
    grep -RHEin "ERROR|FATAL|EXCEPT" . | grep -v "DEBUG"
}

function kill_firefox() {
    killall firefox
}

main
