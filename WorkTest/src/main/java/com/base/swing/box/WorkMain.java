package com.base.swing.box;

import com.base.swing.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/30 16:04
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class WorkMain {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainJFrame frame = new MainJFrame();
                //frame.add();
                //frame.setJMenuBar(new MainMenu());

                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setVisible(true);
                //RightJPopupMenu rightJPopupMenu = new RightJPopupMenu();
            }
        });
    }
}
