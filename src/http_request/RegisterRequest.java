package http_request;

public class RegisterRequest extends HttpRequest{

	public RegisterRequest(String ip, String name1, String name2) {
		super("http://" + ip + ":8080/server_client/register/?names="
				+ getCodedNames(name1, name2));
	}
	
	private static String getCodedNames(String name1, String name2) {
		if (name1 == null || name1.equals("")) {
			return name2.replace(" ", "%20");
		} else if (name2 == null || name2.equals("")) {
			return name1.replace(" ", "%20");
		}
		name1 = name1.replace(" ", "%20");
		name2 = name2.replace(" ", "%20");
		return name1 + "," + name2;
	}

}
