package model;

public class Cursus {

	private int id;
	private String naam;
	private String beschrijving;
	
	public Cursus(int id, String naam, String beschrijving) {
		super();
		this.id = id;
		this.naam = naam;
		this.beschrijving = beschrijving;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

}
