package com.exam.meli.dto;

public class StatsDTO
{
	private int countMutantDna;
	private int countHumanDna;
	private double ratio;
	
	
	public StatsDTO()
	{
		super();
	}
	public StatsDTO(int countMutantDna, int countHumanDna, double ratio)
	{
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}
	public int getCountMutantDna()
	{
		return countMutantDna;
	}
	public void setCountMutantDna(int countMutantDna)
	{
		this.countMutantDna = countMutantDna;
	}
	public int getCountHumanDna()
	{
		return countHumanDna;
	}
	public void setCountHumanDna(int countHumanDna)
	{
		this.countHumanDna = countHumanDna;
	}
	public double getRatio()
	{
		return ratio;
	}
	public void setRatio(double ratio)
	{
		this.ratio = ratio;
	}
	
	
}
