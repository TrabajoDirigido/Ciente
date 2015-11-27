package cell_res_set;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import variables.ObjectResSet;
import variables.ResSet;

public class ObjectCellResSet extends CellResSet<Object>{

	@Override
	protected Object getCellValue(Cell cell) {
		if(cell==null)
			return null;
		else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA)
			return cell.getCellFormula();
		else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			return null;
		else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN)
			return cell.getBooleanCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_ERROR)
			return cell.getErrorCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
			return cell.getNumericCellValue();
		return null;
	}

	@Override
	protected ResSet getListValues(List<Object> list) {
		return new ObjectResSet(list);
	}

}
