package model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.ConnectionTest;
import exception.AddVehicleException;
import exception.MandatoryFieldException;
import view.AddVehicle;

public abstract class Vehicle {
	private String vehicle_id;
	private String image;
	private String vehicle_name;
	private String vehicle_class;
	private String engine_type;
	private String engine_capacity;
	private String ac;
	private String lock;
	private String air_bags;
	private String stereo_type;
	private String window_type;
	private String amount;
	private String availability;
	private String seats;
	private String make;
	private String year;
	public ArrayList<String> max_id=new ArrayList<String>();
	public ArrayList<String> arr1=new ArrayList<String>();
	public ArrayList<String> rent_id_arr=new ArrayList<String>();
	public ArrayList<String> total=new ArrayList<String>();
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getVehicle_class() {
		return vehicle_class;
	}
	public void setVehicle_class(String vehicle_class) {
		this.vehicle_class = vehicle_class;
	}
	public String getEngine_type() {
		return engine_type;
	}
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	public String getEngine_capacity() {
		return engine_capacity;
	}
	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public String getAir_bags() {
		return air_bags;
	}
	public void setAir_bags(String air_bag) {
		this.air_bags = air_bag;
	}
	public String getStereo_type() {
		return stereo_type;
	}
	public void setStereo_type(String stereo_type) {
		this.stereo_type = stereo_type;
	}
	public String getWindow_type() {
		return window_type;
	}
	public void setWindow_type(String window_type) {
		this.window_type = window_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

	public abstract void vehicle_select(String vehicle_type);
	public abstract void seater(String seat_type);
	public abstract void make(String make);
	
	public void Available(String avail) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME +" WHERE AVAILABILITY='"+avail+"'";
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

	
	public void All() {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		arr1.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME;
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
	
	public void vehicle()
	{
		if(validation())
		{
		int size=max_vehicles()+1;
		try (Connection con = ConnectionTest.getConnection("testDB");
				Statement stmt = con.createStatement();) {
			String query = "INSERT INTO VEHICLES  VALUES ('"+vehicle_id+"_0"+ size+ "','No_image.jpg','"+year+
					"','"+vehicle_name+"','"+vehicle_class+"','"+engine_type+"','"+engine_capacity+
					"','"+ac+"','"+lock+"','"+air_bags+"','"+stereo_type+"','"+window_type+
					"','"+amount+"','"+availability+"','"+seats+"','"+make+"')";
			
			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("Insert into table " + "RENTAL_RECORDS" + " executed successfully");
			System.out.println(result + " row(s) affected");
			AddVehicle.window2.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		}
	}

public int max_vehicles() {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLES";
		max_id.clear();
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME;
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
							
							max_id.add(resultSet.getString("VEHICLE_ID"));
							
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return max_id.size();
		}
	

public boolean validation()
{
	if(vehicle_id ==null || ac ==null || air_bags==null || amount==null || availability==null 
			|| engine_capacity==null || engine_type==null || lock==null || make==null ||
			seats==null || stereo_type==null ||vehicle_class==null || vehicle_name==null
			|| window_type==null || year==null)
	{
		MandatoryFieldException.mandatoryfield();
		return false;
	}
	
	if(engine_capacity.matches("^[a-zA-Z]+$"))
	{
		AddVehicleException.invalid_engine_capacity_Exception();
		return false;
	}
	
	if(amount.matches("^[a-zA-Z]+$"))
	{
		AddVehicleException.invalid_cost_Exception();
	}
	
	
	return true;

}


}
