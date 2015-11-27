package cell_res_set;

import org.apache.poi.ss.usermodel.Cell;

public class FormulaCellResSet extends StringCellResSet{
	@Override
	protected String getCellValue(Cell cell) {
		if(cell==null)
			return null;
		try{
			return cell.getCellFormula();
		}catch(Exception e){
			return null;
		}
	}
	
}
