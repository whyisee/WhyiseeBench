/*
package com.base.sstring;

import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.Constant;
import org.gjt.jclasslib.structures.constants.ConstantClassInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

*/
/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/10/28 21:13
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 *//*


public class Test {
    public static void main(String[] args) throws Exception {

        String filePath = "g:\\GenEntity.class";
        FileInputStream fis = new FileInputStream(filePath);

        DataInput di = new DataInputStream(fis);
        ClassFile cf = new ClassFile();
        cf.read(di);
        Constant[] infos = cf.getConstantPool();

        int count = infos.length;
        for (int i = 0; i < count; i++) {
            if (infos[i] != null) {
                System.out.print(i);
                System.out.print(" = ");
                System.out.print(infos[i].getVerbose());
                System.out.print(" = ");
               // System.out.println(infos[i].getTagVerbose());
                if(i == 362){
                    ConstantUtf8Info uInfo = (ConstantUtf8Info)infos[i];
                   // uInfo.setBytes("芝麻不开门!".getBytes());
                    infos[i]=uInfo;
                }
            }
        }
        cf.setConstantPool(infos);
        fis.close();
        File f = new File(filePath);
        ClassFileWriter.writeToFile(f, cf);
    }
}*/
