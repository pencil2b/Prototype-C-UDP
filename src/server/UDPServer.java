/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author dorian
 */
public class UDPServer {
	
	private final DatagramSocket socket;
	private final byte buffer[];
	
	public UDPServer(int port) throws SocketException {
		this.buffer = new byte[256];
		socket = new DatagramSocket(port);
	}
	
	public byte[] receive() throws IOException {
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		return buffer;
	}
}
