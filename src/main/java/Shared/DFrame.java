package Shared;

import javax.swing.*;

/**
 * Created by lab on 2014/12/17.
 */
public class DFrame {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel panel = new DPanel();


//        panel.setLayout(new GridLayout());

        frame.add(panel);

        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
