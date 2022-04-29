package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static Workbook wb;
	public static Object[][] readExcel(String sheet) throws FileNotFoundException, IOException{
		wb = new XSSFWorkbook(new FileInputStream("src/test/resources/OpenEMRData.xlsx"));
		Sheet sh = wb.getSheet(sheet);
		Object[][] data = new Object[sh.getPhysicalNumberOfRows()-1][sh.getRow(0).getPhysicalNumberOfCells()];
		for(int r=1;r<sh.getPhysicalNumberOfRows();r++)
		{
			if(sh.getRow(r) != null)
			{
			System.out.println(r);
			Row row = sh.getRow(r);
			for(int c=0;c<row.getPhysicalNumberOfCells();c++)
			{
				if(row.getCell(c) != null)
				{
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				data[r-1][c] = cell.getStringCellValue();
				}
			}
			}
		}
		return data;
	}
	
	public static Object[][] readExcel(String sheet, int rows, int cols) throws FileNotFoundException, IOException{
		wb = new XSSFWorkbook(new FileInputStream("src/test/resources/OpenEMRData.xlsx"));
		Sheet sh = wb.getSheet(sheet);
		Object[][] data = new Object[rows][cols];
		for(int r=1;r<=rows;r++)
		{
			Row row = sh.getRow(r);
			for(int c=0;c<cols;c++)
			{
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				data[r-1][c] = cell.getStringCellValue();
				System.out.println(data[r-1][c]);
			}
		}
		System.out.println("Complete Data");
		return data;
	}
}
