package com.office;

import java.io.*;

//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;





/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/4 11:46
 * @version 1.0
 * @Modified By:deal Excel file
 * @used in: WorkTest
 */
public class ExcelTest {
    public static void main(String[] args) throws Exception {

        ExcelUtils excelUtils=new ExcelUtils();

      //  excelUtils.readExcelSheetRow("F:\\WXWL\\2014全网维系项目\\项目管理\\需求设计文档\\2019年\\2月\\关于产品支撑中心申请存量平台系统功能改造的需求@2019020100525\\表设计.xlsx",2);
        //excelUtils.readExcelSheetCol("G:tmp/a.xlsx",1);
        //excelUtils.readExcelSheetCol("G:tmp/粉丝明细原版.xlsx","A");
        excelUtils.readExcelSheetRowCol("G:tmp/a.xlsx",1,"B");

       // excelUtils.getSheetByNum("",1);

        //System.out.println("Test--------15:01--->:"+excelUtils.ch2int('B'));
        //testWrite();

    }

}
