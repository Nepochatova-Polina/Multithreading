package com.company.Lab1;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class b {
    static Thread th1, th2;
    private static final Semaphore sem = new Semaphore(1);
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
        JTextField text = new JTextField("                                            ");
        JButton start1 = new JButton("Start first Thread");
        JButton start2 = new JButton("Start second Thread");
        JButton stop1 = new JButton("Stop first Thread");
        JButton stop2 = new JButton("Stop second Thread");
        JSlider slider = new JSlider();

        start1.addActionListener(e -> {
            th1 = new Thread(
                    () -> {
                        try {
                            sem.acquire();
                            slider.setValue(th1.getPriority() * 10);
                            text.setText("busy with a first thread");
                            stop2.setEnabled(false);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
            th1.setPriority(Thread.MIN_PRIORITY);
            th1.start();
        });

        start2.addActionListener(e -> {
            th2 = new Thread(
                    () -> {
                        try {
                            sem.acquire();
                            slider.setValue(th2.getPriority() * 10 - 10);
                            text.setText("busy with a second thread");
                            stop1.setEnabled(false);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
            th2.setPriority(Thread.MAX_PRIORITY);
            th2.start();
        });
        stop1.addActionListener(e -> {
            text.setText("  ");
            sem.release();
            stop2.setEnabled(true);
            th1.interrupt();
        });
        stop2.addActionListener(e -> {
            sem.release();
            text.setText("   ");
            stop1.setEnabled(true);
            th2.interrupt();

        });

        panel.add(start1);
        panel.add(start2);
        panel.add(stop1);
        panel.add(stop2);
        panel.add(text);
        panel.add(slider);
        return panel;
    }
}
