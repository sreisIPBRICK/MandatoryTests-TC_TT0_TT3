/*package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GetData {

	String path;
	int row, column;
	String datastring;
	WebDriver driver;
	
 	public void GetPath(){
	System.out.print("Enter the path of your Configuration file(/home/sreis/workspace/data.xls):");
	BufferedReader reader;

	reader = new BufferedReader(new InputStreamReader(System.in));

	try {
		path = reader.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	System.out.println(path);
}
	
 	public String ReadData(int sheet, int row, int column) {

		File src = new File(path);

		try {
			// Workbook is a class in Jexcel which will take file as an argument
			// and getWork//book is a predefined method which will read the
			// workbook and will return the w//Workbook object

			Workbook wb = Workbook.getWorkbook(src);

			// Workbook is loaded now we have to load sheet so using workbook
			// object (wb) we// can call getSheet method which will take index
			// as an argument and will load t//he sheet, we can also specify the
			// sheetname also

			Sheet sh1 = wb.getSheet(sheet);

			// Sheet is loaded then we have to read cell so using sh1 object
			// call getCell me//thod which we take two arguments
			// getCell(column,row)

			Cell c1 = sh1.getCell(column, row);

			// Cell is loaded then using getContents method we have to extract
			// the data usin//g getContents() methods
			// this method will always return you String.
			// now you are done

			String data1 = c1.getContents();
			datastring = data1;
		} catch (BiffException e) {

			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datastring;
	
}

 	public void TimeOut(String timeString) throws InterruptedException{
		//String timeString;//=this.ReadData(0, 6, 1);
		long time=Integer.parseInt(timeString);
		time=time*1000;
		Thread.sleep(time);
	}
		
}
*/