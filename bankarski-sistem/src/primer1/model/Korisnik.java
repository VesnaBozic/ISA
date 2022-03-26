package primer1.model;

import java.util.ArrayList;

public class Korisnik {

	private int id;
	private String ime;
	private String prezime;
	private ArrayList<Racun> racuni = new ArrayList<Racun>();
	
	
	public ArrayList<Racun> getRacuni() {
		return racuni;
	}
	public void setRacuni(ArrayList<Racun> racuni) {
		this.racuni = racuni;
	}
	public Korisnik() {
}
	public Korisnik(int id, String ime, String prezime) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getImeIPrezime() {
		return ime + " " + prezime;
	}
	
	public Racun getRacun(int indeks) {
		return this.racuni.get(indeks);
		
	}
	
	public Racun getRacun(String brojRacuna) {
		
		for (Racun r : this.racuni) {
			if (r.getBrojRacuna().equals(brojRacuna)) {
				return r;
			}
		}
		return null;
	}
	
	public void dodajRacun(Racun noviRacun) {
		if (getRacun(noviRacun.getBrojRacuna()) == null) {
		this.racuni.add(noviRacun); 
		noviRacun.setKorisnik(this);
		}
		
		
	}
	
	public void ukloniRacun (int indeks) {
		this.racuni.remove(indeks);
	}
	
	public void ukloniRacun (Racun r) {
		this.racuni.remove(r);
		
	}
	
	public void ukloniRacun (String brojRacuna) {
	  this.racuni.remove(getRacun(brojRacuna));
		
	}
	
	

}
