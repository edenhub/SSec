package Shared;

import edu.sysu.workflowlab.i18n.handler.NameMappingHandler;
import edu.sysu.workflowlab.i18n.i18nfactory.PropertiesFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lab on 2014/12/17.
 */

/**
 * 同之前demo中的使用一样，不同的是，对于不同的类文件，不需要显示写多个Handler，
 * 只要拿到NameMappingHandler实例，将要国际化的组件和更改的方法注册进去就可以了
 */
public class DPanel extends JPanel {
    public JButton button = new JButton("click");
    public JLabel label = new JLabel("welcome");

    public JButton btnChangeEn = new JButton("english");
    public JButton btnChange = new JButton("中文");
    public JButton btnChangeJp = new JButton("日本語の");

    PropertiesFactory factory = PropertiesFactory.getFactory();

    public DPanel(){
        this.add(button);
        this.add(label);
        this.add(btnChange);
        this.add(btnChangeEn);
        this.add(btnChangeJp);

        NameMappingHandler handler = NameMappingHandler.getInstance();

        NameMappingHandler.Component btnCmn =
                handler.new Component("setText","editor.btn",button);
        NameMappingHandler.Component labelCmn =
                handler.new Component("setText","editor.welcome",label);
        handler.registerComponent("DPanel_JButton",btnCmn);
        handler.registerComponent("DPanel_JLabel",labelCmn);


        factory.registerObserver(NameMappingHandler.class.getName(),handler);

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factory.changeProperties("zh_cn");
                factory.notice();
            }
        });

        btnChangeEn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factory.changeProperties("en_us");
                factory.notice();
            }
        });

        btnChangeJp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factory.changeProperties("ja_jp");
                factory.notice();
            }
        });
    }

    public static void testM(){
        synchronized(DPanel.class){
            try {
                DPanel.class.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void testM2(){
        DPanel.class.notify();
    }

}
