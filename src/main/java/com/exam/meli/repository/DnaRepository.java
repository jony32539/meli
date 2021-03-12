package com.exam.meli.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.exam.meli.entity.Dna;

public interface DnaRepository extends CrudRepository<Dna, String>
{
	@Query("SELECT COUNT(*) AS cantidad_mutantes FROM Dna d WHERE d.mutante = :idMutantes")
	public int consultarCantidadMutantes(@Param("idMutantes") boolean idMutantes);
	
	@Query("SELECT COUNT(*) AS cantidad_humanos FROM Dna d WHERE d.mutante = :idHumanos")
	public int consultarCantidadHumanos(@Param("idHumanos") boolean idHumanos);
}
