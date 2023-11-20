package program;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import model.entities.Account;
import model.entities.CurrentAccount;
import model.entities.Holder;
import model.entities.SavingAccount;
import model.entities.SpecialAccount;
import model.entities.enu.TransactionType;
import model.exception.AmountException;
import model.services.Transaction;

public class ProgramAccount {
	static List<Account> account = new ArrayList<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		menu();
	}

	public static void menu() throws IOException {

		Locale.setDefault(Locale.US);

		int op = Integer.parseInt(JOptionPane.showInputDialog("-----Selecione uma operação-----\n"
				+ "OP 1 - CRIAR CONTA\n" + "OP 2 - DEPOSITAR\n" + "OP 3 - SACAR\n" + "OP 4 - TRANSFERIR\n"
				+ "OP 5 - LISTAR CONTAS\n" + "OP 6 - LISTAR MOVIMENTAÇÕES\n" + "OP 7 - SAIR"));

		switch (op) {
		case 1:
			createAccount();
			break;
		case 2:
			deposit();
			break;
		case 3:
			withdraw();
			break;
		case 4:
			transfer();
			break;
		case 5:
			listAccount();
			break;
		case 6:
			listAccountMoviment();
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "Obrigado e tenha um bom dia!");
			System.exit(0);
		default:
			JOptionPane.showMessageDialog(null, "Opção errada, digite um número relacionado a opção que queira");
			menu();
			break;
		}
	}

	static void createAccount() throws IOException {
		
		String name = JOptionPane.showInputDialog("Digite o nome do cliente: ");

		String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente: ");

		String email = JOptionPane.showInputDialog("Digite o Email do cliente: ");

		Holder holder = new Holder(name, email, cpf);
		
		char type = JOptionPane.showInputDialog("Digite o tipo de conta:\n"
				+ "Corrente - c\n"
				+ "Poupança - p\n"
				+ "Especial - e").charAt(0);
		
		if(type == 'c') {
			CurrentAccount currentAccount = new CurrentAccount(holder);
			account.add(currentAccount);
		} else if (type == 'p') {
			SavingAccount savingAccount = new SavingAccount(holder);
			account.add(savingAccount);
		} else {
			SpecialAccount specialAccount = new SpecialAccount(holder);
			account.add(specialAccount);
		}

		JOptionPane.showMessageDialog(null, "Conta criada com sucesso.");

		menu();

	}

	static Account hasAccount(int idConta) {
		Account conta = null;
		if (account.size() > 0) {

			for (Account c : account) {
				if (c.getNum() == idConta) {
					conta = c;
				}
			}
		}
		return conta;

	}

	static void deposit() throws IOException {

		int numAccount = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numAccount);

		try {
			if (conta != null) {
				double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer depositar: "));
				Transaction ht;
				conta.deposit(amount, ht = new Transaction(LocalDate.now(), TransactionType.valueOf("DEPOSITO")));
				conta.getDate().add(ht);
				JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso.");

			}
		} catch (AmountException e) {
			JOptionPane.showMessageDialog(null, "Erro no deposito: " + e);
		}

		menu();

	}

	static void withdraw() throws IOException {

		int numAccount = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numAccount);

		try {
			if (conta != null) {
				double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer sacar: "));
				Transaction ht;
				conta.withdraw(amount, ht = new Transaction(LocalDate.now(), TransactionType.valueOf("SAQUE")));
				conta.getDate().add(ht);
				JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
			}
		} catch (AmountException e) {
			JOptionPane.showMessageDialog(null, "Erro no saque: " + e);
		}

		menu();
	}

	static void transfer() throws IOException {

		int numAccount = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numAccount);

		try {
			if (conta != null) {

				double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer transferir: "));

				int numTransfer = Integer
						.parseInt(JOptionPane.showInputDialog("Qual número da conta que irá receber transferência: "));

				Account accountReceive = hasAccount(numTransfer);

				if (accountReceive != null) {
					if (conta.getBalance() >= amount) {
						Transaction ht;
						conta.transfer(amount, accountReceive,
								ht = new Transaction(LocalDate.now(), TransactionType.valueOf("TRANSFERENCIA")));
						conta.getDate().add(ht);
						JOptionPane.showMessageDialog(null, "Tansferência realizado com sucesso.");
					}

				}
			}
		} catch (AmountException e) {
			JOptionPane.showMessageDialog(null, "Erro na transferencia: " + e);
		}

		menu();

	}

	static void listAccount() throws IOException {
		if (account.size() > 0) {
			for (Account c : account) {
				JOptionPane.showMessageDialog(null, c);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não existe contas cadastradas");
		}
		menu();
	}

	static void listAccountMoviment() throws IOException {

		int numAccount = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numAccount);

		if (conta != null) {
			if (conta.getDate().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Não foi feita movimentações");
			} else {
					JOptionPane.showMessageDialog(null, conta.getDate());
			}

		} else {
			JOptionPane.showMessageDialog(null, "Essa conta não existe.");
		}
		menu();
	}

}