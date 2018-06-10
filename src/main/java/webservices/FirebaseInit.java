package webservices;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInit {
	// This is to connect to the firebase Project where Only ADMINS should login
	public static void FirebaseInit() throws IOException {
		// Only initializes an app if none exist yet.
		System.out.println("FIREBASEAPPS"+FirebaseApp.getApps().size() );
		if (FirebaseApp.getApps().size() == 0) {
			// FIXME This is an aboslute path
			String path = "C:\\Users\\Martin\\workspace\\Authorisatie_Test\\src\\main\\java\\webservices\\curconhu-firebase-adminsdk-gbgvn-e48f9044b6.json";

			FileInputStream serviceAccount = new FileInputStream(path);

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					// .setDatabaseUrl("https://curconhu.firebaseio.com")
					.build();

			FirebaseApp.initializeApp(options);
		}
	}
	public static boolean functionInUser(String function)  {
		//FIXME dit gebeurt in het filter
		//FirebaseInit();
		String user=AuthenticationFilter.requestCtx.getSecurityContext().getUserPrincipal().getName();
		System.out.println("FirebaseCheck for function:"+function+"&user"+user);
		//TODO dit moet de functies bij de gebruiker ophalen in de databsae.
		//if (function in getFunctionsByUser)
		return true;
		//else
		//return false;
	}
	
}
