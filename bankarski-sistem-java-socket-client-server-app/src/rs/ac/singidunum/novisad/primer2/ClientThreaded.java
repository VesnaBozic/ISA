package rs.ac.singidunum.novisad.primer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThreaded {
	public static class ClientHandler implements Runnable {
		@Override
		public void run() {
			Socket clientSocket;
			try {
				clientSocket = new Socket("localhost", 5000);

				PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				pw.println("00001,00002,1000");
				pw.flush();

				System.out.println(in.readLine());
				pw.close();
				in.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 25; i++) {
			threads.add(new Thread(new ClientHandler()));
		}
		
		for(Thread t : threads) {
			t.start();
		}
	}
}
