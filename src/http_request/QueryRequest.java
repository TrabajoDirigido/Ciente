package http_request;

import org.json.JSONObject;

public class QueryRequest extends HttpRequest{

	public QueryRequest(String ip, JSONObject data) {
		super("http://"+ip+":8080/server_client/receive_client_response/", data);
	}
	
	public static void main(String[] args) {
		HttpRequest r = new QueryRequest("192.168.0.2", new JSONObject().put("mensaje", "hola").put("babita", "rica"));
		r.sendRequest();
	}

}
