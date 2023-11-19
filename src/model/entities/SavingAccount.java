package model.entities;

import model.exception.AmountException;
import model.services.Transaction;

public class SavingAccount extends Account implements Income {

	public SavingAccount (){
		
	}
	
	public SavingAccount(Holder holder){
		super(holder);
	}

	@Override
	public void update(Double rate) {
		super.balance += balance * (rate/100);
		
	}

	@Override
	public void withdraw(Double amount, Transaction date) throws AmountException {
		if(super.getBalance() < amount) {
			throw new AmountException("você não tem saldo suficiente para saque.");
		}  
		if (amount < 0.0){
			throw new AmountException("você digitou quantidade de saque errado.");
		}
		
		super.balance -= amount; 
	}

	@Override
	public void deposit(Double amount, Transaction date) throws AmountException {
		if(amount > 0) {
			super.balance += amount;
		} else {
			throw new AmountException("você digitou quantidade de deposito errado.");
		}
	}
	
	
}
