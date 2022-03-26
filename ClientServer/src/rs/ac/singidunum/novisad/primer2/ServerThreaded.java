package rs.ac.singidunum.novisad.primer2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThreaded {
	public static void main(String[] args) {
		int port = 5000;

		try (ServerSocket ss = new ServerSocket(port)) {

			System.out.println("Server slusa na portu: " + port);
			while (true) {
				Socket socket = ss.accept();

				Handler h = new Handler(socket);
				
				Thread t = new Thread(h);
				t.start();
				
			}
		} catch (IOException e) {

		}
	}
}

