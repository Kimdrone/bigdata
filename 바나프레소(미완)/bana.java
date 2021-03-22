package com.koreait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class bana {

	public static void main(String[] args) {
		String DRIVER_ID = "webdriver.chrome.driver";
		String DRIVER_PATH = "C:/JAVA kb KDR/JSP/chromedriver.exe";

		System.setProperty(DRIVER_ID, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();

		Connection conn = null;
		PreparedStatement pstmt =null;
		
		String sql = "";
		String url = "jdbc:mysql://localhost:3306/banapresso";
		String uid = "root";
		String upw = "1234";

		

		String base_url = "https://www.banapresso.com/store";

		try {
			driver.get(base_url);
			Thread.sleep(2000); 

			int pageNum = 2;
			String sto_name = null; //
			String sto_address = null; 

			while (true) {

				List<WebElement> element1 = driver.findElements(By.cssSelector(".store_name_map > i"));
				List<WebElement> element2 = driver.findElements(By.cssSelector(".store_name_map > i + span"));

				try {
					if (conn != null) {
						sql = "insert into tb_store(sto_name, sto_address) values (?, ?)";
						pstmt = conn.prepareStatement(sql);

						for (int i = 0; i <= element1.size(); i++) {
							sto_name = element1.get(i).getText();
							sto_address = element2.get(i).getText();
							
							System.out.println(sto_name); 
							pstmt.setString(1, sto_name); 
							System.out.println(sto_address);
							pstmt.setString(2, sto_address);
							pstmt.executeUpdate();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, uid, upw);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
