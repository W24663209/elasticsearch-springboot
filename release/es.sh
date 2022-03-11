#!/usr/bin/env bash
#git pull
#mvn clean install -Dmaven.test.skip
cert_path="release/cert/api-prod-v2.pem"
scp -i "$cert_path" target/elasticsearch-0.0.1-SNAPSHOT.jar ec2-user@3.109.174.158:/home/ec2-user/java/sbin/upload
ssh -i "$cert_path" ec2-user@3.109.174.158 "source ~/.bash_profile;cd /home/ec2-user/java/sbin;mv elasticsearch-0.0.1-SNAPSHOT.jar temp/otc100-system-$(date +"%Y%m%d%H%M%S").jar;mv upload/elasticsearch-0.0.1-SNAPSHOT.jar ."