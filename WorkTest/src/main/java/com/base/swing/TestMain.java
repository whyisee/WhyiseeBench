package com.base.swing;

import javax.swing.*;
import java.awt.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 15:49
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestMain {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyJFrame frame = new MyJFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

