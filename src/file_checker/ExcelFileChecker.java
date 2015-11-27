package file_checker;

import java.io.File;


public class ExcelFileChecker implements FileChecker{

	public String check(String path) {
		String message = "";
		if(path.equals("")){
			message += "Debe ingresar un archivo\n";
		}
		else{
			File f = new File(path);
			if(!f.exists() || f.isDirectory()) { 
				message += "Ruta incorrecta al archivo\n";
			}
			else{
				String extension = path.substring(path.lastIndexOf(".") + 1, path.length());
				if(!extension.equals("xls") && !extension.equals("xlsx")){
					message += "Solo archivos excel aceptados\n";
				}
			}
		}
		return message;
	}
	

}
