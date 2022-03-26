package rs.ac.singidunum.novisad.primer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		int port = 5000;
		
		try(ServerSocket ss = new ServerSocket(port)) {
			
			System.out.println("Server slusa na portu: " + port);
			while(true) {
				Socket socket = ss.accept();
				
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
			}
		} catch (IOException e) {
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
