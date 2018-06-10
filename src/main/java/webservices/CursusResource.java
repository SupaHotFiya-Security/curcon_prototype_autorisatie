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
//import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

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
	@Path("Firebase")
	@Produces("application/json")
	public String getcursussenFirebaseRoleCheck() {
		System.out.println(" Checken voor bevoeghdheid");
	//	if(FirebaseInit.FirebaseCheck("getcursussenFirebaseRoleCheck")){
			CursusService service = ServiceProvider.getCursusService();
			JsonArray cursusArray = buildJsonArray(service.getAllCursussen());
			System.out.println(" bevoegd");
			return cursusArray.toString();
	//	}
	//	else{
			//Niet bevoegd of geen geldig token.
	//		System.out.println("Niet bevoegd");
	//		return null ;
	//		return Response.ok().build();
	//	}	
	}

	@GET
	//@RolesAllowed({ "admin","student" })
	@Path("/check")
	@Produces("application/json")
	public String getcursussen() {
		if(!FirebaseInit.functionInUser("cursus_get")){
			return "Unauthorized...";
		}
		
		
		//CursusService service = ServiceProvider.getCursusService();
		//JsonArray cursusArray = buildJsonArray(service.getAllCursussen());
		//return cursusArray.toString();
				return "authorized+";
	}

	//FIXME REMOVE BELOW
//	@POST
//	@RolesAllowed({ "student" })
//	@Produces("application/json")
//	public Response postcursus(@FormParam("id") int id, @FormParam("naam") String naam,
//			@FormParam("beschrijving") String beschrijving) {
//		Cursus newCursus = new Cursus(id, naam, beschrijving);
//		CursusService service = ServiceProvider.getCursusService();
//
//		if (service.addCursus(newCursus)) {
//			return Response.ok().build();
//		} else {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//	}
//	@DELETE
//	@RolesAllowed({ "admin" })
//	@Produces("application/json")
//	
//	public Response deletecursus(@FormParam("id")int id) {
//		Cursus c = new Cursus(id);
//		CursusService service = ServiceProvider.getCursusService();
//
//		if (service.deleteCursus(c)) {
//			return Response.ok().build();
//		} else {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//	}

}
