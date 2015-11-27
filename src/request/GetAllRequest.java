package request;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class GetAllRequest extends ExcelRequest{

	public GetAllRequest(ResSet input, String path) throws IOException {
		super(input, path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResSet execute() throws ResSetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData,
			RequestBuilder requestBuilder) throws JSONException,
			ResSetException {
		// TODO Auto-generated method stub
		return null;
	}

}
