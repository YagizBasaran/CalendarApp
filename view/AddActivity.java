package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class AddActivity extends JFrame implements ActionListener {
 
    Container container=getContentPane();
   
    JLabel activityNameLabel=new JLabel("Activity Name");
    JLabel startLabel=new JLabel("Start");
    JLabel finishLabel=new JLabel("Finish");
    JLabel urgentLabel=new JLabel("Is It Urgent?");
    JLabel periodicLabel=new JLabel("Is It Periodic?");
    public JTextField activityField=new JTextField();
    
    public JRadioButton urgentR1=new JRadioButton("Yes");    
    JRadioButton urgentR2=new JRadioButton("No");  
    public JRadioButton periodicR1=new JRadioButton("Yes");    
    JRadioButton periodicR2=new JRadioButton("No");   

    public JButton addButton=new JButton("Add");
    public JButton cancelButton=new JButton("Cancel");
    DefaultComboBoxModel<Date> modelStart = new DefaultComboBoxModel<>();
  
    public JComboBox<String> cbStart = new JComboBox<String>();
    public JComboBox<String> cbFinish = new JComboBox<String>();

    public JComboBox<String> periodicComboBox = new JComboBox<String>();
    JLabel periodicComboLabel=new JLabel("Starting from today, this activity will be repeated every");
 
    public AddActivity(String title, String activityName)
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle(title);
        setVisible(true);
        activityField.setText(activityName);
        setBounds(10,10,470,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
      

        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityField.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        activityNameLabel.setForeground(Color.WHITE);
        startLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        startLabel.setForeground(Color.WHITE);
        finishLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        finishLabel.setForeground(Color.WHITE);

        urgentLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        urgentLabel.setForeground(Color.WHITE);

        periodicLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        periodicLabel.setForeground(Color.WHITE);
        periodicComboLabel.setForeground(Color.WHITE);

        periodicR1.setBackground(Color.decode("#647687"));
        periodicR2.setBackground(Color.decode("#647687"));
        periodicR1.setForeground(Color.WHITE);
        periodicR2.setForeground(Color.WHITE);

        urgentR1.setBackground(Color.decode("#647687"));
        urgentR2.setBackground(Color.decode("#647687"));
        urgentR1.setForeground(Color.WHITE);
        urgentR2.setForeground(Color.WHITE);
       

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
        do {
            modelStart.addElement(calendar.getTime());
            calendar.add(Calendar.MINUTE, 15);
            String strDate = dateFormat.format(calendar.getTime());  
            cbStart.addItem(strDate);
            cbFinish.addItem(strDate);
        } while (calendar.getTime().before(end.getTime()));

       
        container.add(cbStart);
        cbStart.setBounds(50,150,70,30);
      
        container.add(cbFinish);
        cbFinish.setBounds(330,150,70,30);
        periodicComboBox.addItem("1 Week");    
        periodicComboBox.addItem("2 Weeks"); 
        periodicComboBox.addItem("1 Month");
        periodicComboBox.addItem("2 Months");    
          
        periodicComboBox.setVisible(false);
        periodicComboLabel.setVisible(false);
      
 
    }

    
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
      
       activityNameLabel.setBounds(50,50,150,30);
       activityField.setBounds(50,80,350,30);

       startLabel.setBounds(50,120,100,30);
       finishLabel.setBounds(350,120,100,30);

       urgentLabel.setBounds(50,180,200,30);
       periodicLabel.setBounds(50,250,200,30);

     
       urgentR1.setBounds(50,210,75,30);    
       urgentR2.setBounds(150,210,75,30);   
       
       periodicR1.setBounds(50,280,75,30);    
       periodicR2.setBounds(150,280,75,30);   

       periodicComboBox.setBounds(50,350,200,30);
       periodicComboLabel.setBounds(50,320,400,30);
       
       addButton.setBounds(50,400,100,30);
       cancelButton.setBounds(250,400,100,30);

        periodicR1.addActionListener(e-> showPeriodic(true));
        periodicR2.addActionListener(e-> showPeriodic(false));
 
    }

    public void showPeriodic(Boolean isShown) {
        periodicComboBox.setVisible(isShown);
        periodicComboLabel.setVisible(isShown);
    }

    public void addComponentsToContainer()
    {
        //Adding each components to the Container
       
        container.add(activityNameLabel);
        container.add(activityField);
        container.add(startLabel);
        container.add(finishLabel);
        container.add(urgentLabel);
        container.add(urgentR1);
        container.add(urgentR2);
        container.add(periodicR1);
        container.add(periodicR2);
        container.add(periodicLabel);
        container.add(periodicComboBox);
        container.add(periodicComboLabel);
        
        ButtonGroup bg=new ButtonGroup();    
        bg.add(urgentR1);bg.add(urgentR2);   
        urgentR2.setSelected(true);
        ButtonGroup bg2=new ButtonGroup();    
        bg2.add(periodicR1);bg2.add(periodicR2);   
        periodicR2.setSelected(true);
        
        container.add(addButton);
        container.add(cancelButton);
       
   }

  
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
