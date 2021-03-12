package com.exam.meli.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dna
{
	@Id
	private String dna;
	private boolean mutante;

	public Dna(){}

	public Dna(String dna, boolean mutante)
	{
		super();
		this.dna = dna;
		this.mutante = mutante;
	}

	public String getDna()
	{
		return dna;
	}

	public void setDna(String dna)
	{
		this.dna = dna;
	}

	public boolean getMutante()
	{
		return mutante;
	}

	public void setMutante(boolean mutante)
	{
		this.mutante = mutante;
	}

}
