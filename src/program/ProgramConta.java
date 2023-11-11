package program;

import entities.ContaCorrente;
import entities.Titular;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Conta;

public class ProgramConta {
	static Scanner sc = new Scanner(System.in);
	static List<Conta> contaBancaria = new ArrayList<>();

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

		Titular titular = new Titular(name, email, cpf);

		ContaCorrente contaCorrente = new ContaCorrente(titular);

		contaBancaria.add(contaCorrente);

		menu();

	}

	static Conta hasAccount(int idConta) {
		Conta conta = null;
		if (contaBancaria.size() > 0) {

			for (Conta c : contaBancaria) {
				if (c.getNumero() == idConta) {
					conta = c;
				}
			}
		}
		return conta;

	}

	static void deposit() {
		System.out.print("Digite o número da conta: ");
		int numConta = sc.nextInt();

		Conta conta = hasAccount(numConta);

		if (conta != null) {
			System.out.print("Qual valor quer depositar: ");
			double deposito = sc.nextDouble();
			conta.depositar(deposito);
		} else {
			System.out.println("Conta não encontrada");
		}

		menu();

	}

	static void withdraw() {
		System.out.print("Digite o número da conta: ");
		int numConta = sc.nextInt();

		Conta conta = hasAccount(numConta);

		if (conta != null) {
			System.out.print("Qual valor quer retirar: ");
			double retirar = sc.nextDouble();
			conta.sacar(retirar);
		} else {
			System.out.println("Conta não encontrada");
		}

		menu();
	}

	static void transfer() {
		System.out.print("Digite o número da conta remetente: ");
		int numConta = sc.nextInt();

		Conta conta = hasAccount(numConta);

		if (conta != null) {
			System.out.print("Qual valor quer transferir: ");
			double transferir = sc.nextDouble();
			System.out.print("Qual número da conta que irá receber transferência: ");
			int numTransferir = sc.nextInt();
			
			Conta contaReceber = hasAccount(numTransferir);

			Conta contaTransferir = hasAccount(numTransferir);
			if (contaTransferir != null) {
				if (conta.getSaldo() >= transferir) {
					conta.transferir(transferir, contaReceber);
				}
				
			}

		} else {
			System.out.println("Conta não encontrada");
		}

		menu();
	}

	static void listAccount() {
		if (contaBancaria.size() > 0) {
			for (Conta c : contaBancaria) {
				System.out.println(c);
			}
		} else {
			System.out.println("Não existe contas cadastradas");
		}
		menu();
	}

}