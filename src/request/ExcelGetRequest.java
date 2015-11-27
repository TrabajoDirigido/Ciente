package request;

import interfaces.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.apache.poi.ss.usermodel.Cell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import variables.PairResSet;
import variables.ResSet;
import builder.RequestBuilder;
import cell_list.CellListException;
import cell_list.DefaultCellList;
import cell_list.ExcelCellList;
import cell_list.PuntualCellList;
import cell_res_set.BooleanCellResSet;
import cell_res_set.CellResSet;
import cell_res_set.DoubleCellResSet;
import cell_res_set.FormulaCellResSet;
import cell_res_set.ObjectCellResSet;
import cell_res_set.StringCellResSet;
import error.ResSetException;

public class ExcelGetRequest extends ExcelRequest{
	
	private String inputType;

	public ExcelGetRequest(ResSet input, String path) throws IOException {
		super(input, path);
		this.inputType = "null";
	}

	@Override
	public ResSet execute() throws ResSetException {
		if(!input.getDataType().equals("Pair")){
			throw new ResSetException("Get Request Data is not a "+Pair.class.toString()+", instead: "+input.getDataType());
		}
		
		@SuppressWarnings("unchecked")
		PairResSet<Integer, Integer> inputSet = (PairResSet<Integer, Integer>)input;
		
		if(this.inputType.equals("string")){
			return new StringCellResSet().getResSet(inputSet.getPairIterator(), this.getSheet());
		}
		else if(this.inputType.equals("float")){
			return new DoubleCellResSet().getResSet(inputSet.getPairIterator(), this.getSheet());
		}
		else if(this.inputType.equals("formula")){
			return new FormulaCellResSet().getResSet(inputSet.getPairIterator(), this.getSheet());
		}
		else if(this.inputType.equals("boolean")){
			return new BooleanCellResSet().getResSet(inputSet.getPairIterator(), this.getSheet());
		}
		
		CellResSet<?> resSetBuilder = this.getCellsResSet(inputSet.getPairIterator());
		
		return resSetBuilder.getResSet(inputSet.getPairIterator(), this.getSheet());
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		JSONArray xs = rawData.getJSONArray("x");
		JSONArray ys = rawData.getJSONArray("y");
		
		if(rawData.getJSONObject("sheet").getString("type").equals("string")){
			this.setSheet(rawData.getJSONObject("sheet").getString("var"));
		}
		else{
			this.setSheet(rawData.getJSONObject("sheet").getInt("var"));
		}
		
		this.inputType = rawData.optString("type","");
		
		ExcelCellList<Integer> x = getCellList(xs);
		ExcelCellList<Integer> y = getCellList(ys);
		
		List<Pair<Integer, Integer>> res = new ArrayList<>();
		try {
			res = x.getPairs(y);
		} catch (CellListException e) {
			e.printStackTrace();
		}
		
		return new PairResSet<Integer,Integer>(res);
	}
	
	private ExcelCellList<Integer> getCellList(JSONArray a){		
		if(a.length()==1){
			return new PuntualCellList<Integer>(a.getInt(0));
		}
		else if(a.optString(1).equals("..")){
			List<Integer> data = new ArrayList<>();
			for(int i=a.getInt(0);i<=a.getInt(2);i++){
				data.add(i);
			}
			return new DefaultCellList<Integer>(data);
		}
		
		List<Integer> data = new ArrayList<>();
		for(int i = 0; i< a.length();i++){
			data.add(a.getInt(i));
		}
		
		return new DefaultCellList<Integer>(data);
	}
	
	private CellResSet<?> getCellsResSet(List<Pair<Integer, Integer>>  cells){
		if(cells.isEmpty()){
			return new ObjectCellResSet();
		}
		Pair<Integer, Integer> firstPair = cells.get(0);
		
		int xx = firstPair.getKey() - 1;
		int yy = firstPair.getValue() - 1;			
		Cell firstCell = getSheet().getRow(yy).getCell(xx);
		
		int type;
		if(firstCell==null) return new ObjectCellResSet();;
		type = firstCell.getCellType();
		
		for(Pair<Integer,Integer> cell : cells){
			int x = cell.getKey() - 1;
			int y = cell.getValue() - 1;
			
			Cell c = getSheet().getRow(y).getCell(x);
			if(c==null || c.getCellType()!=type){
				return new ObjectCellResSet();
			}
		}
		
		switch(type){
		case Cell.CELL_TYPE_BLANK:
			return new ObjectCellResSet();
		case Cell.CELL_TYPE_BOOLEAN:
			return new BooleanCellResSet();
		case Cell.CELL_TYPE_NUMERIC:
			return new DoubleCellResSet();
		case Cell.CELL_TYPE_STRING:
			return new StringCellResSet();
		case Cell.CELL_TYPE_FORMULA:
			return new FormulaCellResSet();
		case Cell.CELL_TYPE_ERROR:
			return new ObjectCellResSet();
		default:
			return new ObjectCellResSet();
		}	
	}
	
	public static void main(String[] args) throws IOException, ResSetException {
		String i = new JSONObject().put("var", 2).put("type", "int").toString();
		String i2 = new JSONObject().put("var", 5).put("type", "int").toString();
		
		String uno = new JSONObject().put("var", 1).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String tres = new JSONObject().put("var", 3).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		
		String valorD = new JSONObject().put("var", 321.0).put("type", "float").toString();
		
		String f = new JSONObject().put("var", "false").put("type", "bool").toString();
		String sheet = new JSONObject().put("var", 0).put("type", "int").toString();
		
		String get = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"sheet\":"+sheet+","+
				"\"x\":["+1+"],"+
				"\"y\":["+1+","+2+","+3+","+4+"]"+
				"}";
		
		String sort = "{" +
				"\"id\":2,"+
				"\"method\":\"sort\","+
				"\"des\":"+f+","+
				"\"vals\":"+get+
				"}";
		
		String compare = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+get+","+
				"\"arg2\":"+sort+
				"}";
		
		String compare2 = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+get+","+
				"\"arg2\":["+valorD+","+valorD+","+valorD+","+valorD+"]"+
				"}";
		
		String columnaOrdenada = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+compare+
				"}";
		
		//System.out.println(get);
		
		String c = "{\"method\": \"logic\", \"id\": 1, \"vals\": {\"method\": \"compare\", \"id\": 2, \"arg1\": {\"method\": \"get\", \"id\": 3, \"y\": [{\"var\": 1, \"type\": \"int\"}, {\"var\": 2, \"type\": \"int\"}, {\"var\": 3, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}], \"x\": [{\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}]}, \"arg2\": {\"method\": \"sort\", \"id\": 4, \"vals\": {\"method\": \"get\", \"id\": 5, \"y\": [{\"var\": 1, \"type\": \"int\"}, {\"var\": 2, \"type\": \"int\"}, {\"var\": 3, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}], \"x\": [{\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}, {\"var\": 4, \"type\": \"int\"}]}}}, \"type\": \"and\"}";
		
		String algunValorIgual = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+compare2+
				"}";
		
		String sheet2 = new JSONObject().put("var", 1).put("type", "int").toString();
		
		String newGet = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"type\":\"boolean\","+
				"\"sheet\":"+sheet2+","+
				"\"x\":["+1+"],"+
				"\"y\":["+1+",\"..\","+4+"]"+
				"}";
		
		Request r = new ExcelResquestBuilder("ejemplo.xlsx").build(new JSONObject(newGet));
		
		ResSet resSet = r.execute();
		System.out.println(resSet.getDataType());
		System.out.println(resSet.toJSONArray());
		
		
		String nullVal = new JSONObject().put("type", "null").toString();
		String filter ="{" +
				"\"id\":1,"+
				"\"method\":\"filter\","+
				"\"type\":\"equal\","+
				"\"var\":"+nullVal+","+
				"\"vals\":"+newGet+
				"}";
		
		Request request = new ExcelResquestBuilder("ejemplo.xlsx").build(new JSONObject(filter));
		//System.out.println(request.execute());
		
		String newCompare = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+newGet+","+
				"\"arg2\":["+f+"]"+
				"}";
		
		request = new ExcelResquestBuilder("ejemplo.xlsx").build(new JSONObject(newCompare));
		System.out.println(request.execute());
	}

}
