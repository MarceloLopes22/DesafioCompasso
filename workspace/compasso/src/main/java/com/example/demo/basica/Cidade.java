package com.example.demo.basica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cidade", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "nome", nullable = true, length = 100)
	@NotEmpty(message = "O nome da cidade deve ser preenchida.")
	private String nome;
	
	@Column(name = "estado", nullable = true, length = 2)
	@NotEmpty(message = "O estado deve ser preenchido.")
	@Size(max = 2, message = "O estado não pode passar de 2 caracteres.")
	private String estado;
	
	public Cidade() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
