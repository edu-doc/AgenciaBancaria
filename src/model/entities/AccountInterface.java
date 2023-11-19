package model.entities;

import model.exception.AmountException;
import model.services.Transaction;

public interface AccountInterface {

	public void withdraw(Double amount, Transaction date) throws AmountException;
	
	public void deposit(Double amount, Transaction date) throws AmountException;
	
	public void transfer(Double amount, Account favored, Transaction date) throws AmountException;
}
