package cell_res_set;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import variables.ResSet;
import variables.StringResSet;

public class StringCellResSet extends CellResSet<String>{

	@Override
	protected String getCellValue(Cell cell) {
		if(cell==null)
			return null;
		try{
			return cell.getStringCellValue();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	protected ResSet getListValues(List<String> list) {
		return new StringResSet(list);
	}

}
