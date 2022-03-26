package primer1.model;

public class Transakcija {
	
	private Racun racunUplatioca;
	private Racun racunPrimaoca;
	private double iznos;
	public Transakcija() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transakcija(Racun racunUplatioca, Racun racunPrimaoca, double iznos) {
		super();
		this.setRacunPrimaoca(racunPrimaoca);
		this.setRacunUplatioca(racunUplatioca);

		this.iznos = iznos;
	}
	public Racun getRacunUplatioca() {
		return racunUplatioca;
	}
	public void setRacunUplatioca(Racun racunUplatioca) {
		if (this.racunUplatioca != racunUplatioca ) {
			
		if (this.racunUplatioca != null) {
			this.racunUplatioca.ukloniTransakciju(this);}
			this.racunUplatioca= racunUplatioca;
			this.racunUplatioca.dodajTransakciju(this);
			}
	}
	public Racun getRacunPrimaoca() {
		return racunPrimaoca;
	}
	public void setRacunPrimaoca(Racun racunPrimaoca) {
		if (this.racunPrimaoca != racunPrimaoca ) {
			if(this.racunPrimaoca != null) {
		this.racunPrimaoca.ukloniTransakciju(this); }
		this.racunPrimaoca = racunPrimaoca;
		this.racunPrimaoca.dodajTransakciju(this);
		}
	}
	public double getIznos() {
		return iznos;
	}
	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	
	
	
	
	
	
	

}
