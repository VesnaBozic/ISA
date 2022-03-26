package primer1.model;

import java.util.ArrayList;

public class Racun {
	private Korisnik korisnik;
	private String brojRacuna;
	private double stanje;
	private ArrayList<Transakcija> transakcije = new ArrayList<Transakcija>();
	
	public Racun(Korisnik korisnik, String brojRacuna, double stanje) {
		super();
		this.korisnik = korisnik;
		this.setKorisnik(korisnik);
		
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
	}
	public ArrayList<Transakcija> getTransakcije() {
		return transakcije;
	}
	public void setTransakcije(ArrayList<Transakcija> transakcije) {
		this.transakcije = transakcije;
	}
	public Racun() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		if (this.korisnik != korisnik ) {
		this.korisnik = korisnik;
		this.korisnik.dodajRacun(this);
		}
	}
	public String getBrojRacuna() {
		return brojRacuna;
	}
	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}
	public double getStanje() {
		double stanje = this.stanje;
		
		for(Transakcija t: this.transakcije) {
			if(t.getRacunPrimaoca() == this) {
				stanje += t.getIznos();
		} else {
			stanje -= t.getIznos();
		}}
		
		return stanje;
	}
	public void setStanje(double stanje) {
		this.stanje = stanje;
	}
	@Override
	public String toString() {
		return "Racun [korisnik=" + korisnik + ", brojRacuna=" + brojRacuna + ", stanje=" + getStanje() + "]";
	}
	
	public void uplati(Racun uplatilac, double iznos) {
		this.dodajTransakciju(new Transakcija(uplatilac, this, iznos));
//		uplatilac.transakcije.add(t);
		
	}
	
	public void isplati(Racun primalac, double iznos) {
		this.dodajTransakciju(new Transakcija(this, primalac, iznos));
	}
	
	public void dodajTransakciju(Transakcija t) {
		if(!this.transakcije.contains(t)) 
		{		this.transakcije.add(t);
	}
		
	}
	
	public void ukloniTransakciju(Transakcija t) {
		this.transakcije.remove(t);
	}
	}
	
	

	
	
	

