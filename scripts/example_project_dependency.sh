#!/usr/bin/env bash
apt update
apt install -y python3 python3-pip
python3 --version
pip3 --version
pip3 install pytest
pytest --version


apt install -y maven
mvn -version
