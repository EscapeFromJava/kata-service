# kata-service

docker run --name auth-service -p 5430:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_DB=auth-service library/postgres<br>
docker run --name profile-service -p 5431:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_DB=profile-service library/postgres
