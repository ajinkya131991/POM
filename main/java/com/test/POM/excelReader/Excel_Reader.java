package com.test.POM.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class Excel_Reader 
{
	//Global Variables
	public FileOutputStream fileOut = null;
	public String path;
	public FileInputStream fis;
	public HSSFWorkbook workbook;
	public HSSFSheet sheet;
	public HSSFRow row;
	public HSSFCell cell;
	
	//Constructor of class
	public Excel_Reader(String path) //local variable path
	{
		this.path=path;  //this is used to point global variable to local variable (i.e Path)
		try
		{
			fis = new FileInputStream(path);    //Object of fileinputstream (Takes the path of excel sheet)
			workbook = new HSSFWorkbook(fis);   //object of Hssfworkbook  (Has all sheetnames)  Note: will take fis argument(To take the path) 
		}
		catch(Exception ex)	{ex.printStackTrace();}
		}
	
	@SuppressWarnings({"deprecation"})
	public String[][] getDataFromSheet(String sheetName, String ExcelName)
	{
		String dataSets[][] = null;
		try
		{
			HSSFSheet sheet = workbook.getSheet(sheetName); //Get sheet from excel Workbook
			int totalRow = sheet.getLastRowNum() + 1;  // Count rows
			int totalColumn = sheet.getRow(0).getLastCellNum(); //Count columns
			dataSets = new String[totalRow - 1][totalColumn]; // create array for row and column 
			
			for(int i=1;i<totalRow;i++) // loop for rows  i=1 bcoz our input starts from 2nd line in excel 
			{
				HSSFRow rows = sheet.getRow(i); // row = 1
				for(int j=0;j<totalColumn;j++) //loop for columns  j=0 coz we need all column cells
 				{
					HSSFCell cell = rows.getCell(j); 
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)   //if cell type is string den dis if condition will execute
						dataSets[i-1][j]=cell.getStringCellValue();
					
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)  //if cell type is int den dis if condition will execute	
					{
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i-1][j] = cellText;
					}
					else
						dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue()); //if cell type is boolean den dis if condition will execute	
				}
			}
			return dataSets;
		}
		
		catch(Exception ex)	{ex.printStackTrace();}
		return dataSets;
	}
	
	@SuppressWarnings({"deprecation"})
	public String getCellData(String sheetName, String colName,int rowNum)
	{
		try
		{
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			HSSFRow row = sheet.getRow(0); //No. of coloumns in the sheet 
			for(int i=0; i<row.getLastCellNum(); i++)
			{
				if (row.getCell(i).getStringCellValue().equals(colName));
				{
					col_Num =i;
					break;
				}
			}
			row = sheet.getRow(rowNum - 1); 
			HSSFCell cell = row.getCell(col_Num);
			if(cell.getCellType() == Cell.CELL_TYPE_STRING)
			{
				return cell.getStringCellValue();
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			{
				return "";
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
