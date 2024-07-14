#!/bin/bash

set -e
set -u

function create_user_and_database() {
	local database=$(echo $1 | tr ',' ' ' | awk  '{print $1}')
	local ownerName=$(echo $1 | tr ',' ' ' | awk  '{print $2}')
	local ownerPassword=$(echo $1 | tr ',' ' ' | awk  '{print $3}')
	echo "  Creating user and database '$database'"
	echo "  -e user=$ownerName"
	echo "  -e password=$ownerPassword"
	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
	    CREATE USER $ownerName;
	    CREATE DATABASE $database WITH OWNER $ownerName;
	    ALTER USER $ownerName WITH PASSWORD '$ownerPassword';
	    GRANT ALL PRIVILEGES ON DATABASE $database TO $ownerName;
EOSQL
}

if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
	echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"
	for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ':' ' '); do
		create_user_and_database $db
	done
	echo "Multiple databases created"
fi