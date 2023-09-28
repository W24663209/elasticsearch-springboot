#!/usr/bin/env bash
FILE_NAME=/Users/mac01/IdeaProjects/elasticsearch-springboot/target/elasticsearch-0.0.1-SNAPSHOT.jar
scp -i /Users/mac01/OtPay/jpay-server/cers/kingpay.pem $FILE_NAME  ubuntu@3.110.181.58:/home/ubuntu/es-api