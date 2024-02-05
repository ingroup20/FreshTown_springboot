package com.cha104g1.freshtown_springboot.blob.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhotoWrite {
	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		String url = "jdbc:mysql://localhost:3306/cha104g1?serverTimezone=Asia/Taipei";
		String userid = "root";
		String passwd = "19920220";
		String photos = "src/main/resources/static/images"; //皜祈岫?典??歇蝵格??獢?敺?銝??esources/DB_photos1??
		String update = "update stores set photo =? where storeId=?";

		int count = 1;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);
			File[] photoFiles = new File(photos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(update);
				pstmt.setInt(2, count);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				count++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("ok.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
