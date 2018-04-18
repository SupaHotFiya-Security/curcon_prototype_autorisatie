package webservices;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

	@GET
	//@RolesAllowed({ "admin","student" })
	@Path("/all")
	@Produces("application/json")
	public String getcursussen() {
		CursusService service = ServiceProvider.getCursusService();
		JsonArray cursusArray = buildJsonArray(service.getAllCursussen());
		return cursusArray.toString();
	}


	@POST
	@RolesAllowed({ "student" })
	@Produces("application/json")
	public Response postcursus(@FormParam("id") int id, @FormParam("naam") String naam,
			@FormParam("beschrijving") String beschrijving) {
		Cursus newCursus = new Cursus(id, naam, beschrijving);
		CursusService service = ServiceProvider.getCursusService();

		if (service.addCursus(newCursus)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@DELETE
	@RolesAllowed({ "admin" })
	@Produces("application/json")
	public Response deletecursus(@FormParam("id")int id) {
		Cursus c = new Cursus(id);
		CursusService service = ServiceProvider.getCursusService();

		if (service.deleteCursus(c)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
