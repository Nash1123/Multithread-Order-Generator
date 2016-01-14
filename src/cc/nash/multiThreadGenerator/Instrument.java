package cc.nash.multiThreadGenerator;

public class Instrument {
	
	String symbol;
	String name;
	int id;

	public Instrument(String symbol, String name, int id) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
