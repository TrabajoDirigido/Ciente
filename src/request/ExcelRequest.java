package request;

import interfaces.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import variables.ResSet;

public abstract class ExcelRequest extends Request{
	
	public ExcelRequest(ResSet input, String path) throws IOException{
		super(input);
		File myFile = new File(path);
		String extension = path.substring(path.lastIndexOf(".") + 1, path.length());
		FileInputStream fis = new FileInputStream(myFile);
		
		if(extension.equals("xlsx")){
			workbook = new XSSFWorkbook(fis);	
			sheet = workbook.getSheetAt(0);
		}
		else if(extension.equals("xls")){
			workbook = new HSSFWorkbook(fis);	
			sheet = workbook.getSheetAt(0);
		}
	}



	private Sheet sheet;
	private Workbook workbook;
	
	
	protected void setSheet(String name){
		sheet = workbook.getSheet(name);
	}
	
	protected void setSheet(int pos){
		sheet = workbook.getSheetAt(pos);
	}
	
	
	
	protected Sheet getSheet(){
		return sheet;
	}

}
