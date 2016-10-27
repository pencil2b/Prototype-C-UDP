/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dorian
 */
public class Client  {
	
	static int x, y;
	static int port = 34567;
	
	public static void main(String[] args) throws InterruptedException {
		UDPClient client = new UDPClient();
		
		String server1, server2;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Server 1: ");
		server1 = scanner.next();
		System.out.print("Server 2: ");
		server2 = scanner.next();
		
		Thread timer = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
					}
					x = x + 1 > 100 ? 0 : x + 1;
					y = y + 1 > 100 ? 0 : y + 1;
				}
			}
		};
		
		timer.setDaemon(true);
		timer.start();
		
		while(true) {
			Thread.sleep(200);
			
			// buffer: 'x'(1 byte), x(4 bytes), 'y'(1 byte), y(4 bytes)... 
			byte bytes[] = new byte[10];
			bytes[0] = 'x';
			bytes[5] = 'y';
			byte bytesX[] = ByteBuffer.allocate(4).putInt(x).array();
			byte bytesY[] = ByteBuffer.allocate(4).putInt(y).array();
			for(int i = 0; i < 4; i++) {
				bytes[i + 1] = bytesX[i];
				bytes[i + 6] = bytesY[i];
			}
			
			try {
				client.send(server1, port, bytes);
				client.send(server2, port, bytes);
			} catch (SocketException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
	}
}
