package entitites;

public class Client {

	private String name;
	private String cpf;
	private String email;
	private static int id = 1;
	
	public Client(String name, String cpf, String email) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		id += 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Client.id = id;
	}

	@Override
	public String toString() {
		return "Nome = " + name + ", CPF = " + cpf + ", E-mail = " + email;
	}
	
	
	
	
	
}
