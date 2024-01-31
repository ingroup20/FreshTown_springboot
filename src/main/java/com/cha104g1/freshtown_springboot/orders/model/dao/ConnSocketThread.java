package com.cha104g1.freshtown_springboot.orders.model.dao;
import java.io.*;
import java.net.*;

public class ConnSocketThread extends Thread {
	private Socket socket = null;
	int todayOrder;

	public ConnSocketThread(Socket socket, int count) { // 建構子socket為客戶端
		this.socket = socket;
		todayOrder = count;
	}

	public void run() {
		System.out.println("今天第" + todayOrder + "張訂單");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());

			while (true) {
				String orderId = in.readLine(); // 由socket讀入的字串msg
				if (orderId.equals("storeClosed"))
					break;
				String orderIdServer = new String("\t" + "Server接收到訊息:" + orderId);
				out.println(orderIdServer);
				out.flush();
			}
			out.close();
			in.close();
			socket.close();
			System.out.println("第" + todayOrder + "張訂單付費完成");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
