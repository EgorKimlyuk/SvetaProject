import javax.swing.*;
import javax.swing.filechooser.FileFilter;
//import javax.xml.bind.annotation.XmlList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Start extends JFrame {

    static ArrayList<String[]> XMLList = new ArrayList<>();

    public static void main(String[] args) {

        Start s = new Start();
        GridBagLayout gridBagLayout = new GridBagLayout();
        s.setLayout(gridBagLayout);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(s);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        s.setSize(500,300);
        s.setLocationRelativeTo(null);
        s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        s.setVisible(true);
        s.setTitle("XML TO XMI CONVERTER");
        JButton jButton = new JButton("Choose");

        GridBagConstraints constraints1 = new GridBagConstraints();

        constraints1.fill = GridBagConstraints.EAST;
        constraints1.anchor = GridBagConstraints.EAST;
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        s.add(new JLabel("Choose xml file:"), constraints1);


        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.gridx = 1;
        constraints1.gridy = 0;
        s.add(jButton, constraints1);


        constraints1.gridx = 0;
        constraints1.gridy = 1;
        constraints1.gridwidth = 2;
        //constraints1.fill = GridBagConstraints.CENTER;
        constraints1.insets = new Insets(10,0,0,0);
        JLabel jLabel = new JLabel("                          ");
        s.add(jLabel, constraints1);

        s.revalidate();


        jButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jFileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.getName().endsWith("xml") || f.isDirectory()) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "only xml files";
                    }
                });
                jFileChooser.showOpenDialog(s);
                File file = jFileChooser.getSelectedFile();
                String myPath = file.getAbsolutePath();
                try {
                    Start.XMLList = readXML.readingXML(myPath);
                }catch (Exception exception){
                    exception.getStackTrace();
                }


                jLabel.setText(myPath + " is loaded");
                s.revalidate();
            }
        });


        constraints1.fill = GridBagConstraints.EAST;
        constraints1.anchor = GridBagConstraints.EAST;
        constraints1.gridx = 0;
        constraints1.gridy = 2;
        constraints1.gridwidth = 1;
        constraints1.insets = new Insets(10,0,0,0);
        s.add(new JLabel("Create XMI: "), constraints1);

        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.gridx = 1;
        constraints1.gridy = 2;
        constraints1.gridwidth = 1;
        constraints1.insets = new Insets(10,0,0,0);
        JButton jButton1 = new JButton("Create");
        s.add(jButton1, constraints1);
        s.revalidate();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    createXMI.creatingXMI(Start.XMLList);
                }catch (Exception exception){
                    exception.getStackTrace();
                }
            }
        });


    }



}
