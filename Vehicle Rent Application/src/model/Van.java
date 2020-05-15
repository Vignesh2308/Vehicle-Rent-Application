package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ConnectionTest;

public class Van extends Vehicle{

	public void vehicle_select(String vehicle_type) {
		System.out.println("vannnnn");
		final String DB_NAME = "testDB" ;
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME;
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
								if((resultSet.getString("VEHICLE_ID")).substring(0, 1).equals("V"))
								arr1.add((resultSet.getString("VEHICLE_ID")));	
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}

	public void seater(String seat_type) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME +" WHERE SEAT='"+seat_type+"'";
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
								arr1.add((resultSet.getString("VEHICLE_ID")));	
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
	
	public void make(String make) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME +" WHERE MAKE='"+make+"'";
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
								arr1.add((resultSet.getString("VEHICLE_ID")));	
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}

}
