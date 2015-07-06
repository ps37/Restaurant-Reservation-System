package rrs.models;

public class Reservations {
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String date;
	private String time;
	private int party_size;
	private String confirmation_code;
	private String reservationStatus;
	private String need;
	
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservation_status) {
		this.reservationStatus = reservation_status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getParty_size() {
		return party_size;
	}
	public void setParty_size(int party_size) {
		this.party_size = party_size;
	}
	
	public String getConfirmation_code() {
		return confirmation_code;
	}
	public void setConfirmation_code(String confirmation_code) {
		this.confirmation_code = confirmation_code;
	}
	
}
