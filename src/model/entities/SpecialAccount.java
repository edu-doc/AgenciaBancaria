package model.entities;

public class SpecialAccount extends CurrentAccount implements Income{

	public SpecialAccount () {
		
	}
	
	public SpecialAccount (Holder holder) {
		super(holder);
		setLimit(5000.00);
		setFee(0.25);
	}
	
	@Override
	public void update(Double rate) {
		super.balance += super.balance * (rate/100);
	}
	
	
}
