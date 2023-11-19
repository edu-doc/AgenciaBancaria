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
import model.entities.enu.TransactionType;
import model.exception.AmountException;
import model.services.Transaction;

public class ProgramAccount {
	static List<Account> contaBancaria = new ArrayList<>();

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

		Holder titular = new Holder(name, email, cpf);

		CurrentAccount contaCorrente = new CurrentAccount(titular);

		contaBancaria.add(contaCorrente);

		JOptionPane.showMessageDialog(null, "Conta criada com sucesso.");

		menu();

	}

	static Account hasAccount(int idConta) {
		Account conta = null;
		if (contaBancaria.size() > 0) {

			for (Account c : contaBancaria) {
				if (c.getNum() == idConta) {
					conta = c;
				}
			}
		}
		return conta;

	}

	static void deposit() throws IOException {

		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numConta);

		try {
			if (conta != null) {
				double deposito = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer depositar: "));
				Transaction ht;
				conta.deposit(deposito, ht = new Transaction(LocalDate.now(), TransactionType.valueOf("DEPOSITO")));
				conta.getDate().add(ht);
				JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso.");

			}
		} catch (AmountException e) {
			JOptionPane.showMessageDialog(null, "Erro no deposito: " + e);
		}

		menu();

	}

	static void withdraw() throws IOException {

		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numConta);

		try {
			if (conta != null) {
				double retirar = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer sacar: "));
				Transaction ht;
				conta.withdraw(retirar, ht = new Transaction(LocalDate.now(), TransactionType.valueOf("SAQUE")));
				conta.getDate().add(ht);
				JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
			}
		} catch (AmountException e) {
			JOptionPane.showMessageDialog(null, "Erro no saque: " + e);
		}

		menu();
	}

	static void transfer() throws IOException {

		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numConta);

		try {
			if (conta != null) {

				double transferir = Double.parseDouble(JOptionPane.showInputDialog("Qual valor quer transferir: "));

				int numTransferir = Integer
						.parseInt(JOptionPane.showInputDialog("Qual número da conta que irá receber transferência: "));

				Account contaReceber = hasAccount(numTransferir);

				Account contaTransferir = hasAccount(numTransferir);
				if (contaTransferir != null) {
					if (conta.getBalance() >= transferir) {
						Transaction ht;
						conta.transfer(transferir, contaReceber,
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
		if (contaBancaria.size() > 0) {
			for (Account c : contaBancaria) {
				JOptionPane.showMessageDialog(null, c);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não existe contas cadastradas");
		}
		menu();
	}

	static void listAccountMoviment() throws IOException {

		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));

		Account conta = hasAccount(numConta);

		if (conta != null) {
			if (conta.getDate().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Não foi feita movimentações");
			} else {
				for (Account c : contaBancaria) {
					JOptionPane.showMessageDialog(null, c.getDate());
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Essa conta não existe.");
		}
		menu();
	}

}