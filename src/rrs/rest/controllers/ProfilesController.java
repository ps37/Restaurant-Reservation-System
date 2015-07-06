package rrs.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rrs.daos.ProfilesDAO;
import rrs.daos.ReservationsDAO;
import rrs.exceptions.AppException;
import rrs.models.Profiles;
import rrs.models.Reservations;
import rrs.rest.AppResponse;

@Path("/profiles")//if the URL has profiles then this will handle it
public class ProfilesController {
	
	//to get the profile from the profiles DB
	@GET
	@Path("/get/loginInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getReservation()
	{
		AppResponse response = new AppResponse();

		try {
			ProfilesDAO dao = new ProfilesDAO();
			Profiles profile= dao.getProfile();
			response.setPayload(profile);
		} catch (AppException e) {
			response.setStatus(AppResponse.ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//Method to edit the profile in the profiles DB
		@POST
		@Path("/update/loginProfile")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public AppResponse updateProfile( Profiles profile){
			AppResponse response = new AppResponse();
			try {
				ProfilesDAO dao = new ProfilesDAO();
				profile = dao.updateProfile(profile);
				response.setPayload(profile);
			} catch (AppException e) {
				response.setStatus(AppResponse.ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
		}

}
