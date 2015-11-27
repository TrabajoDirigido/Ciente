package cell_res_set;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import variables.BooleanResSet;
import variables.ResSet;

public class BooleanCellResSet extends CellResSet<Boolean>{

	@Override
	protected Boolean getCellValue(Cell cell) {
		if(cell==null)
			return null;
		try{
			return cell.getBooleanCellValue();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	protected ResSet getListValues(List<Boolean> list) {
		return new BooleanResSet(list);
	}

}
