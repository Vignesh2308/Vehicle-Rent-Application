package model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ConnectionTest;

public class Car extends Vehicle {

	public void vehicle_select(String vehicle_type) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					if ((resultSet.getString("VEHICLE_ID")).substring(0, 1).equals(vehicle_type))
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
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE SEAT='" + seat_type + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
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
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE MAKE='" + make + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					arr1.add((resultSet.getString("VEHICLE_ID")));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Car data(String id) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE VEHICLE_ID = " + "'" + id + "'";

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					Car vehicledata = new Car();

					vehicledata.setVehicle_id(resultSet.getString("VEHICLE_ID"));
					vehicledata.setImage(resultSet.getString("IMAGE"));
					vehicledata.setVehicle_name(resultSet.getString("VEHICLE_NAME"));
					vehicledata.setAmount(resultSet.getString("AMOUNT").toString());
					vehicledata.setVehicle_class(resultSet.getString("VEHICLE_CLASS"));
					vehicledata.setEngine_type(resultSet.getString("ENGINE_TYPE"));
					vehicledata.setEngine_capacity(resultSet.getString("ENGINE_CAPACITY").toString());
					vehicledata.setAc(resultSet.getString("AC"));
					vehicledata.setLock(resultSet.getString("LOCK"));
					vehicledata.setAir_bags(resultSet.getString("AIR_BAGS"));
					vehicledata.setStereo_type(resultSet.getString("STEREO"));
					vehicledata.setAvailability(resultSet.getString("AVAILABILITY"));
					vehicledata.setWindow_type(resultSet.getString("WINDOW_TYPE"));
					vehicledata.setSeats(resultSet.getString("SEAT"));
					vehicledata.setMake(resultSet.getString("MAKE"));
					vehicledata.setYear(resultSet.getString("YEAR"));
					return vehicledata;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void total_ids() {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RENTAL_RECORDS";
		total.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					total.add(resultSet.getString("RENT_ID"));

				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void rent_id_store(String id) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RENTAL_RECORDS";
		rent_id_arr.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE VEHICLE_ID = " + "'" + id + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {

					rent_id_arr.add(resultSet.getString("RENT_ID"));

				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	

}
