package br.edu.fa7.projetofinal;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Integer quantidade;
	private Double valorUnitario;
	
	public Produto() {
		
	}
	
	public Produto(Integer id, String descricao, Integer quantidade, Double valor) {
		this(descricao, quantidade, valor);
		this.id = id;
	}
	
	public Produto(String descricao, Integer quantidade, Double valor) {
		this.descricao     = descricao;
		this.quantidade    = quantidade;
		this.valorUnitario = valor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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