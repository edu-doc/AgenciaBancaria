package model.services;

import java.time.LocalDate;

import model.entities.enu.TipoTransacao;

public class HoraTransacoes {

	private LocalDate date;
	private TipoTransacao tipo;
	
	public HoraTransacoes(LocalDate date, TipoTransacao tipo) {
		this.date = date;
		this.tipo = tipo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return date + " - " + tipo;
	}
	
	
	
}
