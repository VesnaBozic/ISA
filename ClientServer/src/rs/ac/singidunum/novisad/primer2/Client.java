package rs.ac.singidunum.novisad.primer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 100; i++) {
				Socket clientSocket = new Socket("localhost", 5000);
				PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				pw.println("PORUKA!");
				pw.flush();

				System.out.println(in.readLine());
				pw.close();
				in.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
