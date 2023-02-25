# Openweather Backend

**Welcome to Openweather Backend**

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [User Stories](#user-stories)
* [Weather API provider](#weather-api-provider)


## General info
A simple weather checking API service.
	
## Technologies
* Java 1.8
* Spring Boot 2.4.3
* MySQL 5

## Setup
### How to use
####
1.Go to application.properties and update
- mysql server credentials, url, port
- open weather api key

2.Create the database and table from script in `sql` folder  
3.Start up the server  
4.Import the script in `postman` folder to Postman app  
5.Run the API to test

### Build project
./gradlew build

### Run project
./gradlew bootRun

### Scaling the app
check folder `scaling` to get the idea.

## User stories

|Requirements|User Story|Priority|
|---|---|---|
|Search for today weather of a specific city|As an API consumer, I should be able to search for today's weather by inputting the city name.|High|
||||
|Save weather data|As an API consumer, I should be able to save weather data for retrieval|High|
||||
|Get historical weather data|As an API consumer,  I should be able to look for weather data from past periods|High
||||
|Delete historical weather data|As an API consumer,  I should be able to delete an existing weather record|Medium
||||
|Update historical weather data|As an API consumer, I should be able to update an existing weather record|Medium
||||
|Able to ensure existing function is working|As an API contributor, I should be able to make sure my contribution won’t break existing function|Low

## Weather API Provider

https://openweathermap.org/current
> Access current weather data for any location on Earth including over 200,000 cities! We collect and process weather data from different sources such as global and local weather models, satellites, radars and vast network of weather stations. Data is available in JSON, XML, or HTML format.

