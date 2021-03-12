# MUTANT- MERCADO LIBRE
Examen Mercado Libre
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. 

##Requerimientos

* JDK 8
* SpringBoot 2.4.3
* MariaDB
* Maven 3.6.3
* aws beanstalk
* aws RDS

##Instrucciones de Ejecucion Local

1. Realizar un clone del repositorio ejecutando el comando Git git clone https://github.com/jony32539/meli.git
2. Sobre la ruta del proyecto \Meli\target ejecutar el comando java -jar Meli-0.0.1-SNAPSHOT
3. Con una herramienta de test para Servicios web como Soap-UI probar los endPoint http://localhost:8080/stats y http://localhost:8080/mutant

##Instrucciones de Ejecucion sobre AWS

1. Los endPoint para probar sobre AWS son los siguientes:
	*
	*

2. Para el caso de validacion de un Humano como Mutante enviar el siguinete objeto Json
	{
		"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
	}
	

