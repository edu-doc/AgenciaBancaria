package Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entitites.Account;
import entitites.Client;

public class ProgramAccount {
	static Scanner sc = new Scanner(System.in);
	static List<Account> accountBank = new ArrayList<>();

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {

		System.out.println("----------BEM VINDO A AGÊNCIA---------");
		System.out.println("");
		System.out.println("SELECIONE A OPERAÇÕES QUE DESEJA FAZER");
		System.out.println("");
		System.out.println("OP 1 - CRIAR CONTA");
		System.out.println("OP 2 - DEPOSITAR");
		System.out.println("OP 3 - SACAR");
		System.out.println("OP 4 - TRANSFERIR");
		System.out.println("OP 5 - LISTAR");
		System.out.println("OP 6 - SAIR");

		int op = sc.nextInt();

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
			System.out.println("Obrigado e tenha um bom dia!");
			System.exit(0);
		default:
			System.out.println("Opção errada, digite um número relacionado a opção que queira");
			menu();
			break;
		}
	}

	static void createAccount() {
		System.out.print("Digite o nome do cliente: ");
		String name = sc.next();

		System.out.print("Digite o CPF do cliente: ");
		String cpf = sc.next();

		System.out.print("Digite o Email do cliente: ");
		String email = sc.next();

		Client client = new Client(name, cpf, email);

		Account account = new Account(client);

		accountBank.add(account);

		menu();

	}

	static Account hasAccount(int idAccount) {
		Account conta = null;
		if (accountBank.size() > 0) {

			for (Account c : accountBank) {
				if (c.getNumAccount() == idAccount) {
					conta = c;
				}
			}
		}
		return conta;

	}

	static void deposit() {
		System.out.print("Digite o número da conta: ");
		int numAccount = sc.nextInt();

		Account account = hasAccount(numAccount);

		if (account != null) {
			System.out.print("Qual valor quer depositar: ");
			double deposit = sc.nextDouble();
			account.depositBalance(deposit);
		} else {
			System.out.println("Conta não encontrada");
		}

		menu();

	}

	static void withdraw() {
		System.out.print("Digite o número da conta: ");
		int numAccount = sc.nextInt();

		Account account = hasAccount(numAccount);

		if (account != null) {
			System.out.print("Qual valor quer retirar: ");
			double withdraw = sc.nextDouble();
			account.withdrawBalance(withdraw);
		} else {
			System.out.println("Conta não encontrada");
		}

		menu();
	}

	static void transfer() {
		System.out.print("Digite o número da conta remetente: ");
		int numAccount = sc.nextInt();

		Account account = hasAccount(numAccount);

		if (account != null) {
			System.out.print("Qual valor quer transferir: ");
			double transfer = sc.nextDouble();
			System.out.print("Qual número da conta que irá receber transferência: ");
			int numTransfer = sc.nextInt();

			Account accountTransfer = hasAccount(numTransfer);
			if (accountTransfer != null) {
				if (account.getBalance() >= transfer) {
					account.transfer(accountTransfer, transfer);
				}
				
			}

		} else {
			System.out.println("Conta não encontrada");
		}

		menu();
	}

	static void listAccount() {
		if (accountBank.size() > 0) {
			for (Account c : accountBank) {
				System.out.println(c);
			}
		} else {
			System.out.println("Não existe contas cadastradas");
		}
		menu();
	}

}
