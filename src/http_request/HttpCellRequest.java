package http_request;

public class HttpCellRequest extends HttpRequest{

	public HttpCellRequest(String ip, String value, String token, String requestId) {
		super("http://" + ip + ":8080/server_client/cell/?value="+value+
				"&token="+token+"&id="+requestId);
	}
}
