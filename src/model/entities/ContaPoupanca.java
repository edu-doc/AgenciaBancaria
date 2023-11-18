package model.entities;

import model.exception.AmountException;
import model.services.HoraTransacoes;

public class ContaPoupanca extends Conta implements Rendimentos {

	public ContaPoupanca (){
		
	}
	
	public ContaPoupanca(Titular titular){
		super(titular);
	}

	@Override
	public void atualizar(Double taxaRendimento) {
		super.saldo += saldo * (taxaRendimento/100);
		
	}

	@Override
	public void sacar(Double valor, HoraTransacoes date) throws AmountException {
		if(super.getSaldo() < valor) {
			throw new AmountException("você não tem saldo suficiente para saque.");
		}  
		if (valor < 0.0){
			throw new AmountException("você digitou quantidade de saque errado.");
		}
		
		this.saldo -= valor; 
	}

	@Override
	public void depositar(Double valor, HoraTransacoes date) throws AmountException {
		if(valor > 0) {
			this.saldo += valor;
		} else {
			throw new AmountException("você digitou quantidade de deposito errado.");
		}
	}
	
	
}
