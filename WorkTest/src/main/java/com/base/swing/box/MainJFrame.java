package com.base.swing.box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * use for : 主界面
 *
 * @author zoukh
 * Created in:  2020/9/30 16:04
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MainJFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    //
    private JMenuBar mainMenu;
    private JPopupMenu rightJpupupMenu;

    //

    public MainJFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension  screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHight = screenSize.height;
        this.setSize(screenWidth/2,screenHight/2);
        this.setLocationByPlatform(true);

        //后期改成配置文件的方式
        Image img = new ImageIcon("WorkTest/target/classes/pic/notebook2.png").getImage();

        this.setIconImage(img);
        this.setTitle("test");

        mainMenu = new MainMenu();
        this.setJMenuBar(mainMenu);

        rightJpupupMenu = new RightJPopupMenu();
        MouseListener popupListener = new PopupListener(rightJpupupMenu);
        this.addMouseListener(popupListener);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
