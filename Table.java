
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Vladyslav on 09.01.2016.
 */
public class Table {
    private int x, y;
    private ArrayList<Card> lastCards;
    private int lastPos;


    class Card
    {
        private int value;
        private Character suit;

        public Card(int x,Character y)
        {
            value=x;
            suit=y;
        }

        public int getValue()
        {
            return value;
        }

        public Character getSuit()
        {
            return suit;
        }

    }

    public Table(int x, int y)
    {
        this.x = x;
        this.y = y;
        lastCards = new ArrayList<>();
    }

    public int readPos(BufferedImage r)
    {
        int position = 0;

        int color = r.getRGB(x + 492, y + 165);
        if ((color & 0x00ff0000)>> 16 > 150) position = 1;
        color = r.getRGB(x + 417, y + 127);
        if ((color & 0x00ff0000)>> 16 > 150) position = 2;
        color =r.getRGB(x + 342, y + 127);
        if ((color & 0x00ff0000)>> 16 > 150) position = 3;
        color = r.getRGB(x + 154, y + 165);
        if ((color & 0x00ff0000)>> 16 > 150) position = 4;
        color = r.getRGB(x + 130, y + 196);
        if ((color & 0x00ff0000)>> 16> 150) position = 5;
        color = r.getRGB(x + 218, y + 286);
        if ((color & 0x00ff0000)>> 16 > 150) position = 6;
        color = r.getRGB(x + 376, y + 295);
        if ((color & 0x00ff0000)>> 16 > 150) position = 7;
        color =r.getRGB(x + 440, y + 281);
        if ((color & 0x00ff0000)>> 16 > 150) position = 8;
        color = r.getRGB(x + 513, y + 193);
        if ((color & 0x00ff0000)>> 16> 150) position = 9;

        return position;
    }
    public int getOps(BufferedImage r)
    {
        int ops=0;


        int [] x1= {x+517,x+586,x+556,x+432,x+246,x+84,x+45,x+115};
        int [] y1 = {y+291,y+200,y+110,y+67,y+66,y+104,y+193,y+287};

        for (int i=0;i<readPos(r)-1;i++)
        {
            if ( (r.getRGB(x1[i], y1[i])& 0x00ff0000) >> 16 >50) ops+=1;
        }

        return ops;
    }

    private int readNumSt(int x, int y, BufferedImage r)
    {
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
        int color = r.getRGB(x, y);
        if ((color & 0x0000ff00) >> 8>150) st[0]=1; else st[0]=0;
        color = r.getRGB(x+3, y);
        if ((color & 0x0000ff00) >> 8>150) st[1]=1; else st[1]=0;
        color = r.getRGB(x, y+2);
        if ((color & 0x0000ff00) >> 8>150) st[2]=1; else st[2]=0;
        color = r.getRGB(x+3, y+2);
        if ((color & 0x0000ff00) >> 8>150) st[3]=1; else st[3]=0;
        color = r.getRGB(x, y+4);
        if ((color & 0x0000ff00) >> 8>150) st[4]=1; else st[4]=0;
        color = r.getRGB(x+3, y+4);
        if ((color & 0x0000ff00) >> 8>150) st[5]=1; else st[5]=0;
        color = r.getRGB(x, y+6);
        if ((color & 0x0000ff00) >> 8>150) st[6]=1; else st[6]=0;
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

    public float getStack(BufferedImage r)
    {
        int stack=0;
        int color = r.getRGB(304, 360);
        if ((color & 0x0000ff00) >> 8>100)  //===============не целое
        {
            stack+=readNumSt(296+x,355+y,r)*100;
            stack+=readNumSt(306+x,355+y,r)*10;
            stack+=readNumSt(313+x,355+y,r);
        }
        else                      //===============целое
        {
            stack+=readNumSt(304+x,355+y,r);
        }
        return stack;
    }

    public ArrayList<Card> getMyCards (BufferedImage r)
    {
       ArrayList<Card> myCards = new ArrayList<>();

        myCards.add(0,new Card(readCard(x+284,y+299,r),readSuit(x+288,y+318,r)));
        myCards.add(1,new Card(readCard(x+323,y+299,r),readSuit(x+327,y+318,r)));

       return myCards;
    }

    private int readCard (int x1,int x2, BufferedImage r)
    {
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
        int color = r.getRGB(x1+4, x2+19);
        if ((color & 0x00ff0000) >> 16<100){
            color = r.getRGB(x1+2, x2+1);          //======построение массива карты not heart
            if ((color & 0x00ff0000) >> 16>150) cd[0]=1; else cd[0]=0;
            color = r.getRGB(x1+6, x2+3);
            if ((color & 0x00ff0000) >> 16>150) cd[1]=1; else cd[1]=0;
            color = r.getRGB(x1+1, x2+5);
            if ((color & 0x00ff0000) >> 16>150) cd[2]=1; else cd[2]=0;
            color = r.getRGB(x1+8, x2+5);
            if ((color & 0x00ff0000) >> 16>150) cd[3]=1; else cd[3]=0;
            color = r.getRGB(x1+2, x2+7);
            if ((color & 0x00ff0000) >> 16>150) cd[4]=1; else cd[4]=0;
            color = r.getRGB(x1+6, x2+9);
            if ((color & 0x00ff0000) >> 16>150) cd[5]=1; else cd[5]=0;
            color = r.getRGB(x1+8, x2+11);
            if ((color & 0x00ff0000) >> 16>150) cd[6]=1; else cd[6]=0;}

        else {color = r.getRGB(x1+2, x2+1);          //======построение массива карты heart
            if ((color & 0x000000ff)>150) cd[0]=1; else cd[0]=0;
            color = r.getRGB(x1+6, x2+3);
            if ((color & 0x000000ff)>150) cd[1]=1; else cd[1]=0;
            color = r.getRGB(x1+1, x2+5);
            if ((color & 0x000000ff)>150) cd[2]=1; else cd[2]=0;
            color = r.getRGB(x1+8, x2+5);
            if ((color & 0x000000ff)>150) cd[3]=1; else cd[3]=0;
            color = r.getRGB(x1+2, x2+7);
            if ((color & 0x000000ff)>150) cd[4]=1; else cd[4]=0;
            color = r.getRGB(x1+6, x2+9);
            if ((color & 0x000000ff)>150) cd[5]=1; else cd[5]=0;
            color = r.getRGB(x1+8, x2+11);
            if ((color & 0x000000ff)>150) cd[6]=1; else cd[6]=0;}

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

    private char readSuit (int x, int y, BufferedImage r)
    {
        Character suit;
        int color = r.getRGB(x, y);
        if ((color & 0x00ff0000) >> 16>80) suit='h';
        else if ((color & 0x0000ff00) >> 8>80) suit='c';
        else if ((color & 0x000000ff)>80) suit='d';
        else suit='s';
        return suit;}

    public int getOpps(BufferedImage r)
    {               //=============оппонненты после позиции
        int ops=0;

        int [] x1= {x+517,x+586,x+556,x+432,x+246,x+84,x+45,x+115};
        int [] y1 = {y+291,y+200,y+110,y+67,y+66,y+104,y+193,y+287};

        if (readPos(r)>0){
            for (int i=0;i<=8-readPos(r);i++)
            {
                if (((r.getRGB(x1[7-i], y1[7-i]) & 0x00ff0000) >> 16)>50) ops+=1;
            }}

        return ops;
    }

    public boolean checkOpen(BufferedImage r)
    {
        boolean open;
        if (((r.getRGB(x+100, y+20) & 0x00ff0000) >> 16) > 200) open=false;
        else open=true;
        return open;
    }

    public boolean checkFold(BufferedImage r)
    {
        boolean move;
        if (((r.getRGB(x+395, y+425) & 0x00ff0000) >> 16)>200) move=true;
        else move=false;
        return move;
    }

    public boolean checkRaise(BufferedImage r)
    {
        boolean move;
        if (((r.getRGB(x+610, y+425) & 0x00ff0000) >> 16)>200) move=true;
        else move=false;
        return move;
    }

    public boolean checkCheck(BufferedImage r)
    {
        boolean move;
        if (((r.getRGB(x+478, y+425) & 0x0000ff00) >> 8)>235) move=false;
        else move=true;
        return move;
    }

    public boolean checkPause(BufferedImage  r)
    {
        boolean move;
        if (((r.getRGB(x+21, y+360) & 0x0000ff00) >> 8)>235) move=true;
        else move=false;
        return move;
    }

    public boolean firstMove(BufferedImage  r)
    {
        boolean move;
        if (lastCards.equals(getMyCards(r))&lastPos==readPos(r)) move=false;
        else move=true;
        return move;
    }

    public float getPot(BufferedImage  r)
    {
        int pot=0;
        pot+=getNumPot(getX(1,r),153,r)*100;
        pot+=getNumPot(getX(2,r),153,r)*10;
        pot+=getNumPot(getX(3,r),153,r);
        return pot;
    }

    private int getNumPot(int x ,int y,BufferedImage r)
    {
        int num=0;
        int[] st = new int[6];
        int[] n0 = {1,0,1,1,1,1};//======шаблоны
        int[] n1 = {0,1,0,0,0,0};
        int[] n2 = {1,0,0,1,0,0};
        int[] n3 = {0,0,0,0,0,1};
        int[] n4 = {0,0,0,1,1,1};
        int[] n5 = {0,1,0,0,0,1};
        int[] n6 = {0,1,1,1,1,1};
        int[] n7 = {1,1,0,0,0,0};
        int[] n8 = {0,1,0,0,1,1};
        int[] n9 = {1,0,1,1,0,1};
        int color = r.getRGB(x, y);
        if ((color & 0x0000ff00) >> 8>150) st[0]=1; else st[0]=0;
        color = r.getRGB(x+2, y);
        if ((color & 0x0000ff00) >> 8>150) st[1]=1; else st[1]=0;
        color = r.getRGB(x, y+2);
        if ((color & 0x0000ff00) >> 8>150) st[2]=1; else st[2]=0;
        color = r.getRGB(x+5, y+3);
        if ((color & 0x0000ff00) >> 8>150) st[3]=1; else st[3]=0;
        color = r.getRGB(x, y+5);
        if ((color & 0x0000ff00) >> 8>150) st[4]=1; else st[4]=0;
        color = r.getRGB(x+5, y+5);
        if ((color & 0x0000ff00) >> 8>150) st[5]=1; else st[5]=0;
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

    private int getX(int q, BufferedImage  r)
    {
        int num=0;
        if (q==1)
        {
            for (int i=315+x;i<319+x;i++)
            {
            if (((r.getRGB(i, 155)& 0x00ff0000) >> 16) > 250)
            {
                num = i;
                break;
            }

            }
        }
        if (q==2)
        {
            for (int i=332+x;i<335+x;i++)
            {
                if (((r.getRGB(i, 161) & 0x00ff0000) >> 16) > 250) {
                    num = i;
                    break;
                }
            }
        }
        if (q==3)
        {
            for (int i=353+x;i>349+x;i--)
            {
                for (int j=152+y;j<162+y;j++)
                {
                    if(((r.getRGB(i, j) & 0x00ff0000) >> 16)>250) {num=i; break;}
                if (num==i) break;
                }
            }
        }

        return num;
    }

    public float getCall(BufferedImage  r)
    {
        float cll=0;
        return cll;
    }
}
