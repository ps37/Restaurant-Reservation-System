package rrs.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rrs.daos.ReservationsDAO;
import rrs.exceptions.AppException;
import rrs.models.Reservations;
import rrs.rest.AppResponse;

@Path("/reservations")//if the URL has reservations then this will handle it
public class ReservationsController {
	
	//to get all the reservations list
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll() {
		
		AppResponse resp = new AppResponse();
		try {
			ReservationsDAO dao = new ReservationsDAO();
			List<Reservations> reservationsList  = dao.getAllReservations();
			resp.setPayload(reservationsList);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
	
	//to get a specific reservation from the DB
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getReservation(@PathParam("id") int reservationId)
	{
		AppResponse response = new AppResponse();

		try {
			ReservationsDAO dao = new ReservationsDAO();
			Reservations reservation= dao.getReservation(reservationId);
			response.setPayload(reservation);
		} catch (AppException e) {
			response.setStatus(AppResponse.ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//to create a specific reservation in the reservations DB
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse createReservation(Reservations reservation){
		AppResponse response = new AppResponse();
		try {
			ReservationsDAO dao = new ReservationsDAO();
			reservation = dao.createReservation(reservation);
			response.setPayload(reservation);
		} catch (AppException e) {
			response.setStatus(AppResponse.ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//Method to delete a reservation in the reservations DB
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse deleteReservation(@PathParam("id") int reservationId)
	{
		AppResponse response = new AppResponse();

		try {
			ReservationsDAO dao = new ReservationsDAO();
			dao.deleteReservation(reservationId);
			response.setPayload("Deletion Successful");
		} catch (AppException e) {
			response.setStatus(AppResponse.ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//Method to edit a reservation in the reservations DB
	@POST
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse editReservation(@PathParam("id") int reservationId, 
			Reservations reservation){
		AppResponse response = new AppResponse();
		try {
			ReservationsDAO dao = new ReservationsDAO();
			reservation = dao.editReservation(reservation, reservationId);
			response.setPayload(reservation);
		} catch (AppException e) {
			response.setStatus(AppResponse.ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
