package com.base.swing.box;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/10/9 11:46
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class PopupListener extends MouseAdapter {
    JPopupMenu popupMenu;
    PopupListener(JPopupMenu popupMenu){
        this.popupMenu = popupMenu;
    }

    public void mousePressed(MouseEvent e){
        showPopupMneu(e);
    }
    public void mouseReleased(MouseEvent e){
        showPopupMneu(e);
    }
    private void showPopupMneu(MouseEvent e){
        if(e.isPopupTrigger()){
            System.out.println("===test===>"+111);
            popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }
}
