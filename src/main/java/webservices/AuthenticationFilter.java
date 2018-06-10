package webservices;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	public static ContainerRequestContext requestCtx;
	
	@Override
	public void filter(ContainerRequestContext requestCtx) throws IOException {
		AuthenticationFilter.requestCtx=requestCtx;
		
		// Users are treated as guests, unless a valid JWT is provided
		boolean isSecure = requestCtx.getSecurityContext().isSecure();
		MySecurityContext msc = new MySecurityContext("Unknown", "guest", isSecure);
		// Check if the HTTP Authorization header is present and formatted
		// correctly
		String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println(authHeader+"44");
		if (authHeader != null ){// FIXME && authHeader.startsWith("Bearer ")) {
			// Extract the token from the HTTP Authorization header
			//FIXME String token = authHeader.substring("Bearer".length()).trim();
			String idToken = authHeader;
			try {
				// Validate the token
//				JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
//				Claims claims = parser.parseClaimsJws(token).getBody();
//				String user = claims.getSubject();
//				String role = claims.get("role").toString();
				//FIXME init 1x in de applicatie laten gebeuren waar?
				FirebaseInit.FirebaseInit();		
				
				FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
				String user =decodedToken.getEmail();
				System.out.println("FILTER DECODED EMAIL"+user);
			
				msc = new MySecurityContext(user,"r0leUnused",isSecure);
				System.out.println("Testtest end of  verify token here");

			} catch (JwtException | IllegalArgumentException e) {
				System.out.println("Invalid token, processing as guest!");
			} catch (FirebaseAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(msc+"43");
		requestCtx.setSecurityContext(msc);
	}
}
