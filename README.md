# LittleDemoApp

This is a demo which uses Spring-Boot, React and Docker to implement a microservice REST api web application.

# Build
First build the app, studentController and courseController by running:

    mvn clean package -DskipTests

on the app directory and running:

    mvn clean package

on the other two directories.

Then to build the frontend, run:

    npm install

on the frontend directory.

# Run
On the root directory run:

    docker-compose up --build

to build and run all the docker images.

Then, cross your fingers and connect to http://localhost:3001/ in your borwser. 

ta daa...


If you are getting an error about wrong volume spesification, try:

    docker-compose down -v

and 

    docker-compose up --build

again.

Note that this project was only tested on Ubuntu.
    