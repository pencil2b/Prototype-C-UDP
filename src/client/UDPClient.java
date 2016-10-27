/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author dorian
 */
public class UDPClient {
	
	public void send(String host, int port, byte buffer[]) throws UnknownHostException, SocketException, IOException {
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);
	}
}
