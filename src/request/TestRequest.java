package request;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONException;
import org.json.JSONObject;

import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class TestRequest extends ExcelRequest{

	public TestRequest(ResSet input, String path) throws IOException {
		super(input, path);
	}

	@Override
	public ResSet execute() throws ResSetException {
		Row r = this.getSheet().getRow(0);
		Cell c = r.getCell(4);
		System.out.println(c.getBooleanCellValue());
		return null;
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData,
			RequestBuilder requestBuilder) throws JSONException,
			ResSetException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws ResSetException, IOException {
		new TestRequest(null, "ejemplo.xlsx").execute();
	}

}
