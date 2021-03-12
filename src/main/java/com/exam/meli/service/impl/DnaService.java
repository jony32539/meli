package com.exam.meli.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.meli.MutanteException;
import com.exam.meli.dto.StatsDTO;
import com.exam.meli.entity.Dna;
import com.exam.meli.repository.DnaRepository;
import com.exam.meli.service.IDnaService;

@Service
public class DnaService implements IDnaService
{
	@Autowired
	private DnaRepository dnaRepository;

	private static String PATRON_CADENA = "[ATCG]+";
	private String[] dna;
	private String[] dnaT;
	private String[][] matrizDna;
	private String[][] matrizT;
	private int tamano;
	private int cantidadSecuencias = 0;
	private int totalSecuencias = 0;

	@Override
	public boolean isMutant(String[] dna) throws Exception
	{
		this.dna = dna;
		init();
		return validarMutante();
	}

	private void init() throws MutanteException
	{
		validarDna();
		calularTamanoDna();
		this.matrizDna = new String[this.tamano][this.tamano];
		this.matrizT = new String[this.tamano][this.tamano];
		this.dnaT = new String[this.tamano];
	}

	private void calcularSecuencias() throws MutanteException
	{
		init();
		llenarMatriz();
		buscarSecuenciasOblicuas();
		buscarHorizontales();
		buscarVerticales();
	}

	private boolean validarMutante() throws MutanteException
	{
		boolean esMuntante = false;
		calcularSecuencias();
		if (totalSecuencias > 1)
		{
			esMuntante = true;
		} else
		{
			esMuntante = false;
		}
		crearEstadistica(esMuntante);
		return esMuntante;
	}

	private void sumarSecuencias(int cantidadDeSecuencias)
	{
		this.totalSecuencias = this.totalSecuencias + cantidadDeSecuencias;
	}

	public void pasarMatriaTranspuestaAArray()
	{
		for (int i = 0; i < tamano; i++)
		{
			StringBuilder letras = new StringBuilder();
			for (int j = 0; j < tamano; j++)
			{
				letras.append(matrizT[i][j]);
			}
			this.dnaT[i] = letras.toString();
		}
	}

	private void calularTamanoDna()
	{
		this.tamano = dna.length;
	}

	private void buscarVerticales()
	{
		transponerMatriz();
		pasarMatriaTranspuestaAArray();

		int cantidadIdentificadas = 0;
		int contadorFilas = 0;
		while (contadorFilas < dna.length)
		{
			cantidadIdentificadas = cantidadIdentificadas + buscarSecuencias(obtenerLetras(dnaT[contadorFilas]), 0);
			contadorFilas++;
		}
		sumarSecuencias(cantidadIdentificadas);

	}

	private void transponerMatriz()
	{
		for (int x = 0; x < matrizDna.length; x++)
		{
			for (int y = 0; y < matrizDna[x].length; y++)
			{
				matrizT[y][x] = matrizDna[x][y];
			}
		}
	}

	private void buscarHorizontales()
	{
		int cantidadIdentificadas = 0;
		int contadorFilas = 0;
		while (contadorFilas < dna.length)
		{
			cantidadIdentificadas = cantidadIdentificadas + buscarSecuencias(obtenerLetras(dna[contadorFilas]), 0);
			contadorFilas++;
		}
		sumarSecuencias(cantidadIdentificadas);
	}

	private int buscarSecuencias(char[] arreglo, int indiceBusqueda)
	{
		int secuencias = 0;
		int contadorLetras = 1;
		int contadorwhile = 1;
		while (contadorwhile != 4)
		{
			if (indiceBusqueda < this.tamano - 1)
			{
				if (arreglo[indiceBusqueda] == arreglo[indiceBusqueda + 1])
				{
					contadorLetras++;
					indiceBusqueda++;

				} else
				{
					secuencias = secuencias + buscarSecuencias(arreglo, indiceBusqueda + 1);
				}
			}
			contadorwhile++;
		}

		if (contadorLetras == 4)
		{
			secuencias++;
			if (indiceBusqueda < this.tamano - 1)
			{
				secuencias = secuencias + buscarSecuencias(arreglo, indiceBusqueda + 1);
			}
		} else if (indiceBusqueda < this.tamano - 1)
		{
			secuencias = +buscarSecuencias(arreglo, indiceBusqueda + 1);
		}
		return secuencias;
	}

	private void buscarSecuenciasOblicuas()
	{
		for (int i = 0; i < tamano; i++)
		{
			for (int j = 0; j < tamano; j++)
			{
				boolean oblicuaAbajoDerecha = buscarOblicuaAbajoDerecha(matrizDna[i][j], i, j, 1);
				if (oblicuaAbajoDerecha)
				{
					cantidadSecuencias++;
				}
				boolean oblicuaAbajoIzquierda = buscarOblicuaAbajoIzquierda(matrizDna[i][j], i, j, 1);
				if (oblicuaAbajoIzquierda)
				{
					cantidadSecuencias++;
				}
			}
		}
		sumarSecuencias(cantidadSecuencias);
	}

	private boolean buscarOblicuaAbajoDerecha(String letra, int indiceX, int indiceY, int contadorDeLetra)
	{
		int siguienteIndiceX = indiceX + 1;
		int siguienteIndiceY = indiceY + 1;
		boolean retorno = false;

		if (!(contadorDeLetra == 4))
		{
			if (siguienteIndiceX < tamano && siguienteIndiceY < tamano)
			{
				if (matrizDna[siguienteIndiceX][siguienteIndiceY].equalsIgnoreCase(letra))
				{
					contadorDeLetra++;
					buscarOblicuaAbajoDerecha(letra, siguienteIndiceX, siguienteIndiceY, contadorDeLetra);
				} else
				{
					retorno = false;
				}
			} else
			{
				retorno = false;
			}
		} else
		{
			retorno = true;
		}

		return retorno;
	}

	private boolean buscarOblicuaAbajoIzquierda(String letra, int indiceY, int indiceX, int contadorDeLetra)
	{

		int siguienteIndiceX = indiceX - 1;
		int siguienteIndiceY = indiceY + 1;
		boolean respuesta = false;

		if (!(contadorDeLetra == 4))
		{
			if (siguienteIndiceX >= 0 && siguienteIndiceY < tamano)
			{
				if (matrizDna[siguienteIndiceY][siguienteIndiceX].equalsIgnoreCase(letra))
				{
					contadorDeLetra++;
					buscarOblicuaAbajoIzquierda(letra, siguienteIndiceY, siguienteIndiceX, contadorDeLetra);
				} else
				{
					respuesta = false;
				}
			} else
			{
				respuesta = false;
			}
		} else
		{
			respuesta = true;
		}

		return respuesta;
	}

	private void llenarMatriz()
	{
		for (int i = 0; i < this.tamano; i++)
		{
			String elementoArray = dna[i];
			obtenerLetras(elementoArray);
			char[] letras = obtenerLetras(elementoArray);
			for (int j = 0; j < this.tamano; j++)
			{
				matrizDna[i][j] = Character.toString(letras[j]);
			}
		}
	}

	private char[] obtenerLetras(String elementoArray)
	{
		return elementoArray.toCharArray();
	}

	private void crearEstadistica(boolean esMutante)
	{

		Dna dnaEntity = new Dna(obtenerDnaCompleto(), esMutante);
		Optional<Dna> entityResponse = dnaRepository.findById(dnaEntity.getDna());
		if (!(entityResponse.isPresent()))
		{
			dnaRepository.save(dnaEntity);
		}
	}

	private String obtenerDnaCompleto()
	{
		return Arrays.stream(dna).collect(Collectors.joining());
	}

	private void validarDna() throws MutanteException
	{
		if (this.dna.length == 0)
		{
			throw new MutanteException("La cadena dna no puede ser vacia");
		}
		if (this.dna.length != dna[0].length())
		{
			throw new MutanteException("La cadena no es tamano NxN");
		}
		Pattern patternDna = Pattern.compile(PATRON_CADENA, Pattern.CASE_INSENSITIVE);
		if (!patternDna.matcher(Arrays.toString(this.dna).replaceAll("[\\[\\], ]", "")).matches())
		{
			throw new MutanteException("La cadena dna no es valida");
		}

	}

	public StatsDTO consultarEstadistics()
	{
		int cantidadHumanos = dnaRepository.consultarCantidadHumanos(false);
		int cantidadMutantes = dnaRepository.consultarCantidadMutantes(true);
		StatsDTO statsDTO = new StatsDTO(cantidadMutantes, cantidadHumanos,
				calcularRatio(cantidadMutantes, cantidadHumanos));
		return statsDTO;
	}

	public double calcularRatio(int mutantes, int humanos)
	{
		BigDecimal ratio = new BigDecimal(mutantes).divide(new BigDecimal(humanos), 2, RoundingMode.HALF_UP);
		return ratio.doubleValue();
	}

}
