package model.services;

import java.time.LocalDate;

import model.entities.enu.TransactionType;

public class Transaction {

	private LocalDate date;
	private TransactionType type;
	
	public Transaction(LocalDate date, TransactionType type) {
		this.date = date;
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TransactionType getType() {
		return type;
	}

	public void setTipo(TransactionType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return getType() + " - " + getDate();
	}
	
	
	
}
