package view;

import javax.swing.*;
import controller.DBController;
import model.*;
import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.util.Date;

 
public class Notification extends JFrame implements ActionListener {
 
    Container container=getContentPane();   
    JLabel activityNameLabel=new JLabel("Activity Start Reminder");
    JLabel activityDescription=new JLabel();
    
    public JButton cancelButton=new JButton("Close");
    public JButton yesButton=new JButton("Yes");
    public JButton noButton=new JButton("No");
    private Boolean attendance;

 
    public Notification(Activity activity, Boolean attendance, String username)
    {
       //Calling methods inside constructor.
       this.attendance=attendance;
   
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle(activity.getActivityDescription());
        setVisible(true);
        
        if (attendance) {
            activityNameLabel.setText("Activity Finish Reminder");
            activityDescription.setText("<html>It is "+ new SimpleDateFormat("HH:mm").format(new Date()) + " now. You should have finished your '" + activity.getActivityDescription() + "' activity<br><br>Did you attend ?</html>");
        } else {
            activityNameLabel.setText("Activity Start Reminder");
            activityDescription.setText("<html>It is "+ new SimpleDateFormat("HH:mm").format(new Date()) + " now. Time for " + activity.getActivityDescription() + "</html>");
          
        }
        setBounds(10,10,500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
      
        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);

        activityDescription.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        activityDescription.setForeground(Color.WHITE);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesButtonPressed(activity,username);
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noButtonPressed(activity,username);
            }
        });
        
        
    }

    
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
      
       activityNameLabel.setBounds(60,60,350,30);
       activityDescription.setBounds(60,110,350,100);
       cancelButton.setBounds(200,400,100,30);
       yesButton.setBounds(150,400,80,30);
       noButton.setBounds(250,400,80,30);

    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
       
        container.add(activityNameLabel);  
        container.add(activityDescription);  
        if (attendance) {
            container.add(yesButton);container.add(noButton);
          
            
        } else {
            container.add(cancelButton);
        }
        
       
   }
   public ActionListener yesButtonPressed(Activity a, String p) {
       DBController c=new DBController();
       if (c.saveAttendanceDB(a, p,1)) this.dispose();
       return null;
       
   }
   public ActionListener noButtonPressed(Activity a, String p) {
    DBController c=new DBController();
    if (c.saveAttendanceDB(a, p,2)) this.dispose();
    return null;
    
}
   

  
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
