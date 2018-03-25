import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;


public class SodaMachineFrame extends JFrame
{
   private JPanel Lpanel, Cpanel, Rpanel, dep, ret, sal;
   private JFrame frame;
   private JTextField deposited, returned, sales;
   private SodaMachine mac;
   private JButton Nickel, Dime, Quarter, hDollar, Dollar, cReturn;
   private JButton s1,s2,s3,s4,s5,s6,s7,s8,s9;
   
   public SodaMachineFrame(SodaMachine m)
   {
      mac = m;
      final int nickel = 5;
      final int dime = 10;
      final int quarter = 25;
      final int half = 50;
      final int dollar = 100;
      
      class ReturnListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            deposited.setText("$0.00");
            double res = (double)mac.returnDeposits()/100;
            String str = formatter.format(res);
            returned.setText(str);
            cReturn.setEnabled(false);
            for(int i =0; i < mac.getCount(); i++)
            {
               Lpanel.getComponent(i).setEnabled(false);
            }
            
         }
      }
      
      class S1Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(0)/100;
               String str = formatter.format(res);
               returned.setText(str);
               cReturn.setEnabled(false);
               
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S2Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(1)/100;
               String str = formatter.format(res);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               returned.setText(str);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S3Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(2)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S4Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(3)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S5Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(4)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S6Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(5)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S7Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(6)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      
      
      class S8Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(7)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
      
      class S9Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               NumberFormat formatter = new DecimalFormat("$##0.00");
               deposited.setText("$0.00");
               double res = (double)mac.dispenseSoda(8)/100;
               String str = formatter.format(res);
               returned.setText(str);
               double tsale = (double)mac.getSales()/100;
               String saltr = formatter.format(tsale);
               sales.setText(saltr);
               cReturn.setEnabled(false);
               for(int i =0; i < mac.getCount(); i++)
               {
                  Lpanel.getComponent(i).setEnabled(false);
               }
            }
            catch(IndexOutOfBoundsException b)
            {}
            
         }
      }
              
      
      class NickelListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            mac.deposit(nickel);
            double res = (double)mac.getDeposits()/100;
            String str = formatter.format(res);
            deposited.setText(str);
            returned.setText("$0.00");
            cReturn.setEnabled(true);
            for(int i = 0; i < mac.getCount(); i++)
            {
               if(mac.getSodaCount(i) > 0 && mac.sufficientFunds())
               {
                  Lpanel.getComponent(i).setEnabled(true);
               }
                  
            }
         }
      }
      
      class DimeListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            mac.deposit(dime);
            double res = (double)mac.getDeposits()/100;
            String str = formatter.format(res);
            deposited.setText(str);
            returned.setText("$0.00");
            cReturn.setEnabled(true);
            for(int i = 0; i < mac.getCount(); i++)
            {
               if(mac.getSodaCount(i) > 0 && mac.sufficientFunds())
               {
                  Lpanel.getComponent(i).setEnabled(true);
               }
                  
            }
         }
      }
      
      class QuarterListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            mac.deposit(quarter);
            double res = (double)mac.getDeposits()/100;
            String str = formatter.format(res);
            deposited.setText(str);
            returned.setText("$0.00");
            cReturn.setEnabled(true);
            for(int i = 0; i < mac.getCount(); i++)
            {
               if(mac.getSodaCount(i) > 0 && mac.sufficientFunds())
               {
                  Lpanel.getComponent(i).setEnabled(true);
               }
                  
            }
         }
      }
      
      class HalfListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            mac.deposit(half);
            double res = (double)mac.getDeposits()/100;
            String str = formatter.format(res);
            deposited.setText(str);
            returned.setText("$0.00");
            cReturn.setEnabled(true);
            for(int i = 0; i < mac.getCount(); i++)
            {
               if(mac.getSodaCount(i) > 0 && mac.sufficientFunds())
               {
                  Lpanel.getComponent(i).setEnabled(true);
               }
                  
            }
         }
      }
      
      class DollarListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            NumberFormat formatter = new DecimalFormat("$##0.00");
            mac.deposit(dollar);
            double res = (double)mac.getDeposits()/100;
            String str = formatter.format(res);
            deposited.setText(str);
            returned.setText("$0.00");
            cReturn.setEnabled(true);
            for(int i = 0; i < mac.getCount(); i++)
            {
               if(mac.getSodaCount(i) > 0 && mac.sufficientFunds())
               {
                  Lpanel.getComponent(i).setEnabled(true);
               }
                  
            }
         }
      }
            
      //frame = new JFrame();
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //frame.setResizable(false);
      NumberFormat formatter = new DecimalFormat("$##0.00");
      
      
      Rpanel = new JPanel();
      Rpanel.setPreferredSize(new Dimension(150, 300));
      Rpanel.setBorder(BorderFactory.createEtchedBorder());
      Rpanel.setLayout(new GridLayout(5,1));
      Nickel = new JButton("Nickel");
      Nickel.addActionListener(new NickelListener());
      Dime = new JButton("Dime");
      Dime.addActionListener(new DimeListener());
      Quarter = new JButton("Quarter");
      Quarter.addActionListener(new QuarterListener());
      hDollar = new JButton("Half Dollar");
      hDollar.addActionListener(new HalfListener());
      Dollar = new JButton("Dollar");
      Dollar.addActionListener(new DollarListener());
      Rpanel.add(Nickel);
      Rpanel.add(Dime);
      Rpanel.add(Quarter);
      Rpanel.add(hDollar);
      Rpanel.add(Dollar);
      //frame.add(Rpanel, BorderLayout.EAST);
      
      Lpanel = new JPanel();
      Lpanel.setPreferredSize(new Dimension(150,300));
      Lpanel.setBorder(BorderFactory.createEtchedBorder());
      //frame.add(Lpanel, BorderLayout.WEST);
      Lpanel.setLayout(new GridLayout(mac.getCount(), 1));
      
      try
      {
         s1 = new JButton(mac.getSodaName(0));
         s1.addActionListener(new S1Listener());
         Lpanel.add(s1);
         s1.setEnabled(false);
         
         s2 = new JButton(mac.getSodaName(1));
         s2.addActionListener(new S2Listener());
         Lpanel.add(s2);
         s2.setEnabled(false);
         
         s3 = new JButton(mac.getSodaName(2));
         s3.addActionListener(new S3Listener());
         Lpanel.add(s3);
         s3.setEnabled(false);
         
         s4 = new JButton(mac.getSodaName(3));
         s4.addActionListener(new S4Listener());
         Lpanel.add(s4);
         s4.setEnabled(false);
         
         s5 = new JButton(mac.getSodaName(4));
         s5.addActionListener(new S5Listener());
         Lpanel.add(s5);
         s5.setEnabled(false);
         
         s6 = new JButton(mac.getSodaName(5));
         s6.addActionListener(new S6Listener());
         Lpanel.add(s6);
         s6.setEnabled(false);
         
         s7 = new JButton(mac.getSodaName(6));
         s7.addActionListener(new S7Listener());
         Lpanel.add(s7);
         s7.setEnabled(false);
         
         s8 = new JButton(mac.getSodaName(7));
         s8.addActionListener(new S8Listener());
         Lpanel.add(s8);
         s8.setEnabled(false);
         
         s9 = new JButton(mac.getSodaName(8));
         s9.addActionListener(new S9Listener());
         Lpanel.add(s9);
         s9.setEnabled(false);
      }
      catch(IndexOutOfBoundsException b)
      {
         
      }
      
      Cpanel = new JPanel();
      Cpanel.setPreferredSize(new Dimension(150,300));
      Cpanel.setBorder(BorderFactory.createEtchedBorder());
      //frame.add(Cpanel, BorderLayout.CENTER);
      Cpanel.setLayout(new GridLayout(3,1));
      
      dep = new JPanel();
      dep.setBorder(BorderFactory.createEtchedBorder());
      dep.add(new JLabel("Amount Deposited"));
      double res = (mac.getDeposits())/100;
      String str = (formatter.format(res));
      deposited = new JTextField(6);
      deposited.setHorizontalAlignment(JTextField.RIGHT);
      deposited.setEditable(false);
      deposited.setText(str);
      cReturn = new JButton("Coin Return");
      cReturn.addActionListener(new ReturnListener());
      cReturn.setEnabled(false);
      dep.add(deposited);
      dep.add(cReturn);
      
      ret = new JPanel();
      ret.setBorder(BorderFactory.createEtchedBorder());
      ret.add(new JLabel("Change Returned"));
      double cR = (0/100);
      String cStr = (formatter.format(cR));
      returned = new JTextField(6);
      returned.setHorizontalAlignment(JTextField.RIGHT);
      returned.setEditable(false);
      returned.setText(cStr);
      ret.add(returned);
      
      sal = new JPanel();
      sal.setBorder(BorderFactory.createEtchedBorder());
      sal.add(new JLabel("Total Sales"));
      double tS = (mac.getSales()/100);
      String tStr = (formatter.format(tS));
      sales = new JTextField(6);
      sales.setHorizontalAlignment(JTextField.RIGHT);
      sales.setEditable(false);
      sales.setText(tStr);
      sal.add(sales);
      
      Cpanel.add(dep);
      Cpanel.add(ret);
      Cpanel.add(sal);
      getContentPane().add(Lpanel, BorderLayout.WEST);
      getContentPane().add(Cpanel, BorderLayout.CENTER);
      getContentPane().add(Rpanel, BorderLayout.EAST);
      
      
      
     
      
      
      //frame.pack();
      setResizable(false);
      pack();
      //frame.setVisible(true);
      
   }
  
   
}
