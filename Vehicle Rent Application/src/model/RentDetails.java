package model;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ConnectionTest;
import exception.MandatoryFieldException;
import exception.VehicleBookingException;

public class RentDetails extends RentalRecords{
	
	public RentDetails()
	{
		
	}
	public RentDetails(String rent_id, String cust_id, String vehicle_id, String cust_name, String rent_date,
			String act_return_date, String est_return_date, String actual_fee, String late_fee, String total_fee) {
		super();
		this.rent_id = rent_id;
		this.cust_id = cust_id;
		this.vehicle_id = vehicle_id;
		this.cust_name = cust_name;
		this.rent_date = rent_date;
		this.act_return_date = act_return_date;
		this.est_return_date = est_return_date;
		this.actual_fee = actual_fee;
		this.late_fee = late_fee;
		this.total_fee = total_fee;
	}


	public RentDetails rent(String id) {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RENTAL_RECORDS";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE RENT_ID = " + "'"+ id+"'";

		try (ResultSet resultSet = stmt.executeQuery(query)) {
						while(resultSet.next()) {
							RentDetails rentdata = new RentDetails(); 
							rentdata.setRent_id(resultSet.getString("RENT_ID"));
							rentdata.setCust_id(resultSet.getString("CUSTOMER_ID"));
							rentdata.setVehicle_id(resultSet.getString("VEHICLE_ID"));
							rentdata.setCust_name(resultSet.getString("CUSTOMER_NAME"));
							rentdata.setRent_date(resultSet.getString("RENT_DATE").toString());
							rentdata.setEst_return_date(resultSet.getString("ESTIMATED_RETURN_DATE").toString());
							if (resultSet.getString("ACTUAL_RETURN_DATE").toString().equals("2020-10-10"))
								rentdata.setAct_return_date("");
							else {
								rentdata.setAct_return_date(resultSet.getString("ACTUAL_RETURN_DATE").toString());
							}
							
							rentdata.setActual_fee(resultSet.getString("ACTUAL_FEE"));
							if (resultSet.getString("LATE_FEE").equals("0"))
							{
								rentdata.setTotal_fee("");
								rentdata.setLate_fee("");
								
							}
							else {
								rentdata.setLate_fee(resultSet.getString("LATE_FEE"));
								rentdata.setTotal_fee(String.valueOf(Integer.parseInt(resultSet.getString("ACTUAL_FEE"))+Integer.parseInt(resultSet.getString("LATE_FEE"))));
							}
							return rentdata;
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		}

	
	public boolean booking(Car data1,RentalRecords data2)
	{
		if(validation(data2))
		{	
		try (Connection con = ConnectionTest.getConnection("testDB");
			Statement stmt = con.createStatement();) {
		String query = "INSERT INTO RENTAL_RECORDS  VALUES ("
 				+  "'0"+ data2.rent_id+"'," + "'"+data2.cust_id+"',"  +  "'"+data2.vehicle_id+"',"
				+  "'"+data2.cust_name+"',"   +"'"+data2.rent_date+"',"
				+  "'"+data2.est_return_date+"',"   +"'"+"2020-10-10"+"',"   +"'"+data1.getAmount()+"',"
				+  "'0')";
		
		int result = stmt.executeUpdate(query);
		
		con.commit();
		
		System.out.println("Insert into table " + "RENTAL_RECORDS" + " executed successfully");
		System.out.println(result + " row(s) affected");

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

		try (Connection con = ConnectionTest.getConnection("testDB");
				Statement stmt = con.createStatement();) {
			String query = "UPDATE VEHICLES SET AVAILABILITY='N'"+" WHERE VEHICLE_ID = '" +data2.vehicle_id +"'";
			
			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("UPDATE into table " + "VEHICLES" + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return true;
		}
		return false;
	}
	
	
	
	public boolean return_vehicle(Car data1,RentalRecords data2)
	{
		System.out.println(data2.rent_id);
		try (Connection con = ConnectionTest.getConnection("testDB");
			Statement stmt = con.createStatement();) {
		String query = "UPDATE VEHICLES SET AVAILABILITY='Y'"+" WHERE VEHICLE_ID = '" +data2.getVehicle_id() +"'";
		
		int result = stmt.executeUpdate(query);
		
		con.commit();
		
		System.out.println("UPDATE into table " + "VEHICLES" + " executed successfully");
		System.out.println(result + " row(s) affected");

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return return_veh2(data1,data2);
	}
	
	public boolean return_veh2(Car data1,RentalRecords data2)
	{
		System.out.println(data2.getVehicle_id());
		System.out.println(data2.rent_id+"efrggs");
//		System.out.println(rent_id+"845612");
		System.out.println(data2.getRent_id());
		try (Connection con = ConnectionTest.getConnection("testDB");
				Statement stmt = con.createStatement();) {
			String query = "UPDATE RENTAL_RECORDS SET LATE_FEE= "+"'"+late_fee+"',"+
				"ACTUAL_RETURN_DATE='"+act_return_date+"'"+" WHERE RENT_ID ='" +data2.rent_id+
					"'";
			
			
			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("UPDATE into table " + "CUSTOMER222" + " executed successfully");
			System.out.println(result + " row(s) affected");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	public boolean validation(RentalRecords data)
	{
		if(data.cust_id==null || data.cust_name==null || data.no_of_days==null)
		{
			MandatoryFieldException.mandatoryfield();
			return false;
		}
		if (data.cust_id.matches("^[a-zA-Z]+$"))
		{
			VehicleBookingException.invalid_customer_id();
			return false;
		}
		
		if(data.cust_name.matches("^[0-9]+$"))
		{
			VehicleBookingException.invalid_customer_name();
			return false;
		}
		
		if(Integer.parseInt(data.no_of_days)>4 || data.no_of_days.matches("^[a-zA-Z]+$"))
		{
			VehicleBookingException.invalid_no_of_days();
			return false;
		}
		
		return true;
		
	}
	
	


	


	


	

	


}
