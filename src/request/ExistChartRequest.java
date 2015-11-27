package request;

import interfaces.Request;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONException;
import org.json.JSONObject;

import variables.BooleanResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;
public class ExistChartRequest extends ExcelRequest{

	public ExistChartRequest(ResSet input, String path) throws IOException {
		super(input, path);
	}

	@Override
	public ResSet execute() throws ResSetException {
		XSSFDrawing drawing = ((XSSFSheet)this.getSheet()).createDrawingPatriarch();
		if(drawing.getCharts().isEmpty()){
			return new BooleanResSet(false);
		}
		return new BooleanResSet(true);
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
		Request r = new ExistChartRequest(null, "ejemplo.xlsx");
		System.out.println(r.execute());
	}
	
}
