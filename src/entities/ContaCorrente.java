package entities;

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
	public boolean sacar(Double valor) {
		if(getSaldoComLimite() > valor && valor> 0.0) {
			saldo -= valor + taxa;
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public boolean depositar(Double valor) {
		if(valor > 0) {
			saldo += valor - taxa;
			return false;
		} else {
			return true;
		}
	}

	
	
}
