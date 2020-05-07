package com.office;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/2/26 15:32
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ShowCreateTableH {
    public static void main(String[] args) throws Exception {
        ExcelUtils excelUtils=new ExcelUtils();
        String filePath= "D:\\桌面\\work\\脚本备份\\粉丝中心\\粉丝中心报表模型.xls";
        //excelUtils.readExcelSheetRow(filePath,2);
        //excelUtils.readExcelSheetAll(filePath,1);
        Sheet sheet = excelUtils.getSheetByNum(filePath,2);
        int rowNums = excelUtils.getLastRowNum(sheet);

        StringBuffer sb = new StringBuffer();
        //生成建表语句
        sb.append("\n"+"CREATE TABLE IF NOT EXISTS "+excelUtils.getSheetRowColString(sheet,3,1)+"\n");
        sb.append(" ( "+"\n");
       // sb.append(" "+excelUtils.getSheetRowColString(sheet,5,2)+" "+excelUtils.getSheetRowColString(sheet,5,3)+"\n");
        sb.append(" "+excelUtils.getSheetRowColString(sheet,5,2)+" string \n");

        for (int r = 6 ;r<=rowNums+1;r++) {
            // excelUtils.readExcelSheetRow(filePath,r);
            sb.append(","+excelUtils.getSheetRowColString(sheet,r,2)+" string \n");

        }
        sb.append(" ); "+"\n");

        //生成注释
/*        sb.append("comment on table  "+excelUtils.getSheetRowColString(sheet,3,1)+" is '"+excelUtils.getSheetRowColString(sheet,1,2)+"'; "+"\n");
        for (int r = 5 ;r<=rowNums+1;r++) {
            // excelUtils.readExcelSheetRow(filePath,r);
            sb.append("comment on column "+excelUtils.getSheetRowColString(sheet,3,1)+"."+excelUtils.getSheetRowColString(sheet,r,2)
                    +" is '"+excelUtils.getSheetRowColString(sheet,r,1)+excelUtils.getSheetRowColString(sheet,r,4)+"'; "+"\n");

        }*/

        System.out.println(sb);

    }
}
