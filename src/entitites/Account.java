package entitites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

	private static int idAccount = 1;

	private int numAccount;
	private Client client;
	private Double balance = 0.0;
	
	private List<AccountMoviment> movements = new ArrayList<>();

	public Account(Client client) {
		this.numAccount = idAccount;
		this.client = client;
		idAccount += 1;
	}

	public int getNumAccount() {
		return numAccount;
	}

	public void setNumAccount(int numAccount) {
		this.numAccount = numAccount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Double getBalance() {
		return balance;
	}

	public Double depositBalance(Double balance) {
		if (balance > 0) {
			this.balance += balance;
			System.out.println("Deposito realizado");
			return this.balance;
		} else {
			System.out.println("Digite um valor de deposito maior que zero");
			return 0.0;
		}
	}

	public void withdrawBalance(Double balance) {
		if (getBalance() >= balance && balance > 0) {
			this.balance -= balance;
			System.out.println("Saque realizado");
		} else {
			System.out.println("Erro você não tem essa quantidade de dinheiro para retirar");
			System.out.println("Seu saldo é: " + getBalance());
		}
	}

	public void transfer (Account transferAccount, Double value) {
		if (getBalance() >= value && value >0 && transferAccount != null) {
			this.balance -= value;
			
			transferAccount.balance = transferAccount.depositBalance(value);
			System.out.println("Transferência realizada");
		} else {
			System.out.println("Erro na transferência");
		}
	}
	
	public void addAccountMoviment(AccountMoviment movement) {
		if(movement != null) {
			movements.add(movement);
		}
	}
	
	public void removeAccountMoviment(AccountMoviment movement) {
		if (movement != null) {
			movements.remove(movement);
		}
	}
	
	@Override
	public String toString() {
		return "ID da conta = " + getNumAccount() + ", " + client.toString() + ", Saldo = R$ "
				+ String.format("%.2f", getBalance());
	}

}