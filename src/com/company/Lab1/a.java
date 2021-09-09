package com.company.Lab1;

import javax.swing.*;

public class a {
    static Thread th1, th2;

    public static void main(String[] args) {
        JFrame win = new JFrame();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(500, 400);

        JPanel panel = getjPanel();

        win.setContentPane(panel);
        win.setVisible(true);
    }

    private static JPanel getjPanel() {
        JPanel panel = new JPanel();
        JTextField text = new JTextField("                  ");
        JButton start = new JButton("Start");
        JSlider slider = new JSlider();
        start.addActionListener(e -> {
            th2 = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            th1 = new Thread(
                    () -> {
                        while (true) {
                            text.setText(th1.getPriority() + " : " + th2.getPriority());
                            synchronized (slider) {
                                if (th1.getPriority() > th2.getPriority()) {
                                    slider.setValue(100 - th1.getPriority() * 10);
                                } else {
                                    slider.setValue(th2.getPriority() * 10);
                                }
                                try {
                                    Thread.sleep(1000);

                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                            }
                        }
                    });
            th1.setPriority(Thread.MIN_PRIORITY);
            th2.setPriority(Thread.MAX_PRIORITY);
            th1.start();
            th2.start();
        });

        JButton firstPlus = new JButton("first thread +");
        firstPlus.addActionListener(e -> th1.setPriority(th1.getPriority() + 1));
        JButton firstMinus = new JButton("first thread -");
        firstMinus.addActionListener(e -> th1.setPriority(th1.getPriority() - 1));


        JButton secondPlus = new JButton("second thread +");
        secondPlus.addActionListener(e -> th2.setPriority(th2.getPriority() + 1));
        JButton secondMinus = new JButton("second thread -");
        secondMinus.addActionListener(e -> th2.setPriority(th2.getPriority() - 1));

        panel.add(firstMinus);
        panel.add(firstPlus);
        panel.add(secondMinus);
        panel.add(secondPlus);
        panel.add(start);
        panel.add(text);
        panel.add(slider);
        return panel;
    }
}