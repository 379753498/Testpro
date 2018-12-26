package org.com.testpro.Excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class Userpojo extends BaseRowModel {
    @ExcelProperty(index = 0,value = "name")
    private String name;
    @ExcelProperty(index = 1,value = "age")
    private String age;
    @ExcelProperty(index = 2,value = "email")
    private String email;
    @ExcelProperty(index = 3,value = "hello")
    private String hello;
}
