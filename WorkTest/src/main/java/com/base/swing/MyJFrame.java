package com.base.swing;

import javax.swing.*;
import java.awt.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 15:54
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MyJFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    public MyJFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension  screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHight = screenSize.height;
        this.setSize(screenWidth/2,screenHight/2);
        this.setLocationByPlatform(true);

        Image img = new ImageIcon("WorkTest/target/classes/pic/notebook2.png").getImage();
        //System.out.println("===test===>"+this.getClass().getResource("./"));
        //System.out.println("===test===>"+this.getClass().getResource("/"));
        //System.out.println("===test===>"+System.getProperty("user.dir"));
        //System.out.println("===test===>"+img);
        this.setIconImage(img);
        this.setTitle("test");
    }
}
