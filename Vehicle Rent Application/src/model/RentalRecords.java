package model;


public abstract class RentalRecords {

	protected String rent_id;
	protected String cust_id;
	protected String vehicle_id;
	protected String cust_name;
	protected String rent_date;
	protected String act_return_date;
	protected String est_return_date;
	protected String actual_fee;
	protected String late_fee;
	protected String total_fee;
	protected String no_of_days;
	
	public String getRent_id() {
		return rent_id;
	}
	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getRent_date() {
		return rent_date;
	}
	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}
	public String getAct_return_date() {
		return act_return_date;
	}
	public void setAct_return_date(String act_return_date) {
		this.act_return_date = act_return_date;
	}
	public String getEst_return_date() {
		return est_return_date;
	}
	public void setEst_return_date(String date) {
		this.est_return_date = date;
	}
	public String getActual_fee() {
		return actual_fee;
	}
	public void setActual_fee(String actual_fee) {
		this.actual_fee = actual_fee;
	}
	public String getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(String late_fee) {
		this.late_fee = late_fee;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getno_of_days() {
		return no_of_days;
	}
	public void setno_of_days(String new_no_of_days) {
		this.no_of_days = new_no_of_days;
	}
	
	public RentalRecords() {
		
	}
		
	public RentalRecords(String rent_id, String cust_id, String vehicle_id, String cust_name, String rent_date,
			String act_return_date, String est_return_date, String actual_fee, String late_fee,String total_fee) {
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
	
	public abstract boolean booking(Car data1,RentalRecords data2);
	public abstract boolean return_vehicle(Car data1,RentalRecords cont);
	public abstract boolean validation(RentalRecords data2);
	
	
	}
