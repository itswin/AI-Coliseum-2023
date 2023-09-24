#!/usr/bin/env bash
wget https://www.coliseum.ai/api/tournaments/aic2023/download/scaffold -O scaffold.zip
unzip scaffold.zip
rm -rf jars
mv AIC2023/jars jars
rm -rf AIC2023
rm scaffold.zip
