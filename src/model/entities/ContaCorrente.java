package model.entities;

import model.exception.AmountException;
import model.services.HoraTransacoes;

public class ContaCorrente extends Conta {
	
	private Double limite = 1000.00;
	private Double taxa = 0.50;
	
	public ContaCorrente() {
		
	}
	
	

	public ContaCorrente(Titular titular) {
		super(titular);
	}


	public Double getLimite() {
		return limite;
	}


	public void setLimite(Double limite) {
		this.limite = limite;
	}


	public Double getTaxa() {
		return taxa;
	}


	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
	public Double getSaldoComLimite() {
		return super.saldo + getLimite();
	}

	@Override
	public void sacar(Double valor, HoraTransacoes date) throws AmountException {
		
		if(getSaldoComLimite() < valor) {
			throw new AmountException("você não tem saldo com limite suficiente para saque.");
		}  
		
		if (valor < 0.0){
			throw new AmountException("você digitou quantidade de saque errado.");
		}
		
		this.saldo -= (valor + this.taxa); 
		
	}

	@Override
	public void depositar(Double valor, HoraTransacoes date) throws AmountException {
		if(valor > 0) {
			this.saldo += valor - this.taxa;
		} else {
			throw new AmountException("você digitou quantidade de deposito errado.");
		}
	}

	
	
}
