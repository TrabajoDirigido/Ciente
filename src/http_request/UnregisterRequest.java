package http_request;

public class UnregisterRequest extends HttpRequest{

	public UnregisterRequest(String ip, String token) {
		super("http://" + ip + ":8080/server_client/unregister/?token="
				+ token);
	}

}
