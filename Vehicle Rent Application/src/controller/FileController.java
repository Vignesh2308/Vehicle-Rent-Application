package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.ConnectionManager;
import data.ConnectionTest;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileController {
	Connection dbConnection;
	public void exportDataToFile(Stage primaryStage) throws Exception {
		FileChooser fileChooser = new FileChooser();  

		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showSaveDialog(primaryStage);
		String data="";
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(file);
		try (Connection con = ConnectionTest.getConnection("testDB");
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + "VEHICLES WHERE 1=1";
		
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
							data=resultSet.getString("VEHICLE_ID") + ","+
							resultSet.getString("IMAGE") + ","+
							resultSet.getString("VEHICLE_NAME") + ","+
							resultSet.getString("AMOUNT").toString() + ","+
							resultSet.getString("VEHICLE_CLASS") + ","+
							resultSet.getString("ENGINE_TYPE") + ","+
							resultSet.getString("ENGINE_CAPACITY").toString() + ","+
							resultSet.getString("AC") + ","+
							resultSet.getString("LOCK") + ","+
							resultSet.getString("AIR_BAGS") + ","+
							resultSet.getString("STEREO") + ","+
							resultSet.getString("AVAILABILITY") + ","+
							resultSet.getString("WINDOW_TYPE") + ","+
							resultSet.getString("SEAT") + ","+
							resultSet.getString("MAKE") + ","+
							resultSet.getString("YEAR") + System.lineSeparator();
							
							try {

								fileWriter.write(data);

							} catch (IOException e) {
								e.printStackTrace();
							}
							data="";
						}
						String query1 = "SELECT * FROM RENTAL_RECORDS WHERE 1=1";
						try (ResultSet resultSet1 = stmt.executeQuery(query1)) {
							while(resultSet1.next()) {
								data=resultSet1.getString("RENT_ID") + ","+
										resultSet1.getString("CUSTOMER_ID") + ","+
										resultSet1.getString("VEHICLE_ID") + ","+
										resultSet1.getString("CUSTOMER_NAME") + ","+
										resultSet1.getString("RENT_DATE") + ","+
										resultSet1.getString("ESTIMATED_RETURN_DATE") + ","+
										resultSet1.getString("ACTUAL_RETURN_DATE") + ","+
										resultSet1.getString("ACTUAL_FEE") + ","+
										resultSet1.getString("LATE_FEE") + System.lineSeparator();
								
								
								
								try {

									fileWriter.write(data);

								} catch (IOException e) {
									e.printStackTrace();
								}
								data="";
							}
							}
						
						
						fileWriter.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("DONE");
	}
	
	
	public void importDataFromFile(Stage primaryStage) throws Exception { // Method to import file
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				List<String> listFromFileLine = new ArrayList<>();
				String text;
				while ((text = bufferedReader.readLine()) != null) {
					listFromFileLine = Arrays.asList(text.split(","));
					
					
					System.out.println(listFromFileLine);
					
					dbConnection = ConnectionManager.getConnection();
					Statement stmt = dbConnection.createStatement();

						String query = "INSERT INTO RENTAL_RECORDS  VALUES ("
				 				+  "'"+listFromFileLine.get(0)+"'," + "'"+listFromFileLine.get(1)+"',"  +  "'"+listFromFileLine.get(2)+"',"
								+  "'"+listFromFileLine.get(3)+"',"   +"'"+listFromFileLine.get(4)+"',"
								+  "'"+listFromFileLine.get(5)+"',"   +"'"+listFromFileLine.get(6)+"',"   +"'"+listFromFileLine.get(7)+"',"
								+  "'"+listFromFileLine.get(8)+"')";
						
						stmt.addBatch(query);
//						dbConnection.commit();
						
						stmt.executeBatch();
						stmt.close();
						dbConnection.close();
						System.out.println("Insert into table " + "RENTAL_RECORDS" + " executed successfully");
						System.out.println("hello" + " row(s) affected");					
				}
			}
			finally {
				try {
					
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	}
	
	
	
	
}
