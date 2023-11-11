package entities;

public abstract class Conta {

	private static Integer numAccount = 1;
	private Integer numero;
	private final String agencia = "0001";
	protected Double saldo = 0.0;

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

	public abstract boolean sacar(Double valor);

	public abstract boolean depositar(Double valor);

	public boolean transferir(Double valor, Conta favorecido) {
		if (this.saldo >= valor && favorecido != null) {
			this.saldo -= valor;
			return favorecido.depositar(valor);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Conta [numero=" + getNumero() + ", agencia=" + getAgencia() + ", saldo=" + String.format("%.2f",getSaldo()) + ", titular=" + getTitular() + "]";
	}
	
	
	
	
	
}
