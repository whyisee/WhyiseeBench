package com.base.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 16:03
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class FirstSwing {


    public static void main(String[] args){
        final Random random = new Random();
        final JButton button = new JButton("Change Color");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(new Color(random.nextInt()));
            }
        });

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyJFrame frame = new MyJFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(button);
                frame.setVisible(true);

            }
        });
    }
}
