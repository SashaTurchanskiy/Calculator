package com.company;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Calculator {
    public JFrame window = new JFrame("Calculator");
    public JTextField imput = new JTextField();

    public Calculator() {
        window.setSize(480, 405);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        enter_area();
        month_button();


        window.setVisible(true);


    }

    public void enter_area() {
        imput.setFont(new Font("Arial", Font.BOLD, 24));
        imput.setBounds(16, 10, 248, 36);
        imput.setBackground(Color.white);
        imput.setHorizontalAlignment(JTextField.RIGHT);

        window.add(imput);

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher((KeyEventDispatcher) new KeyDispatcher());
    }

    class KeyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                result();
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                imput.setText("");
            }
            return false;
        }
    }
    private void month_button()
    {
        int num = 0;
        String [] arr = {"1","2","3","*","4","5","6","/","7","8","9","-","(","0",")",".",(","),"="};
        JButton[] jButton_n = new JButton[arr.length];
        for (int e = 0; e < 5 ; e++)
        {
            for (int r = 0; r < 4 ; r++)
            {
                jButton_n[num] = new JButton();
                jButton_n[num].setFont(new Font("Arial",Font.PLAIN,26));
                jButton_n[num].setText(arr[num]);
                jButton_n[num].setMargin(new Insets(0,0,0,0));
                if (num < arr.length -1)
                {
                    jButton_n[num].setBounds(16+r*62,55+ e *62,60,60);
                }
                else
                {
                    jButton_n[num].setBounds(16+r*62, 55+ e *62, 122,60);
                }
                jButton_n[num].setFocusable(false);
                window.add(jButton_n[num]);

                ActionListener num_button = new GonumListener();
                jButton_n[num].addActionListener(num_button);

                if (num < arr.length - 1)
                {
                    num++;
                }
                else
                {
                    break;
                }
            }
        }
    }
    public class GonumListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getText();
            if (name == "=" || name == "С")
            {
                // ..
            }
            else
            {
                imput.setText(imput.getText()+name);
            }


            if (name == "=")
            {
                result();
            }


            if (name == "С")
            {
                imput.setText("");
            }

            window.repaint();

        }
    }
        private void result()
        {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("JavaScript");
            try
            {
                imput.setText("" + engine.eval(imput.getText()));
            }
            catch (ScriptException e1)
            {
                //...
            }
        }

        public static void main(String[] args)
        {
            new Calculator();
        }


}


