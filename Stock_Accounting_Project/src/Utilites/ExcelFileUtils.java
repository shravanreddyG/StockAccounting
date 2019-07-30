package Utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtils 
{
	Workbook wb;
	
	//it load all the excel sheet
	public ExcelFileUtils() throws Throwable
	{
		FileInputStream fis=new FileInputStream("D:\\shravan\\Stock_Accounting_Project\\TestInputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	
	
	//row count
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	
	
	
	//column count
	public int colCount(String sheetname,int rownum)
	{
		return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}
	
	
	
	//reading data
	public String getData(String sheetname,int rownum,int colnum)
	{
		String data;
		
		if (wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		}
		return data;
	}
	
	
	//store data
	public void setData(String sheetname,int rownum,int colnum,String Status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row row=sh.getRow(rownum);
		org.apache.poi.ss.usermodel.Cell cell=row.createCell(colnum);
		cell.setCellValue(Status);
		if (Status.equalsIgnoreCase("PASS"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			row.getCell(colnum).setCellStyle(style);
		}
		else
			if (Status.equalsIgnoreCase("FAIL"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				row.getCell(colnum).setCellStyle(style);
			}
			else
				if (Status.equalsIgnoreCase("Not Executed"))
				{
					CellStyle style=wb.createCellStyle();
					Font font=wb.createFont();
					font.setColor(IndexedColors.BROWN.getIndex());
					font.setBold(true);
					style.setFont(font);
					row.getCell(colnum).setCellStyle(style);
				}
		FileOutputStream fos=new FileOutputStream("D:\\shravan\\Stock_Accounting_Project\\TestOutput\\OutPutSheet.xlsx");
		wb.write(fos);
		fos.close();
	}
	
	
	
	
	
	
}
