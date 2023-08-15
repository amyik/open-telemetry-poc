## Deploy Private Registry on Localhost
docker run -d -p 5000:5000 --restart=always --name registry registry:2
https://docs.docker.com/registry/deploying/