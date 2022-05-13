package view;

import javax.swing.*;

import model.UserColor;

import java.awt.*;
import java.awt.event.*;

 
public class ColorPalette extends JFrame implements ActionListener {
 
    Container container=getContentPane();   
    JLabel activityNameLabel=new JLabel("Color Palette");
 
    
    public JButton cancelButton=new JButton("Close");
    public JButton saveButton=new JButton("Save All");
    

    public JButton urgentButton=new JButton("Edit");
    public JButton periodicButton=new JButton("Edit");
    public JButton missedButton=new JButton("Edit");
    public JButton attendedButton=new JButton("Edit");
    public JButton upcomingButton=new JButton("Edit");

    public JLabel cattended=new JLabel();
    public JLabel cmissed=new JLabel();
    public JLabel cupcoming=new JLabel();
    public JLabel curgent=new JLabel();
    public JLabel cperiodic=new JLabel();
 
    public ColorPalette(UserColor userColor)
    {
       //Calling methods inside constructor.
        container.setLayout(null);
        setVisible(true);
        
        activityNameLabel.setText("Color Palette");
        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);

      
        setBounds(10,10,500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));

        activityNameLabel.setBounds(60,10,350,30);
      
        
        JLabel cattendedText=new JLabel("Attended");
        cattended.setBounds(60,50,75,30);
        cattendedText.setBounds(136,50,200,30);
        attendedButton.setBounds(337,50,100,30);
        
        
        paintLabel(cattended,userColor.getAttended());
        paintLabel(cattendedText,"#f8de89");
        attendedButton.setActionCommand("Attended");
        attendedButton.addActionListener(this);

       
        JLabel cmissedText=new JLabel("Missed");
        cmissed.setBounds(60,81,75,30);
        cmissedText.setBounds(136,81,200,30);
        missedButton.setBounds(337,81,100,30);
        paintLabel(cmissed,userColor.getMissed());
        paintLabel(cmissedText,"#f8de89");
        missedButton.setActionCommand("Missed");
        missedButton.addActionListener(this);

        
        JLabel cupcomingText=new JLabel("Upcoming");
        cupcoming.setBounds(60,112,75,30);
        upcomingButton.setBounds(337,112,100,30);
        cupcomingText.setBounds(136,112,200,30);
        paintLabel(cupcoming,userColor.getUpcoming());
        paintLabel(cupcomingText,"#f8de89");
        upcomingButton.setActionCommand("Upcoming");
        upcomingButton.addActionListener(this);

       
        JLabel curgentText=new JLabel("Urgent");
        curgent.setBounds(60,143,75,30);
        curgentText.setBounds(136,143,200,30);
        urgentButton.setBounds(337,143,100,30);
        
        paintLabel(curgent,userColor.getUrgent());
        paintLabel(curgentText,"#f8de89");
        urgentButton.setActionCommand("Urgent");
        urgentButton.addActionListener(this);

       
        JLabel cperiodicText=new JLabel("Periodic");
        cperiodic.setBounds(60,174,75,30);
        cperiodicText.setBounds(136,174,200,30);
        periodicButton.setBounds(337,174,100,30);
        
        paintLabel(cperiodic,userColor.getPeriodic());
        paintLabel(cperiodicText,"#f8de89");
        periodicButton.setActionCommand("Periodic");
        periodicButton.addActionListener(this);


       
        saveButton.setBounds(150,420,100,30);
        cancelButton.setBounds(280,420,100,30);
        container.add(activityNameLabel);  
        
        container.add(cattended); 
        container.add(cattendedText);
        container.add(attendedButton);
        
        container.add(cmissed); 
        container.add(cmissedText);
        container.add(missedButton);

        container.add(cupcoming); 
        container.add(cupcomingText);
        container.add(upcomingButton);
       

        container.add(curgent); 
        container.add(curgentText);
        container.add(urgentButton);

        container.add(cperiodic); 
        container.add(cperiodicText);
        container.add(periodicButton);
        
         
        container.add(cancelButton);
        container.add(saveButton);

        

    }
   
    public void paintLabel(JLabel lbl,String code) {
        lbl.setOpaque(true);
        lbl.setBackground(Color.decode(code));
        lbl.setFont(new Font("Verdana", Font.ITALIC, 15));
        return;
        
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
      Color c=JColorChooser.showDialog(this,"Choose",Color.CYAN); 
      if (c!=null ) {
        String hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
        String actionCommand = ((JButton) e.getSource()).getActionCommand();
        if (actionCommand.equals("Attended")) cattended.setBackground(Color.decode(hex));
        if (actionCommand.equals("Missed")) cmissed.setBackground(Color.decode(hex));
        if (actionCommand.equals("Urgent")) curgent.setBackground(Color.decode(hex));
        if (actionCommand.equals("Upcoming")) cupcoming.setBackground(Color.decode(hex));
        if (actionCommand.equals("Periodic")) cperiodic.setBackground(Color.decode(hex));
      }
 
    }

   
}
