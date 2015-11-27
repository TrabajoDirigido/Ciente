package cell_res_set;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import variables.DoubleResSet;
import variables.ResSet;

public class DoubleCellResSet extends CellResSet<Double> {

	@Override
	protected Double getCellValue(Cell cell) {
		if(cell==null)
			return null;
		try{
			return cell.getNumericCellValue();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	protected ResSet getListValues(List<Double> list) {
		return new DoubleResSet(list);
	}

}
