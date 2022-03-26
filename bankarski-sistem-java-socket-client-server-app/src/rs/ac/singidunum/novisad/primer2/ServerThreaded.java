package rs.ac.singidunum.novisad.primer2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import rs.ac.singidunum.novisad.primer2.model.Korisnik;
import rs.ac.singidunum.novisad.primer2.model.Racun;

public class ServerThreaded {
	public static void main(String[] args) {
		Korisnik korisnik1 = new Korisnik(0, "Petar", "Marković");
		Korisnik korisnik2 = new Korisnik(1, "Marko", "Petrović");
		
		Racun r1 = new Racun(korisnik1, "00001", 10000);
		Racun r2 = new Racun(korisnik2, "00002", 0);
		
		HashMap<String, Racun> racuni = new HashMap<String, Racun>();
		racuni.put(r1.getBrojRacuna(), r1);
		racuni.put(r2.getBrojRacuna(), r2);
		racuni.put("00003", new Racun(korisnik1, "00003",  1000));
		
		int port = 5000;

		try (ServerSocket ss = new ServerSocket(port)) {
//			"sa racuna,na racun,1000"
//			"uspesno" | "neuspesno"
			System.out.println("Server slusa na portu: " + port);
			while (true) {
				Socket socket = ss.accept();

				Handler h = new Handler(socket, racuni);
				
				Thread t = new Thread(h);
				t.start();
				
			}
		} catch (IOException e) {

		}
	}
}
