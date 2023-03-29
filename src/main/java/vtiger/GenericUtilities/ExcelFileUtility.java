package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	/**
	 * This method used to read data from excel file based on row and cell value
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String excelFileUtility(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantsUtility.ExcelFilePath);
		//Workbook wb=WorkbookFactory.create(fis);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheet);
		Row rw=sh.getRow(row);
		Cell ce=rw.getCell(cell);
		String value= ce.getStringCellValue();
		wb.close();
		return value;
	}
	
	/**
	 * This method will be used to get total rows utilized
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantsUtility.ExcelFilePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheet);
		int rowcount = sh.getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	
	/**
	 * this method is used to write data in to excel sheet
	 * @param sheet
	 * @param row
	 * @param cell
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheet,int row,int cell,String value) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IConstantsUtility.ExcelFilePath);
		Workbook wb=WorkbookFactory.create(fis);
		Row rw=wb.getSheet(sheet).getRow(row);
		rw.createCell(cell).setCellValue(value);
		FileOutputStream fos=new FileOutputStream(IConstantsUtility.ExcelFilePath);
		wb.write(fos);
		
		System.out.println("data written");
		wb.close();
		
	}
	
	/**
	 * This method will load the data from excel sheet to data provider
	 * @param sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipleData(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantsUtility.ExcelFilePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetname);
		int lastRow=sh.getLastRowNum();
		int lastCell=sh.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}

		return data;
	}
}
