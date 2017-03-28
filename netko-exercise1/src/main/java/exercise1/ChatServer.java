package exercise1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

	public static void main(String[] args) {
		try {

			// create new server socket on port 1234 on local network adapter
			ServerSocket serverSocket = new ServerSocket(8080);

			// start listening on port 1234 on local network adapter
			Socket socketAccept = serverSocket.accept();

			// get printstream of output stream of server socket (used for
			// response)
			PrintStream printStream = new PrintStream(socketAccept.getOutputStream(), true);

			// wrap a scanner around the inputstream of server socket
			Scanner socketScanner = new Scanner(socketAccept.getInputStream());

			// if set to false, stop listening
			Boolean serverRunning = true;

			// listen for input from keyboard
			Scanner responseScanner = new Scanner(System.in);

			while (serverRunning) {
				String receive = socketScanner.nextLine();
				if (receive.equals("")) {
					serverRunning = false;
				}
				System.out.println("Nachricht: " + receive);
				System.out.print("Antwort: ");
				String response = responseScanner.nextLine();
				printStream.print(response + System.getProperty("line.separator"));
				if (response.equals("")) {
					serverRunning = false;
				}
			}

			socketScanner.close();
			serverSocket.close();
			responseScanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
