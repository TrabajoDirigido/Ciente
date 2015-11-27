package cell_res_set;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import error.ResSetException;

import variables.ResSet;

public abstract class CellResSet<E> {
	
	protected abstract E getCellValue(Cell cell); 
	protected abstract ResSet getListValues(List<E> list);
	
	public ResSet getResSet(List<Pair<Integer, Integer>> pairIterator, Sheet sheet) throws ResSetException{
		List<E> l = new ArrayList<>();
		for(Pair<Integer, Integer> cell : pairIterator){
			int x = cell.getKey() - 1;
			int y = cell.getValue() - 1;	
			Row r = sheet.getRow(y);
			
			E value;
			if(r != null){
				Cell c = r.getCell(x);				
				value = getCellValue(c);
			}
			else value = null;
			
			if(value==null){
				//throw new ResSetException("Cell "+x+", "+y+" dont match correct type");
				//System.out.println("Cell "+x+", "+y+" dont match correct type");
			}			
			l.add(value);
		}	
		return getListValues(l);
	}

}
