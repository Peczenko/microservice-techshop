#!/bin/bash

if [ ! -f /opt/keycloak/data/import/.imported ]; then
  /opt/keycloak/bin/kc.sh start-dev --import-realm --features=scripts
  touch /opt/keycloak/data/import/.imported
else
  /opt/keycloak/bin/kc.sh start-dev --features=scripts
fi
