package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.exception.AmountException;
import model.services.HoraTransacoes;

public abstract class Conta {

	private static Integer numAccount = 1;
	private Integer numero;
	private final String agencia = "0001";
	protected Double saldo = 0.0;
	
	private List<HoraTransacoes> date = new ArrayList<>();

	private Titular titular;

	public Conta() {

	}

	public Conta(Titular titular) {
		this.numero = numAccount;
		this.titular = titular;
		numAccount += 1;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public List<HoraTransacoes> getDate() {
		return date;
	}

	public abstract void sacar(Double valor, HoraTransacoes date) throws AmountException;

	public abstract void depositar(Double valor, HoraTransacoes date) throws AmountException;

	public void transferir(Double valor, Conta favorecido, HoraTransacoes date) throws AmountException {
		if (this.saldo >= valor && favorecido != null) {
			this.saldo -= valor;
			favorecido.depositar(valor, date);
		} else {
			throw new AmountException("transferencia não concluida");
		}
	}

	@Override
	public String toString() {
		return "Conta [numero=" + getNumero() + ", agencia=" + getAgencia() + ", saldo=" + String.format("%.2f",getSaldo()) + ", titular=" + getTitular() +"]";
	}
	
	
	
	
	
	
}
