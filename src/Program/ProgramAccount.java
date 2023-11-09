package Program;

import entitites.Account;
import entitites.Client;

public class ProgramAccount {

	public static void main(String[] args) {
		
		Client c1 = new Client("Eduardo", "438243824", "eduardo@gmail.com");
		
		Account ac1 = new Account(c1);
		
		Account ac2 = new Account(c1);
		
		System.out.println(ac1.toString());
		
		ac1.addBalance(3000.00);
		
		System.out.println(ac1.toString());
		
		ac1.transfer(ac2, 1000.00);
		
		System.out.println(ac1.toString());
		
		System.out.println(ac2.toString());

	}

}
