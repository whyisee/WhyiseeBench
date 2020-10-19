package com.base.swing.box;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/30 17:51
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class RightJPopupMenu  extends JPopupMenu{
    JMenu fileMenu;
    //JPopupMenu rightJpupupMenu ;
    JMenuItem openFile,closeFile,exit;
    JRadioButtonMenuItem copyFile,pasteFile;
    ButtonGroup rightButtonGroup;

    public RightJPopupMenu() {


        //rightJpupupMenu = new JPopupMenu();
        rightButtonGroup = new ButtonGroup();

        fileMenu = new JMenu("file");
        openFile = new JMenuItem("open");
        closeFile = new JMenuItem("close");
        fileMenu.add(openFile);
        fileMenu.add(closeFile);

        this.add(fileMenu);
        this.addSeparator();
        copyFile = new JRadioButtonMenuItem("copy");
        pasteFile = new JRadioButtonMenuItem("paste");
        rightButtonGroup.add(copyFile);
        rightButtonGroup.add(pasteFile);

        this.add(copyFile);
        this.add(pasteFile);
        this.addSeparator();

        exit = new JMenuItem("exit");
        this.add(exit);



    }



}
