#Setup redis container
docker build -t cfm/db:latest . -f redis_dockerfile

#Initialize and start redis server at port 6379
docker run -it -p 6379:6379 cfm/db:latest


#Addressbook is developed for for tomcat
#Use runMe.sh to compile, build and deploy the war file on to tomcat server.

 
 

