import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Vladyslav on 26.12.2015.
 */
public class MyP extends JFrame{
    private JTextField textField;
    private JTextField textField2;
    private static AtomicBoolean paused= new AtomicBoolean(true);
    private JCheckBox CPause;
    private JCheckBox CStop;
    private play pl = new play();
    private Robot r=null;
    private BufferedImage image;
    private Rectangle rect= new Rectangle(0,0,1280,1024);

    public MyP() {
        super("MyP");
        createGUI();

    }
    public void createGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button1 = new JButton("Start");
        JButton button2 = new JButton("Stop");
        panel.add(button1);
        panel.add(button2);
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(20);
        textField2 = new JTextField();
        panel.add(textField2);
        textField2.setColumns(20);
        CPause = new JCheckBox("Pause");
        panel.add(CPause);
        CStop = new JCheckBox("Stop");
        panel.add(CStop);
        ActionListener actionListener = new TestActionListener();
        ActionListener actionListener2 = new TestActionListener2();
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener2);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(300, 120));
        }

    public class TestActionListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
             synchronized(this) {
                 pl.start();
                 paused.set(false);
             }}}


    public class TestActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (paused.get()==true)  {
                paused.set(false);
               pl.suspend();
            }
            else {    paused.set(true); pl.resume();
                 }
            textField2.setText(String.valueOf(paused));
        }
    }

    public int readOps(int mp){               //=============оппонненты до позиции
        int ops=0;

        int [] x= {517,586,556,432,246,84,45,115};
        int [] y = {291,200,110,67,66,104,193,287};

        for (int i=0;i<mp-1;i++)
        {
            if ( r.getPixelColor(x[i], y[i]).getRed()>50) ops+=1;
        }

        return ops;}

    public int readOpps(int mp){               //=============оппонненты после позиции
        int ops=0;

        int [] x= {517,586,556,432,246,84,45,115};
        int [] y = {291,200,110,67,66,104,193,287};

        if (mp>0){
        for (int i=0;i<=8-mp;i++)
        {
            if ( r.getPixelColor(x[7-i], y[7-i]).getRed()>50) ops+=1;
        }}

        return ops;}
    public float readCll()
    {
        float cll=0;
        cll+=(float)readNumRs(458,441);
        cll+=(float)readNumRs(470,441)/10;
        cll+=(float)readNumRs(478,441)/100;
        return cll;
    }

    public float readRs ()
    {
        float rs=0;
        rs+=(float)readNumRs(565,441);
        rs+=(float)readNumRs(577,441)/10;
        rs+=(float)readNumRs(585,441)/100;
        return rs;
    }

    public int readNumRs(int x1, int x2){
        int num=0;
        int[] st = new int[6];
        int[] n0 = {1,1,1,0,1,0};//======шаблоны
        int[] n1 = {1,0,0,0,0,1};
        int[] n2 = {0,1,0,0,0,1};
        int[] n3 = {0,1,0,1,0,0};
        int[] n4 = {0,1,0,1,0,1};
        int[] n5 = {1,1,0,1,0,1};
        int[] n6 = {1,0,0,1,0,1};
        int[] n7 = {1,1,0,0,0,0};
        int[] n8 = {0,1,0,1,0,1};
        int[] n9 = {1,1,1,0,0,0};
        Color color = r.getPixelColor(x1+2, x2);
        if (color.getGreen()>155) st[0]=1; else st[0]=0;
        color = r.getPixelColor(x1+5, x2);
        if (color.getGreen()>155) st[1]=1; else st[1]=0;
        color = r.getPixelColor(x1, x2+3);
        if (color.getGreen()>155) st[2]=1; else st[2]=0;
        color = r.getPixelColor(x1+2, x2+3);
        if (color.getGreen()>155) st[3]=1; else st[3]=0;
        color = r.getPixelColor(x1, x2+5);
        if (color.getGreen()>155) st[4]=1; else st[4]=0;
        color = r.getPixelColor(x1+5, x2+8);
        if (color.getGreen()>155) st[5]=1; else st[5]=0;
        if (Arrays.equals(st,n0)) num=0;
        else if (Arrays.equals(st,n1)) num=1;
        else if (Arrays.equals(st,n2)) num=2;
        else if (Arrays.equals(st,n3)) num=3;      //=============сравнение с шаблонами
        else if (Arrays.equals(st,n4)) num=4;
        else if (Arrays.equals(st,n5)) num=5;
        else if (Arrays.equals(st,n6)) num=6;
        else if (Arrays.equals(st,n7)) num=7;
        else if (Arrays.equals(st,n8)) num=8;
        else if (Arrays.equals(st,n9)) num=9;
        return num;
    }

    public int readNumSt(int x, int y){
        int num=0;
        int[] st = new int[7];
        int[] n0 = {1,0,1,0,1,0,0};//======шаблоны
        int[] n1 = {0,1,0,1,0,1,0};
        int[] n2 = {1,0,0,1,0,0,1};
        int[] n3 = {1,0,0,1,0,0,0};
        int[] n4 = {0,1,0,1,1,1,0};
        int[] n5 = {0,0,0,1,0,0,0};
        int[] n6 = {1,0,1,1,1,0,0};
        int[] n7 = {0,0,0,1,0,1,0};
        int[] n8 = {1,0,0,1,1,0,0};
        int[] n9 = {1,0,1,0,0,0,0};
        Color color = r.getPixelColor(x, y);
        if (color.getGreen()>150) st[0]=1; else st[0]=0;
        color = r.getPixelColor(x+3, y);
        if (color.getGreen()>150) st[1]=1; else st[1]=0;
        color = r.getPixelColor(x, y+2);
        if (color.getGreen()>150) st[2]=1; else st[2]=0;
        color = r.getPixelColor(x+3, y+2);
        if (color.getGreen()>150) st[3]=1; else st[3]=0;
        color = r.getPixelColor(x, y+4);
        if (color.getGreen()>150) st[4]=1; else st[4]=0;
        color = r.getPixelColor(x+3, y+4);
        if (color.getGreen()>150) st[5]=1; else st[5]=0;
        color = r.getPixelColor(x, y+6);
        if (color.getGreen()>150) st[6]=1; else st[6]=0;
        if (Arrays.equals(st,n0)) num=0;
        else if (Arrays.equals(st,n1)) num=1;
        else if (Arrays.equals(st,n2)) num=2;
        else if (Arrays.equals(st,n3)) num=3;      //=============сравнение с шаблонами
        else if (Arrays.equals(st,n4)) num=4;
        else if (Arrays.equals(st,n5)) num=5;
        else if (Arrays.equals(st,n6)) num=6;
        else if (Arrays.equals(st,n7)) num=7;
        else if (Arrays.equals(st,n8)) num=8;
        else if (Arrays.equals(st,n9)) num=9;


        return num;
    }
    public float readStack()
    {
        int x,y;
        float stack=0;
        Color color = r.getPixelColor(304, 360);
        if (color.getGreen()>100)  //===============не целое
          {
              x=296;
              y=355;
              stack+=(float)readNumSt(x,y);
              stack+=(float)readNumSt(306,y)/10;
              stack+=(float)readNumSt(313,y)/100;
          }
        else                      //===============целое
        {
            stack+=readNumSt(304,355);
        }
        return stack;
    }


    public int readPos()
    {
        int position=0;
        Color color = r.getPixelColor(492, 165);
        if (color.getRed()>150) position=1;
        color = r.getPixelColor(417, 127);
        if (color.getRed()>150) position=2;
        color = r.getPixelColor(342, 127);
        if (color.getRed()>150) position=3;
        color = r.getPixelColor(154,165);
        if (color.getRed()>150) position=4;
        color = r.getPixelColor(130, 196);
        if (color.getRed()>150) position=5;
        color = r.getPixelColor(218, 286);
        if (color.getRed()>150) position=6;
        color = r.getPixelColor(376, 295);
        if (color.getRed()>150) position=7;
        color = r.getPixelColor(440, 281);
        if (color.getRed()>150) position=8;
        color = r.getPixelColor(513, 193);
        if (color.getRed()>150) position=9;
        return position;
    }
    public int readCard (int x1,int x2){
        int card =0;
        int[] cd = new int[7];
        int[] c2 = {1,0,1,0,1,1,0};//======шаблоны
        int[] c3 = {1,0,1,1,1,1,1};
        int[] c4 = {1,0,1,1,0,0,1};
        int[] c5 = {0,1,0,1,0,1,1};
        int[] c6 = {1,0,0,1,0,1,1};
        int[] c7 = {0,1,1,1,1,1,1};
        int[] c8 = {1,0,1,1,0,1,1};
        int[] c9 = {1,0,0,0,0,1,1};
        int[] c10 = {0,1,0,0,0,1,0};
        int[] c11 = {1,0,1,1,1,0,1};
        int[] c12 = {1,0,0,0,0,0,0};
        int[] c13 = {0,1,1,1,0,0,0};
        int[] c14 = {1,0,1,1,0,1,0};
        Color color = r.getPixelColor(x1+4, x2+19);
        if (color.getRed()<100){
        color = r.getPixelColor(x1+2, x2+1);          //======построение массива карты not heart
        if (color.getRed()>150) cd[0]=1; else cd[0]=0;
        color = r.getPixelColor(x1+6, x2+3);
        if (color.getRed()>150) cd[1]=1; else cd[1]=0;
        color = r.getPixelColor(x1+1, x2+5);
        if (color.getRed()>150) cd[2]=1; else cd[2]=0;
        color = r.getPixelColor(x1+8, x2+5);
        if (color.getRed()>150) cd[3]=1; else cd[3]=0;
        color = r.getPixelColor(x1+2, x2+7);
        if (color.getRed()>150) cd[4]=1; else cd[4]=0;
        color = r.getPixelColor(x1+6, x2+9);
        if (color.getRed()>150) cd[5]=1; else cd[5]=0;
        color = r.getPixelColor(x1+8, x2+11);
        if (color.getRed()>150) cd[6]=1; else cd[6]=0;}

            else {color = r.getPixelColor(x1+2, x2+1);          //======построение массива карты heart
                if (color.getBlue()>150) cd[0]=1; else cd[0]=0;
                color = r.getPixelColor(x1+6, x2+3);
                if (color.getBlue()>150) cd[1]=1; else cd[1]=0;
                color = r.getPixelColor(x1+1, x2+5);
                if (color.getBlue()>150) cd[2]=1; else cd[2]=0;
                color = r.getPixelColor(x1+8, x2+5);
                if (color.getBlue()>150) cd[3]=1; else cd[3]=0;
                color = r.getPixelColor(x1+2, x2+7);
                if (color.getBlue()>150) cd[4]=1; else cd[4]=0;
                color = r.getPixelColor(x1+6, x2+9);
                if (color.getBlue()>150) cd[5]=1; else cd[5]=0;
                color = r.getPixelColor(x1+8, x2+11);
                if (color.getBlue()>150) cd[6]=1; else cd[6]=0;}

        if (Arrays.equals(cd,c2)) card=2;
        else if (Arrays.equals(cd,c3)) card=3;      //=============сравнение с шиблонами
        else if (Arrays.equals(cd,c4)) card=4;
        else if (Arrays.equals(cd,c5)) card=5;
        else if (Arrays.equals(cd,c6)) card=6;
        else if (Arrays.equals(cd,c7)) card=7;
        else if (Arrays.equals(cd,c8)) card=8;
        else if (Arrays.equals(cd,c9)) card=9;
        else if (Arrays.equals(cd,c10)) card=10;
        else if (Arrays.equals(cd,c11)) card=11;
        else if (Arrays.equals(cd,c12)) card=12;
        else if (Arrays.equals(cd,c13)) card=13;
        else if (Arrays.equals(cd,c14)) card=14;

        return card;
    }

    public char readSuit (int x, int y)
    {
        Character suit;
        Color color = r.getPixelColor(x, y);
        if (color.getRed()>80) suit='h';
        else if (color.getGreen()>80) suit='c';
        else if (color.getBlue()>80) suit='d';
        else suit='s';
        return suit;}

     class play extends Thread
     {
         @Override
        public void run()
         {
             Table table1 = new Table(0,0);
             Date dt,dt2;
             long speed;
             int color;
             int  red;
             int  blue;
             int  green;
             while (true) {
             try {
                 r = new Robot();
             } catch (AWTException e1) {
                 e1.printStackTrace();
             }
                 image=r.createScreenCapture(rect);

                 /*textField.setText(String.valueOf(readCard(284,299))+readSuit(288,318)+" "
                         +String.valueOf(readCard(323,299))+readSuit(327,318)+" Stack="
                         +readStack()+" Pos="+readPos());*/

                 dt=new Date();
                /* color = image.getRGB(100, 100);
                 red = (color & 0x00ff0000) >> 16;
                 green = (color & 0x0000ff00) >> 8;
                 blue = color & 0x000000ff;*/
                 textField2.setText("Ops="+table1.getOps(image)+" Opps" + table1.getOpps(image)+ "Stack="+table1.getStack(image)
                         +" Pot="+table1.getPot(image));
                 dt2=new Date();
                 speed=dt2.getTime()-dt.getTime();
                 textField.setText(String.valueOf(speed));
                 try {
                     sleep(10);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }

    private void move (Table table)
    {

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                MyP frame = new MyP();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);


                }
        });
    }


}
