package request;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONException;
import org.json.JSONObject;

import variables.ResSet;
import variables.StringResSet;
import xml_chart.Chart;
import builder.RequestBuilder;
import error.ResSetException;

public class ChartDataRequest extends ExcelRequest{

	private String inputType;

	public ChartDataRequest(ResSet input, String path) throws IOException {
		super(input, path);
		inputType = "null";
	}

	@Override
	public ResSet execute() throws ResSetException {		
		XSSFDrawing drawing = ((XSSFSheet)this.getSheet()).createDrawingPatriarch();
		String ret = null;
		if(drawing.getCharts().isEmpty()){
			return new StringResSet(ret);
		}
		
		Chart chart = new Chart(drawing.getCharts().get(0).toString());
		if(inputType.equals("title"))
			new StringResSet(chart.getTitle());
		else if(inputType.equals("xLabel"))
			new StringResSet(chart.getXLabel());
		else if(inputType.equals("yLabel"))
			new StringResSet(chart.getYLabel());
		else if(inputType.equals("type"))
			new StringResSet(chart.getChartType());
		else if(inputType.equals("series"))
			new StringResSet(chart.getSeriesNames());
		
		return new StringResSet(ret);
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
		
		this.inputType = rawData.optString("type","");		
		
		return null;
	}

}
