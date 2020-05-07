/*
package com.office;

*/
/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/4/1 15:15
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 *//*

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XLSX2CSV {
    */
/**
     * Uses the XSSF Event SAX helpers to do most of the work of parsing the Sheet
     * XML, and outputs the contents as a (basic) CSV.
     *//*

    private List<String[]> rows = new ArrayList<String[]>();

    private final OPCPackage xlsxPackage;

    */
/**
     * Number of columns to read starting with leftmost
     *//*

    private int minColumns;

    */
/**
     * Destination for data
     *//*

    private class SheetToCSV implements SheetContentsHandler {
        private String[] record;
        private int minColumns;
        private int thisColumn = 0;

        public SheetToCSV(int minColumns) {
            super();
            this.minColumns = minColumns;
        }

        @Override
        public void startRow(int rowNum) {
            record = new String[this.minColumns];
            // System.out.println("################################:"+rowNum);
        }

        public void endRow(int rowNum) {
            thisColumn = 0;
            rows.add(this.record);
            // System.out.println("**********************************");

        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            if (thisColumn < this.minColumns)
                record[thisColumn] = formattedValue;
            thisColumn++;
            // System.out.print(formattedValue);
            // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");

        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
            // Skip, no headers or footers in CSV
        }

        public void cell(String arg0, String arg1) {
            // TODO Auto-generated method stub

        }

        public void endRow() {
            // TODO Auto-generated method stub

        }

    }

    */
/**
     * Creates a new XLSX -> CSV converter
     *
     * @param pkg        The XLSX package to process
     * @param output     The PrintStream to output the CSV to
     * @param minColumns The minimum number of columns to output, or -1 for no
     *                   minimum
     *//*

    public XLSX2CSV(OPCPackage pkg, int minColumns) {
        this.xlsxPackage = pkg;
        this.minColumns = minColumns;
    }

    */
/**
     * Parses and shows the content of one sheet using the specified styles and
     * shared-strings tables.
     *
     * @param styles
     * @param strings
     * @param sheetInputStream
     *//*

    public void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, SheetContentsHandler sheetHandler,
                             InputStream sheetInputStream) throws IOException, ParserConfigurationException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    */
/**
     * Initiates the processing of the XLS workbook file to CSV.
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     *//*

    public List<String[]> process() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            String sheetName = iter.getSheetName();
            // this.output.println();
            // this.output.println(sheetName + " [index=" + index + "]:");
            processSheet(styles, strings, new SheetToCSV(this.minColumns), stream);
            stream.close();
            ++index;
        }
        return this.rows;
    }

    */
/**
     * 得到excel的记录
     *
     * @param excelPath
     * @param minColumns 输出多少列
     * @return
     * @throws Exception
     *//*

    public static List<String[]> getRecords(String excelPath, int minColumns) throws Exception {
        File xlsxFile = new File(excelPath);
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return null;
        }
        // The package open is instantaneous, as it should be.
        OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, minColumns);
        List<String[]> list = xlsx2csv.process();
        p.close();
        return list;
    }

    public static void main(String[] args) throws Exception {

        List<String[]> list = getRecords("G:tmp/粉丝明细原版.xlsx", 4);
        FileOutputStream fs = new FileOutputStream(new File("G:tmp/wx_user.txt"));
        OutputStreamWriter osw= new OutputStreamWriter(fs);
        for (int i = 0; i < list.size(); i++) {

            for (String a : list.get(i)) {
                //System.out.print(a + " ");
                osw.write(a + " ");
            }
            //System.out.println();

        }
        osw.close();
    }
}*/
