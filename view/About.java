package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


 
public class About extends JFrame implements ActionListener {
 
    Container container=getContentPane();
  
    
    public JButton closeButton=new JButton("Close");
    
    

 
    public About(String title)
    {
       //Calling methods inside constructor.
       container.setLayout(null);
       JLabel panelTitle=new JLabel("");
       JLabel panelText=new JLabel("");
       
       setTitle(title);
       setVisible(true);
        
       setBounds(10,10,500,500);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setResizable(false);
       setLocationRelativeTo(null); 
       getContentPane().setBackground(Color.decode("#647687"));
        
       panelTitle.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
       panelTitle.setForeground(Color.WHITE);
       panelTitle.setText(title);
       panelText.setText(getDetailedText(title));
       panelText.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
       panelText.setForeground(Color.WHITE);
       panelTitle.setBounds(40,20,350,30);
       panelText.setBounds(40,60,420,300);
       panelText.setVerticalAlignment(JLabel.TOP);
       closeButton.setBounds(200,400,80,30);
      

       container.add(panelTitle);
       container.add(panelText);
       container.add(closeButton);
        
        
    }
    public String getDetailedText(String title) {
        String detailedText="";
        if (title.equals("Contacts")) {
            detailedText="<html>Kutay Tire:<br>kutaytire@gmail.com<br><br> " +
            "Yağız Başaran:<br>yagiz.basaran@ug.bilkent.edu.tr<br><br> " +
            "Alp Batu Aksan:<br>alpbatuaksans@gmail.com<br><br> " +
            "Alper Yıldırım:<br>safkankanarya@gmail.com<br><br> " +
            "Melih Rıza Yıldız:<br>riza.yildiz@ug.bilkent.edu.tr" ;
        } else {
            detailedText="<html>As a result, this app will make sure that every user fulfills their promised activities, keep" + 
            "track of time, and does not forget special days like religious days or national holidays by " +
            "providing the necessary features and a very user-friendly and sophisticated interface. For this " +
            "purpose, the user interface is designed to meet the needs of users all around the world. " +
            "Colorization carries great importance in the user interface as can be seen. The pages are designed " +
            "to give a sense of comfort to the users and the functionality of each button can be understood " +
            "without any problems. Therefore, the app has the potential to reach users from every religion and " +
            "culture with its friendly user interface and various functions.</html>";
             

        }
        return detailedText;
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
