package rs.ac.singidunum.novisad.primer2.model;

import java.util.ArrayList;

public class Racun {
	private Korisnik korisnik;
	private String brojRacuna;
	private double stanje;
	private ArrayList<Transakcija> transakcije = new ArrayList<Transakcija>();
	
	public Racun() {
		super();
	}
	
	public Racun(Korisnik korisnik, String brojRacuna, double stanje) {
		super();
		this.setKorisnik(korisnik);
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public double getStanje() {
		double stanje = this.stanje;
		
		for(Transakcija t : this.transakcije) {
			if(t.getRacunPrimaoca() == this) {
				stanje += t.getIznos();
			} else {
				stanje -= t.getIznos();
			}
		}
		
		return stanje;
	}

	public void setStanje(double stanje) {
		this.stanje = stanje;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		if(this.korisnik != korisnik) {
			this.korisnik = korisnik;
			this.korisnik.dodajRacun(this);
		}
	}
	
	public void uplati(Racun uplatilac, double iznos) {
		this.dodajTransakciju(new Transakcija(uplatilac, this, iznos));
	}
	
	public void isplati(Racun primalac, double iznos) {
		this.dodajTransakciju(new Transakcija(this, primalac, iznos));
	}
	
	public void dodajTransakciju(Transakcija t) {
		if(!this.transakcije.contains(t)) {
			this.transakcije.add(t);
		}
	}
	
	public void ukloniTransakciju(Transakcija t) {
		this.transakcije.remove(t);
	}

	@Override
	public String toString() {
		return "Racun [korisnik=" + korisnik + ", brojRacuna=" + brojRacuna + ", stanje=" + getStanje() + "]";
	}
}
