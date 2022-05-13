package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


 
public class CalendarType extends JFrame implements ActionListener {
 
    Container container=getContentPane();
  
    
    public JButton closeButton=new JButton("Close");
    public JCheckBox showHicri= new JCheckBox("Show Hijrah Calendar");
    
    

 
    public CalendarType(Boolean showHicriBool)
    {
        //Calling methods inside constructor.
        container.setLayout(null);
        JLabel panelTitle=new JLabel("");
        JLabel panelText=new JLabel("");
        
        setTitle("Calendar Type");
        setVisible(true);
            
        setBounds(10,10,500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
            
        panelTitle.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        panelTitle.setForeground(Color.WHITE);
        panelTitle.setText("Calendar Type");
        panelText.setText(getDetailedText("Calendar Type"));
        panelText.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
        panelText.setForeground(Color.WHITE);
        panelTitle.setBounds(40,20,350,30);
        panelText.setBounds(40,60,420,200);
        showHicri.setBounds(40,160,420,30);
       
        showHicri.setBackground(Color.decode("#647687"));
        showHicri.setForeground(Color.WHITE);
        showHicri.setSelected(showHicriBool);
        
        
        panelText.setVerticalAlignment(JLabel.TOP);
        closeButton.setBounds(200,400,80,30);
        

        container.add(panelTitle);
        container.add(panelText);
        container.add(showHicri);
        
        container.add(closeButton);
            
        
    }
    public String getDetailedText(String title) {
        String detailedText="";
        
            detailedText="<html>Our calendar funcitonality is based on Gregorian calendar." +
            "<br><br>You can see Hijrah calendar overlapped with Gregorian calendar";
             

        
        return detailedText;
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
