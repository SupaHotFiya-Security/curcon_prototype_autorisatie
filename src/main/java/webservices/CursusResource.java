package webservices;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Cursus;
import model.CursusService;
import model.ServiceProvider;

@Path("/cursus")
public class CursusResource {

	private JsonArray buildJsonArray(ArrayList<Cursus> cursussen) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Cursus c : cursussen) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", c.getId());
			job.add("naam", c.getNaam());
			job.add("beschrijving", c.getBeschrijving());
			jsonArrayBuilder.add(job);
		}

		return jsonArrayBuilder.build();
	}

	@Path("/altijd")
	@GET
	//@RolesAllowed({"admin","student"})
	@Produces("application/json")
	public String getCustomers() {
		CursusService service = ServiceProvider.getCursusService();
		JsonArray cursusArray = buildJsonArray(service.getAllCursussen());

		return cursusArray.toString();
	}
	
	@Path("/admin")
	@GET
	@RolesAllowed({"admin"})
	@Produces("application/json")
	public String getCustomerss() {
		CursusService service = ServiceProvider.getCursusService();
		JsonArray cursusArray = buildJsonArray(service.getAllCursussen());
		return cursusArray.toString();
	}
	
	

}
