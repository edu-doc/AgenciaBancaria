package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.exception.AmountException;
import model.services.Transaction;

public abstract class Account implements AccountInterface {

	private static Integer numAccount = 1;
	private Integer num;
	private final String agency = "0001";
	protected Double balance = 0.0;
	
	private List<Transaction> date = new ArrayList<>();

	private Holder holder;

	public Account() {

	}

	public Account(Holder holder) {
		this.num = numAccount;
		this.holder = holder;
		numAccount += 1;
	}

	public Integer getNum() {
		return num;
	}

	public void setNumero(Integer num) {
		this.num = num;
	}

	public String getAgency() {
		return agency;
	}

	public Holder getHolder() {
		return holder;
	}

	public void setHolder(Holder holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}

	public List<Transaction> getDate() {
		return date;
	}

	public abstract void withdraw(Double amount, Transaction date) throws AmountException;

	public abstract void deposit(Double amount, Transaction date) throws AmountException;

	public void transfer(Double amount, Account favored, Transaction date) throws AmountException {
		if (this.balance >= amount && favored != null) {
			this.balance -= amount;
			favored.deposit(amount, date);
		} else {
			throw new AmountException("transferencia não concluida");
		}
	}

	@Override
	public String toString() {
		return "Conta numero = " + getNum() + "\nagencia = " + getAgency() + "\nsaldo = " + String.format("%.2f",getBalance()) + "\n" + getHolder();
	}
	
	
	
	
	
	
}
