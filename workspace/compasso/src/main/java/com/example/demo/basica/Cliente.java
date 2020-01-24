package com.example.demo.basica;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.sexo.Sexo;

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
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "sexo", length = 100, nullable = true)
	private Sexo sexo;
	
	@Column(name = "data_nascimento", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name = "idade", nullable = true)
	private int idade ;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cliente_cidade", joinColumns = { 
			@JoinColumn(name = "id_cliente", nullable = false, updatable = true, insertable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_cidade", 
					nullable = false, updatable = true, insertable = true) })
	private Set<Cidade> cidades = new HashSet<>();
	
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}
}
