package model.entities;

import model.exception.AmountException;
import model.services.Transaction;

public class CurrentAccount extends Account {

	private Double limit = 1000.00;
	private Double fee = 0.50;

	public CurrentAccount() {

	}

	public CurrentAccount(Holder holder) {
		super(holder);
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getBalanceWithLimit() {
		return super.balance + getLimit();
	}

	@Override
	public void withdraw(Double amount, Transaction date) throws AmountException {

		if (getBalanceWithLimit() < amount) {
			throw new AmountException("você não tem saldo com limite suficiente para saque.");
		}

		if (amount < 0.0) {
			throw new AmountException("você digitou quantidade de saque errado.");
		}

		super.balance -= (amount + this.fee);

	}

	@Override
	public void deposit(Double amount, Transaction date) throws AmountException {
		if (amount > 0) {
			super.balance += amount - this.fee;
		} else {
			throw new AmountException("você digitou quantidade de deposito errado.");
		}
	}

}
