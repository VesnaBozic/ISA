package rs.ac.singidunum.novisad.primer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import rs.ac.singidunum.novisad.primer2.model.Racun;

public class Handler implements Runnable {
	private Socket socket;
	private HashMap<String, Racun> racuni;

	public Handler(Socket socket, HashMap<String, Racun> racuni) {
		super();
		this.socket = socket;
		this.racuni = racuni;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			System.out.println("Konekcija stigla od: " + socket.getInetAddress().toString());
			String request = in.readLine();
			String[] podaci = request.split(",");
			if(podaci.length < 3) {
				pw.println("neuspesno!");
				pw.close();
				in.close();
				socket.close();
				return;
			}
			
			Racun r1 = racuni.get(podaci[0]);
			Racun r2 = racuni.get(podaci[1]);
			double iznos = 0;
			try {
				iznos = Double.parseDouble(podaci[2]);
			} catch (NumberFormatException e) {
				pw.println("neuspesno!");
				pw.close();
				in.close();
				socket.close();
				return;
			}
			
			if(r1 == null || r2 == null) {
				pw.println("neuspesno!");
				pw.close();
				in.close();
				socket.close();
				return;
			}
			
			if(r1.getStanje() > 0) {
				r2.uplati(r1, iznos);
			}
			
			pw.println("uspesno!");
			pw.flush();

			pw.close();
			in.close();
			socket.close();
			
			System.out.println(r1);
			System.out.println(r2);
			System.out.println("-------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
