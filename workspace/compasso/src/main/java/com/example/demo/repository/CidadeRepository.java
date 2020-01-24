package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.basica.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	Cidade findCidadeByNome(String nome);
	
	//@Query(value = "select c from Cidade c where c.estado =:estado group by c.id,c.estado") @Param(value = "estado")
	List<Cidade> findByEstado(String estado);
}
