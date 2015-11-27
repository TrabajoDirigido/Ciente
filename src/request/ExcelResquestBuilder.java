package request;

import java.io.IOException;

import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import builder.RequestBuilder;
import error.ResSetException;

public class ExcelResquestBuilder extends RequestBuilder {
	
	private String filePath;

	public ExcelResquestBuilder(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public Request build(JSONObject data) throws JSONException, ResSetException{
		if(data.has("method")){			
			try {
				switch(data.getString("method")){
				case "get":
					return getCompleteRequest(new ExcelGetRequest(null, filePath), data);
				case "existChart":
					return getCompleteRequest(new ExistChartRequest(null, filePath), data);
				case "dataChart":
					return getCompleteRequest(new ChartDataRequest(null, filePath), data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return super.build(data);	
	}

}
