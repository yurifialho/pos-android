package br.edu.fa7.projetofinal;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer tipo;
	private Integer quantidade;
	private Double valorUnitario;
	
	public Produto(Integer id, Integer tipo, Integer quantidade, Double valor) {
		this(tipo, quantidade, valor);
		this.id = id;
	}
	
	public Produto(Integer tipo, Integer quantidade, Double valor) {
		this.tipo          = tipo;
		this.quantidade    = quantidade;
		this.valorUnitario = valor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
}
