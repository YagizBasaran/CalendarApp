package view;

import javax.swing.*;



import controller.DBController;
import model.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

 
public class Report extends JFrame implements ActionListener {
 
    Container container=getContentPane();   
    JLabel activityNameLabel=new JLabel("Weekly Report");
    JLabel activityDescription=new JLabel();
    
    public JButton cancelButton=new JButton("Close");
    public JButton yesButton=new JButton("Yes");
    public JButton noButton=new JButton("No");
    

   
 
    public Report(String username)
    {
       //Calling methods inside constructor.
        container.setLayout(null);
        setVisible(true);
        
        activityNameLabel.setText("Weekly Report");
      
        setBounds(10,10,500,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
      
        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);

        activityDescription.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        activityDescription.setForeground(Color.WHITE);

        activityNameLabel.setBounds(160,10,350,30);
        activityDescription.setBounds(60,530,350,100);
        cancelButton.setBounds(200,620,100,30);
        container.add(activityNameLabel);  
        container.add(activityDescription);  
        container.add(cancelButton);

        JLabel headerActivity=new JLabel();
        JLabel headerDate=new JLabel();
        JLabel headerTime=new JLabel();
        headerActivity.setText("Activity");
        headerDate.setText("Date");
        headerTime.setText("Time");
        headerActivity.setOpaque(true);
        headerActivity.setBackground(Color.decode("#00a8f3"));
        headerActivity.setFont(new Font("Verdana", Font.ITALIC | Font.BOLD, 14));
        headerActivity.setBounds(10,50,250,30);

        headerDate.setOpaque(true);
        headerDate.setBackground(Color.decode("#00a8f3"));
        headerDate.setFont(new Font("Verdana", Font.ITALIC | Font.BOLD, 14));
        headerDate.setBounds(262,50 ,125,30);

        headerTime.setOpaque(true);
        headerTime.setBackground(Color.decode("#00a8f3"));
        headerTime.setFont(new Font("Verdana", Font.ITALIC | Font.BOLD, 14));
        headerTime.setBounds(390,50,75,30);
        container.add(headerActivity);
        container.add(headerDate);
        container.add(headerTime);


        DBController dbController=new DBController();
        LocalDate today=LocalDate.now();
      
        ArrayList<Activity> al= dbController.getWeeklyActivitiesDB(today.format(DateTimeFormatter.ISO_LOCAL_DATE),today.minusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE),  username);
        int missed=0;
        int attended=0;
        Long diff=(long) 0;
       
        for (int i=0;i<al.size();i++) {
            JLabel reportActivity=new JLabel();
            JLabel reportDate=new JLabel();
            JLabel reportTime=new JLabel();
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");  
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            diff=diff + al.get(i).getFinishTime().getTime() - al.get(i).getStartTime().getTime();
           
            reportActivity.setText(al.get(i).getActivityDescription());
            reportDate.setText(dateFormat.format(al.get(i).getStartTime()));
            reportTime.setText(timeFormat.format(al.get(i).getStartTime()));
            if (al.get(i).getIsAttended()==1) attended++;
            if (al.get(i).getIsAttended()==2) missed++;
            
            reportActivity.setOpaque(true);
            reportActivity.setBackground(Color.decode("#dd9ebb"));
            reportActivity.setFont(new Font("Verdana", Font.ITALIC | Font.BOLD, 14));
            reportActivity.setBounds(10,82+ 32*i,250,30);
            reportDate.setOpaque(true);
            reportDate.setBackground(Color.decode("#dd9ebb"));
            reportDate.setFont(new Font("Verdana", Font.ITALIC, 14));
            reportDate.setBounds(262,82+ 32*i,125,30);
            reportTime.setOpaque(true);
            reportTime.setBackground(Color.decode("#dd9ebb"));
            reportTime.setFont(new Font("Verdana", Font.ITALIC, 14));
            reportTime.setBounds(390,82+ 32*i,75,30);
            container.add(reportActivity);
            container.add(reportDate);
            container.add(reportTime);
        }
      

        activityDescription.setText("<html>"+ al.size()  + " Events are recorded this week. You missed <font color='RED'>" + 
            missed +"</font> of them and attended <font color='#c4ff0e'>" + attended + 
            "</font>.<br><br>Total Hours Spent : "+ formatDuration(diff) + "</html>");
      

    }
    private static String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        return String.format("%02d hours, %02d minutes.", hours, minutes);
    }
   
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
