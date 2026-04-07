# Ekşi Sözlük Clone - Backend

## Run (Docker)

    docker compose up -d backend

### Populate Database

Wait until the database up, then

    docker exec -i postgres psql -U testuser_eksi -d testdb_eksi -f /sample_data.sql > /dev/null
