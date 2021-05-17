package stockwatcher;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.annotation.WebServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@WebServlet("/stockwatcher/greet")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	public GreetingResponse greetServer(@RequestParam("file") MultipartFile file,
										RedirectAttributes redirectAttributes) throws IllegalArgumentException {
		// Verify that the input is valid.
		System.out.println("greetServer is called!");
		if (!FieldVerifier.isValidName(file.getName())) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		
		GreetingResponse response = new GreetingResponse();
		
		response.setServerInfo(getServletContext().getServerInfo());
		response.setUserAgent(getThreadLocalRequest().getHeader("User-Agent"));
		
		response.setGreeting("Hello, " + file.getName() + "!");
		
		return response;
	}
}
