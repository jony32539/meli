# MUTANT- MERCADO LIBRE
Examen Mercado Libre
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. 

## Requerimientos

* Java 8
* SpringBoot 2.4.3
* MariaDB
* Maven 3.6.3
* AWS beanstalk
* AWS RDS

## Instrucciones de Ejecucion Local

1. Realizar un clone del repositorio ejecutando el comando Git git clone https://github.com/jony32539/meli.git
2. Sobre la ruta del proyecto \Meli\target ejecutar el comando java -jar Meli-0.0.1-SNAPSHOT
3. Con Soap-UI o Postman probar los endPoint http://localhost:8080/stats y http://localhost:8080/mutant

## Instrucciones de Ejecucion sobre AWS

1. Los endPoint para probar sobre AWS son los siguientes:
* http://meli-env-1.eba-2fmuem3r.us-west-2.elasticbeanstalk.com:8080/stats
* http://meli-env-1.eba-2fmuem3r.us-west-2.elasticbeanstalk.com:8080/mutant

2. Para el caso de validacion de un Humano como Mutante enviar el siguinete objeto Json
	{
		"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
	}
	

