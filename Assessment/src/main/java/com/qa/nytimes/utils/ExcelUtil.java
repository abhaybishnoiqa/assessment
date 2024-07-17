package com.qa.nytimes.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ExcelUtil {

    private static Workbook book;
    private static Sheet sheet;

    public static String getTestDataMethod(String sheetName){

        String data = null;
        try {
            FileInputStream ip = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
            //book = WorkbookFactory.create(ip);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);


            data= Arrays.toString(new Object[sheet.getLastRowNum()] [sheet.getRow(0).getLastCellNum()]);

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data =sheet.getRow(i+1).getCell(j).toString();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return data;
    }


}
