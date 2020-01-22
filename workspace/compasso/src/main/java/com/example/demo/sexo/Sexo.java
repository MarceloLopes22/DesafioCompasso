package com.example.demo.sexo;

public enum Sexo {

	MASCULINO(1,"Masculino"),
	FEMININO(2,"Feminino");
	
	private int chave;
	private String valor;
	
	private Sexo(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
