/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author dorian
 */
public class Server {
	
	static int port = 34567;
	
	public static void main(String[] args) throws SocketException, IOException {
		UDPServer server = new UDPServer(port);
		System.out.println("Listening on port " + port + "...");
		
		while(true) {
			byte bytes[] = server.receive();
			int x = 0, y = 0;
			for(int i = 0; i < 4; i++) {
				x = (x << 8) | bytes[i + 1];
				y = (y << 8) | bytes[i + 6];
			}
			System.out.println(String.format("(%d, %d)", x, y));
		}
	}
}
