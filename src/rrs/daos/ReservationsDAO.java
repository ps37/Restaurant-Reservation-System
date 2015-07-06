package rrs.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrs.exceptions.AppException;
import rrs.models.Reservations;
import rrs.utils.DBConnector;

public class ReservationsDAO {
	
	public List<Reservations> getAllReservations () throws AppException {
		List<Reservations> reservationsList = new ArrayList<Reservations>();
		
		Connection con = DBConnector.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM reservations");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Reservations reservations = new Reservations();
				
				reservations.setId(rs.getInt("ID"));
				reservations.setName(rs.getString("NAME"));
				reservations.setEmail(rs.getString("EMAIL"));
				reservations.setDate(rs.getString("DATE"));
				reservations.setTime(rs.getString("TIME"));
				reservations.setPhone(rs.getString("PHONE"));
				reservations.setParty_size(rs.getInt("PARTY_SIZE"));
				reservations.setReservationStatus(rs.getString("STATUS"));
				reservations.setConfirmation_code(rs.getString("CONFIRMATION_CODE"));
				reservations.setNeed(rs.getString("NEED"));
				
				reservationsList.add(reservations);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("ERROR: " + e.getMessage(), e.getCause());
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		
		return reservationsList;
	}
	
	//Method to get a specific Reservation.
	public Reservations getReservation(int reservationId) throws AppException
	{
		Reservations reservations = new Reservations();
		
		Connection con = DBConnector.connectToDB();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservations WHERE ID=?");
			ps.setInt(1, reservationId);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				reservations.setId(rs.getInt("ID"));
				reservations.setName(rs.getString("NAME"));
				reservations.setEmail(rs.getString("EMAIL"));
				reservations.setDate(rs.getString("DATE"));
				reservations.setTime(rs.getString("TIME"));
				reservations.setPhone(rs.getString("PHONE"));
				reservations.setParty_size(rs.getInt("PARTY_SIZE"));
				reservations.setReservationStatus(rs.getString("STATUS"));
				reservations.setConfirmation_code(rs.getString("CONFIRMATION_CODE"));
				reservations.setNeed(rs.getString("NEED"));
			}
			else
			{
				throw new AppException("Error: ID " + reservationId + " is not available in the system.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		
		return reservations;
	}
	
	//Method to insert a reservation
	public Reservations createReservation(Reservations reservations) throws AppException
	{
		Connection con = DBConnector.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("INSERT INTO reservations "
			+ "(Date, Time, Name, Phone, Email, "
			+ "Party_Size, Need) VALUES (?, ?, ?, ?, ?, ?, ?)", 
			PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, reservations.getDate());
			ps.setString(2, reservations.getTime());
			ps.setString(3, reservations.getName());
			ps.setString(4, reservations.getPhone());
			ps.setString(5, reservations.getEmail());
			ps.setInt(6, reservations.getParty_size());
			ps.setString(7,reservations.getNeed());			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
//			reservations.setReservationStatus(rs.getString(8));
			
			if(rs.next())
			{
				reservations.setId(rs.getInt(1));
//				reservations.setReservationStatus(rs.);
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		finally 
		{
			DBConnector.closeResources(ps, rs, con);
		}
		
		return reservations;
		
	}
	
	//Method to delete a specific reservation
	public void deleteReservation(int reservationId) throws AppException
	{	
		Connection con = DBConnector.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("DELETE from reservations WHERE ID=?");
			ps.setInt(1, reservationId);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
	}
	
	//Method to edit a specific reservation
	public Reservations editReservation(Reservations reservations, 
			int reservationId) throws AppException
	{
		Connection con = DBConnector.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("UPDATE reservations SET "
					+ "Date=?, Time=?, Name=?, Phone=?, Email=?, "
			+ "Party_Size=?, Need=? WHERE ID=?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, reservations.getDate());
			ps.setString(2, reservations.getTime());
			ps.setString(3, reservations.getName());
			ps.setString(4, reservations.getPhone());
			ps.setString(5, reservations.getEmail());
			ps.setInt(6, reservations.getParty_size());
			ps.setString(7,reservations.getNeed());
			ps.setInt(8, reservationId);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			reservations.setId(reservationId);
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		finally 
		{
			DBConnector.closeResources(ps, rs, con);
		}
		
		return reservations;
		
	}
}
