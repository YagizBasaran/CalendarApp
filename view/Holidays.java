package view;

import javax.swing.*;

import controller.*;


import model.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

 
public class Holidays extends JFrame implements ActionListener {
 
    Container container=getContentPane();
    JLabel activityNameLabel=new JLabel("");
    public JButton cancelButton=new JButton("Close");
 
    public JComboBox<String> cNationality = new JComboBox<String>();
    JLabel[] holidayDesc=new JLabel[10];
    JLabel[] holidayStart=new JLabel[10];
    JLabel[] holidayEnd=new JLabel[10];

    JTable tbl = new JTable();
   
  
    public Holidays(String title, Person p)
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle(title);
        setVisible(true);
        activityNameLabel.setText(title);
        setBounds(10,10,500,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
       
        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);
        if (title.equals("Cultural Holidays")) {
            cNationality.addItem("No Nationality Selected");
            cNationality.addItem("Turkish");
            cNationality.addItem("German");
            cNationality.addItem("American");
            cNationality.addItem("English");
            cNationality.addItem("Russian");
            if (p.getNationality()!=null)  cNationality.getModel().setSelectedItem(p.getNationality());
            else cNationality.getModel().setSelectedItem("No Nationality Selected");       

        } else {
            cNationality.addItem("No Religion Selected");
            cNationality.addItem("Islam");
            cNationality.addItem("Christianity");
            cNationality.addItem("Judaism");
            if (p.getReligion()!=null) cNationality.getModel().setSelectedItem(p.getReligion());
            else  cNationality.getModel().setSelectedItem("No Religion Selected");
        }
       
        for (int i=0;i<10;i++) {
            JLabel hd=new JLabel();
            JLabel hs=new JLabel();
            JLabel he=new JLabel();
            holidayDesc[i]=hd;
            holidayStart[i]=hs;
            holidayEnd[i]=he;
            holidayDesc[i].setText("");
            holidayStart[i].setText("");
            holidayEnd[i].setText("");
            holidayDesc[i].setOpaque(true);
            holidayDesc[i].setBackground(Color.decode("#dd9ebb"));
            holidayDesc[i].setFont(new Font("Verdana", Font.ITALIC | Font.BOLD, 14));
            holidayDesc[i].setBounds(10,82+ 32*i,250,30);
            holidayStart[i].setOpaque(true);
            holidayStart[i].setBackground(Color.decode("#dd9ebb"));
            holidayStart[i].setFont(new Font("Verdana", Font.ITALIC, 14));
            holidayStart[i].setBounds(262,82+ 32*i,100,30);
            holidayEnd[i].setOpaque(true);
            holidayEnd[i].setBackground(Color.decode("#dd9ebb"));
            holidayEnd[i].setFont(new Font("Verdana", Font.ITALIC, 14));
            holidayEnd[i].setBounds(365,82+ 32*i,100,30);
            container.add(holidayDesc[i]);
            container.add(holidayStart[i]);
            container.add(holidayEnd[i]);
        }
        

       initializeHolidays();
       DBController dbc=new DBController();
       String mode="R";
       if (title.equals("Cultural Holidays")) mode="C";
       ArrayList<Holiday> holidaysNList= dbc.getHolidaysDB(mode,cNationality.getSelectedItem().toString());
       displayHolidays(holidaysNList);
    }

    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
      
       activityNameLabel.setBounds(10,20,350,30);
       cNationality.setBounds(10,50,200,30);
       cancelButton.setBounds(300,600,100,30);
       
    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(activityNameLabel);  
        container.add(cancelButton);
        
        container.add(cNationality);
       
   }
   public void initializeHolidays()
   {
    for (int i=0;i<10;i++) {
        holidayDesc[i].setText("");
        holidayStart[i].setText("");
        holidayEnd[i].setText("");
        holidayDesc[i].setVisible(false);
        holidayStart[i].setVisible(false);
        holidayEnd[i].setVisible(false);
    }
   }
   public void displayHolidays(ArrayList<Holiday> al)
   {
    initializeHolidays();
    for (int i=0;i<al.size();i++) {
       
        
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");  
        
              
        holidayDesc[i].setText(al.get(i).getHolidayDescription());
        holidayStart[i].setText(dateFormat.format(al.get(i).getStartDay()));
        holidayEnd[i].setText(dateFormat.format(al.get(i).getEndDay()));
        System.out.println(al.get(i).getHolidayDescription()+ " " + dateFormat.format(al.get(i).getStartDay())+ " " + dateFormat.format(al.get(i).getEndDay()));
        holidayDesc[i].setVisible(true);
        holidayStart[i].setVisible(true);
        holidayEnd[i].setVisible(true);
      
        
    }
   }




  
@Override
public void actionPerformed(ActionEvent e) {
   
    
}

   

   
}
 
