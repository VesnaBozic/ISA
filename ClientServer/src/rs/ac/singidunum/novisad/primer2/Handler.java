package rs.ac.singidunum.novisad.primer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
	private Socket socket;

	public Handler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			System.out.println("Konekcija stigla od: " + socket.getInetAddress().toString());
			String request = in.readLine();
			System.out.println(request);
			pw.println(request);
			pw.flush();

			pw.close();
			in.close();
			socket.close();
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

