#!/bin/sh

# Deploys the storefront Docker stack

# usage: sh ./stack_deploy_local.sh

set -e

# docker swarm init

docker stack deploy -c docker-compose.yml digiservices
echo "Starting the stack: middleware...pausing for 40 seconds..."
sleep 30

docker stack deploy -c docker-compose-services.yml digiservices
echo "Starting the stack: services...pausing for 40 seconds..."
sleep 10

docker stack ls
docker stack services digiservices
docker container ls

echo "Script completed..."
echo "Services may take up to several minutes to start, fully..."