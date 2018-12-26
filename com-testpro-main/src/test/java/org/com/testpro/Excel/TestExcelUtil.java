package org.com.testpro.Excel;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TestExcelUtil {
    List <Userpojo> usurpers;
    @Test(priority=0)
    public void testread() throws FileNotFoundException {

        File file = new File("E://helloword.xlsx");
        ExcelUtil  excelUtil = new ExcelUtil ();
        usurpers=   excelUtil.readExcelReturnListBean(file,Userpojo.class);
        for (Userpojo userpojo : usurpers) {
            System.out.println(userpojo);
        }
    }
    @Test(priority=1)
    public void testWriterExcel() throws FileNotFoundException {

        File file = new File("E://helloword.xlsx");
        ExcelUtil  excelUtil = new ExcelUtil();
        excelUtil.WriterExcelWithListBean(file,usurpers,Userpojo.class ,1,0,1);

    }
}
