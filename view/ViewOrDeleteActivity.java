package view;

import javax.swing.*;

import controller.DBController;
import model.*;



import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

 
public class ViewOrDeleteActivity extends JFrame implements ActionListener {
 
    Container container=getContentPane();
    JLabel activityNameLabel=new JLabel("Activity Name                     Time");
    public JLabel holidayLabel=new JLabel();
    public JButton cancelButton=new JButton("Cancel");
    UserColor userColor;

 
    public ViewOrDeleteActivity(String title, UserColor userColor)
    {
       //Calling methods inside constructor.
       this.userColor=userColor;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle(title);
        setVisible(true);
        
        setBounds(10,10,500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
        
        holidayLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        holidayLabel.setForeground(Color.BLACK);
        holidayLabel.setOpaque(true);
        holidayLabel.setBackground(Color.decode("#ffe494"));
        holidayLabel.setVisible(false);

        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);
        
    }

    
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
       holidayLabel.setBounds(10,20,460,30);
       activityNameLabel.setBounds(60,60,350,30);
       cancelButton.setBounds(200,400,100,30);
    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
       
        container.add(activityNameLabel);  
        container.add(holidayLabel);
        container.add(cancelButton);
       
   }
   public ActionListener deleteButtonPressed(Activity a, Person p) {
       DBController c=new DBController();
       if (c.deleteActivityDB(p, a)) this.dispose();
       return null;
       
   }
   public void noActivities() {
        JLabel noActivityLabel=new JLabel("There is no activity for this day");
        noActivityLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        noActivityLabel.setForeground(Color.RED);
        noActivityLabel.setBounds(60,100,450,30);
        container.add(noActivityLabel);
   }

   public void addActivitiesComponentsToContainer(Activity activity, int i,String mode,Person p)
    {
        //Adding each components to the Container
        // Delete or View depend on mode
        JLabel deleteActivityLabel=new JLabel(activity.getActivityDescription());
        DateFormat df = new SimpleDateFormat("HH:mm");  
        JLabel deleteActivityStart=new JLabel(df.format(activity.getStartTime()));
        deleteActivityStart.setText(deleteActivityStart.getText() + " -");
        JLabel deleteActivityFinish=new JLabel(df.format(activity.getFinishTime()));
        
        JButton deleteButton=new JButton();
        deleteButton.setText("X");
        deleteButton.setBounds(20,100+ i*35,45,30);
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed(activity,p);
            }
        });
       

        if (mode.equals("D")) {
            container.add(deleteButton);
            // make smaller and put X button
            deleteActivityLabel.setBounds(70,100+i*35,250,30);
            deleteActivityStart.setBounds(330,100+i*35,70,30);
            deleteActivityFinish.setBounds(400,100+i*35,70,30);
            
        } else {
            // make it larger
            deleteActivityLabel.setBounds(10,100+i*35,310,30);
            deleteActivityStart.setBounds(330,100+i*35,70,30);
            deleteActivityFinish.setBounds(400,100+i*35,70,30);

        }
       
       
       
        deleteActivityLabel.setForeground(Color.BLACK);
        deleteActivityLabel.setOpaque(true);
        deleteActivityLabel.setBackground(Color.decode(userColor.getUpcoming()));
        if (activity.getUrgent()) { // urgent
            deleteActivityLabel.setBackground(Color.decode(userColor.getUrgent()));
        }
        if (activity.getPeriodic()) { // periodic
            deleteActivityLabel.setBackground(Color.decode(userColor.getPeriodic()));
        }
        if (activity.getIsAttended()==1) { //attended
            deleteActivityLabel.setBackground(Color.decode(userColor.getAttended()));
        }
        if (activity.getIsAttended()==2) { // unattended
            deleteActivityLabel.setBackground(Color.decode(userColor.getMissed()));
        }
       
        deleteActivityLabel.setFont(new Font("Tahoma",  Font.ITALIC, 18));

        deleteActivityStart.setForeground(Color.BLACK);
        deleteActivityStart.setOpaque(true);
        deleteActivityStart.setBackground(new Color(255, 223, 129));
        deleteActivityStart.setFont(new Font("Tahoma", Font.ITALIC, 18));

        deleteActivityFinish.setForeground(Color.BLACK);
        deleteActivityFinish.setOpaque(true);
        deleteActivityFinish.setBackground(new Color(255, 223, 129));
        deleteActivityFinish.setFont(new Font("Tahoma", Font.ITALIC, 18));
     
       
        container.add(deleteActivityLabel);
        container.add(deleteActivityStart);
        container.add(deleteActivityFinish);
       
       
   }

  
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
