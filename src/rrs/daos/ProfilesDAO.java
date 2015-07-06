package rrs.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrs.exceptions.AppException;
import rrs.models.Profiles;
import rrs.models.Reservations;
import rrs.utils.DBConnector;

public class ProfilesDAO {
	
public Profiles getProfile() throws AppException{
		
		Connection con= DBConnector.connectToDB();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Profiles profile=new Profiles();
		
		try {
			ps=con.prepareStatement("SELECT * FROM profiles");
			rs=ps.executeQuery();
			while(rs.next())
			{			
				profile.setName(rs.getString("Name"));
				profile.setContact(rs.getString("Contact"));
				profile.setEmail(rs.getString("Email"));
				profile.setAddress(rs.getString("Address"));
				profile.setPassword(rs.getString("Password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		
		return profile;		
	}

//Method to edit the profile
	public Profiles updateProfile(Profiles profile) throws AppException{
		
		Connection con = DBConnector.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("UPDATE profiles SET "
					+ "address=?, contact=?, email=?, name=?, password=?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, profile.getAddress());
			ps.setString(2, profile.getContact());
			ps.setString(3, profile.getEmail());
			ps.setString(4, profile.getName());
			ps.setString(5, profile.getPassword());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
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
		
		return profile;
		
	}

}
