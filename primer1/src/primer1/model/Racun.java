package primer1.model;

public class Racun {
	private Korisnik korisnik;
	private String brojRacuna;
	private double stanje;
	public Racun(Korisnik korisnik, String brojRacuna, double stanje) {
		super();
		this.korisnik = korisnik;
		this.setKorisnik(korisnik);
		
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
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
		return stanje;
	}
	public void setStanje(double stanje) {
		this.stanje = stanje;
	}

	
	
	
}
