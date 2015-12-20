package request;

import http_request.HttpRequest;
import http_request.QueryRequest;
import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import variables.ResSet;
import controller.MainController;
import error.ResSetException;

public class RequestHandler {
	
	MainController controller;
	
	public RequestHandler(MainController mainController){
		this.controller = mainController;
	}

	public void handleRequest(String json) {
		try {
			Request request = new ExcelResquestBuilder(controller.getFilename()).build(new JSONObject(json));
			ResSet resSet = request.execute();
			
			JSONObject result = new JSONObject();
			result.put("result", resSet.toJSONArray());
			result.put("id", request.getId());
			result.put("origin", controller.getToken());
					
			System.out.println("result: "+result);
			
			HttpRequest httpRequest = new QueryRequest(controller.getIp(), result);
			String response = httpRequest.sendRequest();
			
			if(response==null){
				response = "Error sending query! D:";
			}
			else if(response.equals("")){
				response = "Query resolved";
			}
			
			controller.statusMessage(response);
			
		} catch (JSONException | ResSetException e) {
			e.printStackTrace();
		}
	}
}
