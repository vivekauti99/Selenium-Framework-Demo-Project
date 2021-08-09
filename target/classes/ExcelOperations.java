package com.DemoProject.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	String src = System.getProperty("user.dir") + "//ExcelFiles//data.xlsx";
	XSSFWorkbook workbook;

	public void writeToExcel(Map<Integer, Object[]> data) throws IOException {
		workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Books Data");

		Set<Integer> keyid = data.keySet();

		int rowid = 0;
		for (int key : keyid) {
			XSSFRow row = sheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(new File(src));
			workbook.write(fos);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readFromExcel() throws IOException {
		FileInputStream fis = new FileInputStream(new File(src));
		workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet("Books Data");
		int rowcount = sheet.getLastRowNum();
		int minRow = 1;
		double minPrice = Double.MAX_VALUE;
		for (int i = 1; i <= rowcount; i++) {
			if (sheet.getRow(i).getCell(2).getNumericCellValue() < minPrice) {
				minPrice = sheet.getRow(i).getCell(2).getNumericCellValue();
				minRow = i;
			}
		}
		String weblink = sheet.getRow(minRow).getCell(3).getStringCellValue();
		workbook.close();
		return weblink;
	}

}
