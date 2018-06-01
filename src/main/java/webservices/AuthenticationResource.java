package webservices;

import java.io.IOException;

///import java.awt.Window;
//import java.security.Key;
//import java.awt.PageAttributes.MediaType;
//import java.awt.RenderingHints.Key;
//import java.util.Calendar;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

//import javax.ws.rs.core.Response;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.impl.crypto.MacProvider;
import persistence.UserDAO;
//import FirebaseInit;

@Path("/authentication")
public class AuthenticationResource {
	//
	@GET
	@Path("/{email}")
	@Produces({ MediaType.TEXT_PLAIN })
	// @Consumes(MediaType.A final static public Key key =
	// MacProvider.generateKey();PPLICATION_FORM_URLENCODED)
	public String authenticateUser(@PathParam("email") String email) {
		// try {
		// Authenticate the user against the database
		UserDAO dao = new UserDAO();
		String role = dao.findRoleForUsernameAndPassword(email);

		if (role == null) {
			throw new IllegalArgumentException("No user found!");
		} else {
			System.out.println(role);
			return role;
		}
	}
	
	@GET
	@Path("/token/{token}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String DecodeToken(@PathParam("token") String idToken) throws FirebaseAuthException, IOException {
		//FIXME Deze INIT moet maar 1x gebeuren terwijl de applicatie runt.
		FirebaseInit init = new FirebaseInit();
	
		
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
		String uid = decodedToken.getUid();
		
		return decodedToken.getEmail();

	}
	// // Issue a token for the user
	// Calendar expiration = Calendar.getInstance();
	// expiration.add(Calendar.MINUTE, 30);
	//
	// String token = Jwts.builder()
	// .setSubject(username)
	// .claim("role", role)
	// .setExpiration(expiration.getTime())
	// .signWith(SignatureAlgorithm.HS512, key)
	// .compact();
	// Return the token on the response
	// return Response.ok(token).build();
	//// } catch (JwtException | IllegalArgumentException e) {
	// return Response.status(Response.Status.UNAUTHORIZED).build();
	// }
}
// }