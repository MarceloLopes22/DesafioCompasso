package com.example.demo.basica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente", nullable = false, precision = 1, unique = true)
	private Integer id;
	
	@Column(name = "nome_completo", length = 100, nullable = true)
	private String nomeCompleto;
	
	@Column(name = "sexo", length = 100, nullable = true)
	private String sexo;
	
	@Column(name = "data_nascimento", nullable = true)
	private LocalDate dataNascimento;
	
	@Column(name = "idade", nullable = true)
	private int idade ;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cliente_cidade", joinColumns = { 
			@JoinColumn(name = "id_cliente", nullable = false, updatable = true, insertable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_cidade", 
					nullable = false, updatable = true, insertable = true) })
	private List<Cidade> cidades;
	
	public Cliente() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}