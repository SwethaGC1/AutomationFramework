package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelPractice {

	public static void main(String[] args) throws IOException {
		//Step1: open the file to java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		
		//step2: Load workbook
		Workbook wb= WorkbookFactory.create(fis);
		//step3: control over sheet
		Sheet sh= wb.getSheet("Contact");
		
		//step4: control over row
		//Row rw=sh.getRow(4);
		
		//step 5: create a cell in that row
		Row rw=sh.createRow(13);
		
		Cell ce=rw.createCell(2);
		//step6: set data into cell
		ce.setCellValue("wasa");
		
		//step7: open the docu,ent  in write mode
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\testdata.xlsx");
		
		//step8: write the data into cell
		wb.write(fos);
		System.out.println("written successfully");;
		
		

	}

}
