package pl.andus.SimpleEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame implements ActionListener {

    private static JFrame frame;
    public static JEditorPane editArea = new JEditorPane();


    public static void main(String[] args) {
        frame = new JFrame("Simple Editor");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception ignored) {

        }

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem fileNew = new JMenuItem("New");
        JMenuItem fileSave = new JMenuItem("Save");
        JMenuItem fileOpen = new JMenuItem("Open");

        JMenu editMenu = new JMenu("Edit");

        JMenuItem editCut = new JMenuItem("Cut");
        JMenuItem editCopy = new JMenuItem("Copy");
        JMenuItem editPaste = new JMenuItem("Paste");
        JMenuItem editAll = new JMenuItem("Select All");

        JMenu closeMenu = new JMenu("Close");

        fileMenu.add(fileNew);
        fileMenu.add(fileSave);
        fileMenu.add(fileOpen);

        editMenu.add(editCut);
        editMenu.add(editCopy);
        editMenu.add(editPaste);
        editMenu.add(editAll);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(closeMenu);

        JPanel panel = new JPanel();

        editArea.setBounds(0,0,800,600);
        editArea.setFont(new Font("Courier", Font.PLAIN, 16));

        panel.setBounds(0, 0, 800, 600);
        panel.add(editArea);
        frame.setJMenuBar(menuBar);

        frame.add(editArea);

        frame.setPreferredSize(new Dimension(800, 700));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        fileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser j = new JFileChooser("f:");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
                j.setFileFilter(filter);

                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter wr = new FileWriter(fi, false);

                        BufferedWriter w = new BufferedWriter(wr);

                        w.write(editArea.getText());

                        w.flush();
                        w.close();
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(frame, evt.getMessage());
                    }
                } else if (r == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
                }
            }
        });

        fileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser j = new JFileChooser("f:");

                int r = j.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        String s1;
                        StringBuilder sl;

                        FileReader fr = new FileReader(fi);

                        BufferedReader br = new BufferedReader(fr);

                        sl = new StringBuilder(br.readLine());

                        while ((s1 = br.readLine()) != null) {
                            sl.append("\n").append(s1);
                        }

                        editArea.setText(sl.toString());
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(frame, evt.getMessage());
                    }
                }
            }
        });

        editCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editArea.cut();
            }
        });

        editCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editArea.copy();
            }
        });

        editPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editArea.paste();
            }
        });

        editAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editArea.selectAll();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}

