package br.edu.fa7.projeto10;

public class Lancamento {
	
	private Long id;
	private String tipoLancamento;
	private Double value;
	private Boolean isReceita;
	
	public Lancamento(Double value, Boolean isReceita, String tipoLancamento) {
		this.value = value;
		this.isReceita = isReceita;
		this.tipoLancamento = tipoLancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getIsReceita() {
		return isReceita;
	}

	public void setIsReceita(Boolean isReceita) {
		this.isReceita = isReceita;
	}
	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
}