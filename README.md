# WaterPipelineSystems
## Requirements:
	- JDK version 11+
	- DataBase - H2
	- CLI application

## Dependecies:
	- h2-1.4.200
	- opencsv-3.9

## Algoritm 
First of all the application data from those two files will be upload to the H2 database
In the application was used Dijkstra’s algorithm that`s looks like:
	*The pipeline system will be initialized with a graph
	*For every start vertex calculates lengths to another vertex.
	*If an edge exists between the specified vertices, the program writes to the output file "TRUE" and calculates the minimum distance between them
	*If an edge don`t exists, the program write to the output file "FALSE"

## Task
[Task of Application](https://github.com/YuraSen/WaterPipelineSystems/blob/master/Test%20assignment%20for%20Java%20internship%20at%20DB%20Best%5B1825%5D.pdf)
