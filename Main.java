import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Main extends JFrame {

    private JTextField textField, tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12;



    public Main() {
        super("Test frame");
        createGUI();
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Read");

        panel.add(button1);

        textField = new JTextField();

        tf1 = new JTextField("1");
        tf2 = new JTextField("2");
        tf3 = new JTextField("3");
        tf4 = new JTextField("4");
        tf5 = new JTextField("5");
        tf6 = new JTextField("6");
        tf7 = new JTextField("7");
        tf8 = new JTextField("8");
        tf9 = new JTextField("9");
        tf10 = new JTextField("10");
        tf11 = new JTextField("11");
        tf12 = new JTextField("12");
        textField.setColumns(15);
        tf1.setColumns(15);
        tf2.setColumns(15);
        tf3.setColumns(15);
        tf4.setColumns(15);
        tf5.setColumns(15);
        tf6.setColumns(15);
        tf7.setColumns(15);
        tf8.setColumns(15);
        tf9.setColumns(15);
        tf10.setColumns(15);
        tf11.setColumns(15);
        tf12.setColumns(15);
        panel.add(textField);
        panel.add(tf1);
        panel.add(tf2);
        panel.add(tf3);
        panel.add(tf4);
        panel.add(tf5);
        panel.add(tf6);
        panel.add(tf7);
        panel.add(tf8);
        panel.add(tf9);
        panel.add(tf10);
        panel.add(tf11);
        panel.add(tf12);

        ActionListener actionListener = new TestActionListener();

        button1.addActionListener(actionListener);


        getContentPane().add(panel);
        setPreferredSize(new Dimension(500, 400));
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = null;
            try {

                 image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
            Robot r = null;
            try {
                r = new Robot();
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
            Color color;
            String s;
            int x1 = 0;
           /* for (int i=315;i<319;i++)
            {
                if(r.getPixelColor(i, 155).getRed()>250) {x1=i; break;}
            }


            //int col = image.getRGB(10,10);

           // s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            textField.setText(String.valueOf(x1));

            for (int i=332;i<335;i++)
            {
                if(r.getPixelColor(i, 161).getRed()>250) {x=i; break;}
            }
            tf1.setText(String.valueOf(x));*/
            for (int i=353;i>349;i--)
            {
                for (int j=152;j<162;j++)
                {
                    if(r.getPixelColor(i, j).getRed()>250) {x1=i; break;}
                }
                if (x1==i) break;
            }


            int x2;
            x1-=5;
            x2=153;
            textField.setText(String.valueOf(x1));
           color = r.getPixelColor(x1, x2);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf1.setText(s);

            color = r.getPixelColor(x1+2, x2);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf2.setText(s);

            color = r.getPixelColor(x1+5, x2);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf3.setText(s);

            color = r.getPixelColor(x1, x2+2);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf4.setText(s);

            color = r.getPixelColor(x1+3, x2+2);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf5.setText(s);

            color = r.getPixelColor(x1+5, x2+3);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf6.setText(s);

            color = r.getPixelColor(x1, x2+5);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf7.setText(s);

            color = r.getPixelColor(x1+3, x2+5);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf8.setText(s);

            color = r.getPixelColor(x1+5, x2+5);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf9.setText(s);

            color = r.getPixelColor(x1, x2+8);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf10.setText(s);

            color = r.getPixelColor(x1+3, x2+8);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf11.setText(s);

            color = r.getPixelColor(x1+5, x2+8);
            s="R=" + String.valueOf(color.getRed())+" G="+String.valueOf(color.getGreen())+" B="+String.valueOf(color.getBlue());
            tf12.setText(s);



        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Main frame = new Main();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

        //BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));


