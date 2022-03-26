//Implementirati jednostavnu poslovnu aplikaciju i server i klijent za razmenu jednostavnih podataka.

//Implementirati poslovnu aplikaicju za upravljanje podacima u banci. Banaka upravlja podacima o korisnicima, njihovim računima, uplatama i isplatama novca.



package primer1;

import primer1.model.Korisnik;
import primer1.model.Racun;
import primer1.model.Transakcija;

public class Aplikacija {

	public static void main(String []args ) {
		
		Korisnik korisnik1 = new Korisnik(0, "Petar", "Petrovic");
		Korisnik korisnik2 = new Korisnik(1, "Marko", "Petrovic");
		
		Racun racun1 = new Racun(korisnik1, "00001", 10000);
		Racun racun2 = new Racun(korisnik2, "00002", 5000);
		
		
		Transakcija transakcija1 = new Transakcija(racun1, racun2, 1000);
		racun1.isplati(racun2, 1000);
		System.out.println(racun1);
		System.out.println(racun2);
		
		
	}
}
