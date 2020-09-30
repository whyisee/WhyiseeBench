package com.base.swing.box;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * use for : 主菜单
 *
 * @author zoukh
 * Created in:  2020/9/30 16:10
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MainMenu extends JMenuBar {
    public MainMenu() {
        add(createFileMenu());

    }

    private JMenu createFileMenu(){
        JMenu menu = new JMenu("File(F)");
        menu.setMnemonic(KeyEvent.VK_F);

        JMenuItem item = new JMenuItem("New(N)",KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menu.add(item);
        item = new JMenuItem("Open(O)",KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menu.add(item);
        item = new JMenuItem("Save(S)",KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menu.add(item);
        item = new JMenuItem("Exit(E)",KeyEvent.VK_E);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menu.add(item);
        return menu;
    }

}
