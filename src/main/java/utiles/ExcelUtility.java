package utiles;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public static Object[][] getExcelData(String filepath, String sheetname) throws EncryptedDocumentException, IOException{
		FileInputStream file = new FileInputStream("F:\\Download\\SwagLabData.xlsx");
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet("LoginData");
		
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int i=1; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				data[i-1][j] = sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
		
	}
	
}
