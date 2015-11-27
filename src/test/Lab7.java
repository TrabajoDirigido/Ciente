package test;

import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import error.ResSetException;

import request.ExcelResquestBuilder;

public class Lab7 {
	
	public static void main(String[] args) throws JSONException, ResSetException {
		String sheet1 = new JSONObject().put("var", 0).put("type", "int").toString();
		String p1 = new JSONObject().put("var", "AVERAGE(C6:C23)").put("type", "string").toString();
		String p2 = new JSONObject().put("var", "AVERAGE(D6:D23)").put("type", "string").toString();
		String p3 = new JSONObject().put("var", "AVERAGE(E6:E23)").put("type", "string").toString();
		String p4 = new JSONObject().put("var", "AVERAGE(F6:F23)").put("type", "string").toString();
		
		String promedios = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"type\":\"formula\","+
				"\"sheet\":"+sheet1+","+
				"\"y\":["+24+"],"+
				"\"x\":["+3+",\"..\","+6+"]"+
				"}";
		
		String comparacionFormulaPromedio = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+promedios+","+
				"\"arg2\":["+p1+","+p2+","+p3+","+p4+"]"+
				"}";
		
		String P1Completa = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+comparacionFormulaPromedio+
				"}";
		String P1Incompleta = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"or\","+
				"\"vals\":"+comparacionFormulaPromedio+
				"}";
		
		
		String m1 = new JSONObject().put("var", "MIN(C6:C23)").put("type", "string").toString();
		String m2 = new JSONObject().put("var", "MIN(D6:D23)").put("type", "string").toString();
		String m3 = new JSONObject().put("var", "MIN(E6:E23)").put("type", "string").toString();
		String m4 = new JSONObject().put("var", "MIN(F6:F23)").put("type", "string").toString();
		
		
		String minimos = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"type\":\"formula\","+
				"\"sheet\":"+sheet1+","+
				"\"y\":["+25+"],"+
				"\"x\":["+3+",\"..\","+6+"]"+
				"}";
		
		String comparacionFormulaMinimos = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+minimos+","+
				"\"arg2\":["+m1+","+m2+","+m3+","+m4+"]"+
				"}";
		
		String andMinimos = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+comparacionFormulaMinimos+
				"}";
		String orMinimos = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"or\","+
				"\"vals\":"+comparacionFormulaMinimos+
				"}";
		
		String ma1 = new JSONObject().put("var", "MAX(C6:C23)").put("type", "string").toString();
		String ma2 = new JSONObject().put("var", "MAX(D6:D23)").put("type", "string").toString();
		String ma3 = new JSONObject().put("var", "MAX(E6:E23)").put("type", "string").toString();
		String ma4 = new JSONObject().put("var", "MAX(F6:F23)").put("type", "string").toString();
		
		
		String maximos = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"type\":\"formula\","+
				"\"sheet\":"+sheet1+","+
				"\"y\":["+26+"],"+
				"\"x\":["+3+",\"..\","+6+"]"+
				"}";
		
		String comparacionFormulaMaximos = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+maximos+","+
				"\"arg2\":["+ma1+","+ma2+","+ma3+","+ma4+"]"+
				"}";
		
		String andMaximos = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+comparacionFormulaMaximos+
				"}";
		String orMaximos = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"or\","+
				"\"vals\":"+comparacionFormulaMaximos+
				"}";
		
		
		String P2Completa = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":["+andMaximos+","+andMinimos+"]"+
				"}"; 
		
		String P2Incompleta = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"or\","+
				"\"vals\":["+orMaximos+","+orMinimos+"]"+
				"}"; 
		
		Request r1 = new ExcelResquestBuilder("temblores.xlsx").build(new JSONObject(P1Completa));
		Request r2 = new ExcelResquestBuilder("temblores.xlsx").build(new JSONObject(P1Incompleta));
		Request r3 = new ExcelResquestBuilder("temblores.xlsx").build(new JSONObject(P2Completa));
		Request r4 = new ExcelResquestBuilder("temblores.xlsx").build(new JSONObject(P2Incompleta));
		
		System.out.println("Tiene el alumno la P1 completa: "+r1.execute());
		System.out.println("Tiene el alumno algo en la P1: "+r2.execute());
		System.out.println("Tiene el alumno la P2 completa: "+r3.execute());
		System.out.println("Tiene el alumno algo en la P2: "+r4.execute());		
		
	}

}
