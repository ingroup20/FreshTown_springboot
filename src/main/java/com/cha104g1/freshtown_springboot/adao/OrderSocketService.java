package com.cha104g1.freshtown_springboot.adao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cha104g1.freshtown_springboot.orders.model.dao.ConnSocketThread;

public class OrderSocketService {
	Socket socket;
	int port;

	
	
	//待寫放入InetAddress storeAddr = InetAddress.getLocalHost();; 
	public  InetAddress whoStore() {
		InetAddress localhost=null;
        try {
            // 使用 getLocalHost 方法獲取本機的 InetAddress 對象
             localhost = InetAddress.getLocalHost();
            System.out.println("Localhost IP Address: " + localhost.getHostAddress());

            // 使用 getByName 方法獲取指定主機名的 InetAddress 對象
            InetAddress googleDNS = InetAddress.getByName("8.8.8.8");
            System.out.println("Google DNS IP Address: " + googleDNS.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("未取得ip位置...");
        }
        return localhost;
    }
	
	public void storeSide() {
		String data;
		InetAddress storeAddr = null; //代表ip的類別InetAddress
		BufferedReader orderId = null; //System.in鍵盤輸入事件->放入orderId
		BufferedReader orderId_in;
		PrintWriter orderId_out;
		
		if(whoStore()!=null)
			storeAddr =whoStore(); 
		else
			System.out.println("Server位址錯誤或未知...");

		try {
			port = 8888;//因為是parseInt方法沒有強制要處理例外，故可不用處理
			socket = new Socket(storeAddr, port);//此建構子就會根據傳入的ip，port去找serverSocket建立連線(真正連線的地方)
			orderId_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//socket是低階資料流
			orderId_out = new PrintWriter(socket.getOutputStream());
			while (true) {
				System.out.println("保持連線中");
				data = orderId.readLine();//socket寫入語法
				orderId_out.println(data);//socket寫入語法
				orderId_out.flush();//socket
				if (data.equals("payDone"))
					break;
				System.out.println("");
			}
			socket.close();
		} catch (IOException ioe) {
			System.out.println("無法連接主機...");
		}
	}
	
	
	public void customerSide() throws IOException {
		ServerSocket sc = null;
		int count = 0;

		System.out.println("TcpServerM listening port 8888.......");
		try {
			sc = new ServerSocket(8888); // 在8888埠建立ServerSocket, 並等待客戶端的連結
		} catch (IOException ioe) {
			System.err.println("錯誤:Could not listen on port: 1024.");
			return;
		}

		try {
			while (true) {//雖然是無窮迴圈創thread，但有accept方法會發生wait，等有client連進來時，才繼續執行
				new ConnSocketThread(sc.accept(), ++count).start(); // 連線start()
			}
		} catch (IOException ioe) {
			System.err.println("Exception" + ioe);
		}
		sc.close();
			
	}

}
