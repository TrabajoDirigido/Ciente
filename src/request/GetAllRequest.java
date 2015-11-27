package request;

import interfaces.Request;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONException;
import org.json.JSONObject;

import variables.IntegerResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class GetAllRequest extends ExcelRequest{

	public GetAllRequest(ResSet input, String path) throws IOException {
		super(input, path);
	}

	@Override
	public ResSet execute() throws ResSetException {
		
		Sheet sheet = this.getSheet();
		int count = 0;
        for (@SuppressWarnings("unused") Row row : sheet) {
            count ++;
        }
		
		return new IntegerResSet(count);
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData,
			RequestBuilder requestBuilder) throws JSONException,
			ResSetException {
		if(rawData.getJSONObject("sheet").getString("type").equals("string")){
			this.setSheet(rawData.getJSONObject("sheet").getString("var"));
		}
		else{
			this.setSheet(rawData.getJSONObject("sheet").getInt("var"));
		}
		
		return null;
	}
	
	public static void main(String[] args) throws IOException, ResSetException {
		Request r = new GetAllRequest(null, "ejemplo.xlsx");
		ResSet set = r.execute();
		System.out.println(set);
	}

}
