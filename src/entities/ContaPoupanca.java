package entities;

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
	public boolean sacar(Double valor) {
		if(getSaldo() > valor && valor > 0) {
			super.saldo -= valor;
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean depositar(Double valor) {
		if(valor > 0) {
			super.saldo += valor;
			return false;
		} else {
			return true;
		}
	}
	
	
}
