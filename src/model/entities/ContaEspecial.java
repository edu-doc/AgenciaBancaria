package model.entities;

public class ContaEspecial extends ContaCorrente implements Rendimentos{

	public ContaEspecial () {
		
	}
	
	public ContaEspecial (Titular titular) {
		super(titular);
		setLimite(5000.00);
		setTaxa(0.25);
	}
	
	@Override
	public void atualizar(Double taxaRendimento) {
		super.saldo += super.saldo * (taxaRendimento/100);
	}
	
	
}
