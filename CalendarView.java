package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

import controller.DBController;
import model.*;
import java.time.*;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;  


public class CalendarView {

  
    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext;
    static JTable tblCalendar;
  
    static JFrame frmMain;
    static Container pane;
    public static Boolean showHicriCalendar=false;
  

    public static JLabel colorPalette = new JLabel();
    public static JLabel culturalHolidays  = new JLabel();
    public static JLabel religiousDays = new JLabel();
    public static JLabel quickEvents = new JLabel();
    public static JLabel reports = new JLabel();
    public static JLabel changeType = new JLabel();
    public JLabel clock= new JLabel();
    public JButton about=new JButton("About");
    public JButton contacts=new JButton("Contacts");

    static JPanel pnlCalendar;
    static int realYear, realMonth, realDay;
    public static int currentYear;
    public static int currentMonth;
    static JPanel daysPanel[] = new JPanel[7];
    static JLabel daysLabel[] = new JLabel[7];
    static JPanel child[] = new JPanel[42];
    public static JLabel childLabel[] = new JLabel[42];
    public static JLabel childLabelHicri[] = new JLabel[42];
    
    public static JLabel imgMinus[] = new JLabel[42]; 
    public static JLabel imgPlus[] = new JLabel[42];
    public static JLabel imgGoz[] = new JLabel[42];
    static Person person;
    public static ArrayList<Holiday> allHolidays=new ArrayList<Holiday>();

    
    

 public CalendarView(Person p) {
 
        this.person=p;
        //Prepare frame
        allHolidays=getAllHolidays(p);


    
        frmMain = new JFrame ("New Generation Calendar"); //Create frame
        frmMain.setSize(900, 800); //Set size to 400x400 pixels
        frmMain.setLocationRelativeTo(null); 
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        
        //Create controls
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("2022");
        btnPrev = new JButton ("<");
        btnNext = new JButton (">");
    
        
        pnlCalendar = new JPanel(null);
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 26));
        lblMonth.setForeground(Color.WHITE);
        lblYear.setFont(new Font("Verdana", Font.PLAIN, 26));
        lblYear.setForeground(Color.WHITE);
        lblYear.setVisible(false);

 
        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder(person.getUserId()));
        pnlCalendar.setBackground(Color.decode("#647687"));
        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        
        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        


        //Set bounds
        pnlCalendar.setBounds(0, 0, 890, 750);
        lblMonth.setBounds(180, 180, 500, 50);
        lblYear.setBounds(450, 180, 150, 50);
        clock.setBounds(10, 5, 300, 50);
        clock.setFont(new Font("Tahoma", Font.ITALIC, 20));
        clock.setForeground(Color.WHITE);
        pnlCalendar.add(clock);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");  
        String strDate = dateFormat.format(new Date());  
        clock.setText(strDate);
    
        btnPrev.setBounds(30, 436, 50, 25);
        btnNext.setBounds(800, 436, 50, 25);


        pnlCalendar.add(lblYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);

        contacts.setBounds(720, 8, 90, 20);
        about.setBounds(810, 8, 75, 20);
        pnlCalendar.add(contacts);
        pnlCalendar.add(about);
        

        colorPalette=new JLabel(new ImageIcon("lib/colorPalette.png"));
        colorPalette.setBounds(40, 50, 112, 112);
        colorPalette.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlCalendar.add(colorPalette);

        culturalHolidays=new JLabel(new ImageIcon("lib/culturalDays.png"));
        culturalHolidays.setBounds(180, 50, 112, 112);
        culturalHolidays.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlCalendar.add(culturalHolidays);

        religiousDays=new JLabel(new ImageIcon("lib/religiousDays.png"));
        religiousDays.setBounds(320, 50, 112, 112);
        religiousDays.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlCalendar.add(religiousDays);

        quickEvents=new JLabel(new ImageIcon("lib/quickEvents.png"));
        quickEvents.setBounds(460, 50, 112, 112);
        quickEvents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlCalendar.add(quickEvents);

        reports=new JLabel(new ImageIcon("lib/reports.png"));
        reports.setBounds(600, 50, 112, 112);
        reports.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlCalendar.add(reports);

        changeType=new JLabel(new ImageIcon("lib/changeType.png"));
        changeType.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeType.setBounds(740, 50, 112, 112);
        pnlCalendar.add(changeType);

        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int j=0; j<7; j++){
        //   .addColumn();
        daysPanel[j] =new JPanel();
        daysPanel[j].setBackground(Color.decode("#e1d5e7"));
        pnlCalendar.add(daysPanel[j]);
        daysPanel[j].setBounds(100+j*97, 240, 96, 54);
        daysLabel[j] =new JLabel();
        daysLabel[j].setText(headers[j]);
        daysLabel[j].setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        daysPanel[j].add(daysLabel[j]);

        }
             //Add days of month
          for (int i=0;i<6;i++)
          {
            for (int j=0;j<7;j++)
            {
               
                child[i*7+j] =new JPanel();
                imgPlus[i*7+j]=new JLabel(new ImageIcon("lib/plus.png"));
                imgMinus[i*7+j]=new JLabel(new ImageIcon("lib/minus.png"));
                imgGoz[i*7+j]=new JLabel(new ImageIcon("lib/goz2.png"));
                childLabel[i*7+j] =new JLabel();
                childLabelHicri[i*7+j] =new JLabel();
                
                pnlCalendar.add(child[i*7+j]);
                child[i*7+j].setBackground(Color.decode("#ffda6d"));
                if (j==0 || j==6) child[i*7+j].setBackground(Color.decode("#dfb922"));
                child[i*7+j].setBounds(100+j*97, 290+i*70, 96, 69);
                childLabel[i*7+j].setText("");
                childLabel[i*7+j].setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
                child[i*7+j].add( imgGoz[i*7+j]);
                child[i*7+j].setLayout(null);
                imgGoz[i*7+j].setBounds(65, 5, 25, 25);
                child[i*7+j].add( childLabel[i*7+j]);
                
                childLabel[i*7+j].setVerticalAlignment(JLabel.TOP);
                
                
                // Calender Type Show
                childLabel[i*7+j].setBounds(30, 23, 50, 70);  //greg
                
              
                // Calender Type HIJDRI
               
                childLabelHicri[i*7+j].setBounds(27, 13, 60, 70);
                childLabelHicri[i*7+j].setVerticalAlignment(JLabel.TOP);
                child[i*7+j].add( childLabelHicri[i*7+j]);
                childLabelHicri[i*7+j].setVisible(false);
                
                child[i*7+j].add( imgPlus[i*7+j]);
                imgPlus[i*7+j].setBounds(5, 43, 25, 25);
                child[i*7+j].add( imgMinus[i*7+j]);
                imgMinus[i*7+j].setBounds(65, 43, 25, 25);
                imgPlus[i*7+j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                imgMinus[i*7+j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                imgGoz[i*7+j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
           
          }
        }
         
          //Make frame visible
          frmMain.setResizable(false);
          frmMain.setVisible(true);
          
          //Get real month/year
          GregorianCalendar cal = new GregorianCalendar(); //Create calendar
          realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
          realMonth = cal.get(GregorianCalendar.MONTH); //Get month
          realYear = cal.get(GregorianCalendar.YEAR); //Get year
          currentMonth = realMonth; //Match month and year
          currentYear = realYear;
          
          //Refresh calendar
          refreshCalendar (realMonth, realYear,person); //Refresh calendar
      }
      
      public static ArrayList<Holiday> getAllHolidays(Person p) {
        ArrayList<Holiday> n=new ArrayList<Holiday>();
        ArrayList<Holiday> r=new ArrayList<Holiday>();
        DBController db=new DBController();

        n=db.getHolidaysDB("C", p.getNationality());
        r=db.getHolidaysDB("R", p.getReligion());
        n.addAll(r);
        return n;

      }



      public static void refreshCalendar(int month, int year,Person p){
          //Variables
          String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
          int nod, som; //Number Of Days, Start Of Month
          //Allow/disallow buttons
          btnPrev.setEnabled(true);
          btnNext.setEnabled(true);
          if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
          if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
          lblMonth.setText(months[month] + " " + String.valueOf(year)); //Refresh the month label (at the top)
          lblYear.setText(String.valueOf(year)); //Refresh the month label (at the top)
          lblMonth.setVerticalAlignment(SwingConstants.CENTER);

  
          //Initialize calendar
          for (int i=0; i<6; i++){
              for (int j=0; j<7; j++){
                    child[i*7+j].setBackground(Color.decode("#ffda6d"));
                    if (j==0 || j==6) child[i*7+j].setBackground(Color.decode("#dfb922"));
                    childLabel[i*7+j].setText("");
                    imgMinus[i*7+j].setVisible(false);
                    imgPlus[i*7+j].setVisible(false);
                    imgGoz[i*7+j].setVisible(false);

                    // Hicri = ON
                    if (showHicriCalendar) {
                        childLabel[i*7+j].setBounds(3, 3, 30, 30);  //hicri
                        childLabel[i*7+j].setFont(new Font("Verdana", Font.ITALIC, 12));
                        childLabel[i*7+j].setText("");
                        childLabelHicri[i*7+j].setText("");
                        childLabelHicri[i*7+j].setVisible(true);
                    } else {
                        childLabelHicri[i*7+j].setVisible(false);
                        childLabel[i*7+j].setBounds(30, 23, 50, 70);
                        childLabel[i*7+j].setText("");
                        childLabel[i*7+j].setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));

                    }
              }
          }
   
          //Get first day of month and number of days
          GregorianCalendar cal = new GregorianCalendar(year, month, 1);
          nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
          som = cal.get(GregorianCalendar.DAY_OF_WEEK);

          // Hijdrah Calendar
          HijrahDate islamyDate,islamyDate2;
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
          islamyDate2= HijrahChronology.INSTANCE.date(LocalDate.of(year,(month+1), 14)); 
          //Draw calendar gregorian
          for (Integer i=1; i<=nod; i++){
              int row = new Integer((i+som-2)/7);
              int column  =  (i+som-2)%7;

              for (int j=0;j<allHolidays.size();j++) {
                if (i>=allHolidays.get(j).getStartDay().getDate() && i<=allHolidays.get(j).getEndDay().getDate() && (currentMonth)==allHolidays.get(j).getStartDay().getMonth()) {
                   // put type here XXXXX
                   if (allHolidays.get(j).getHolidayType().equals("R"))   child[row*7+column].setBackground(Color.decode("#f4c1e9"));
                   else child[row*7+column].setBackground(Color.decode("#a8b9e9"));

                  
                }
            }
              islamyDate= HijrahChronology.INSTANCE.date(LocalDate.of(year,(month+1), i)); 
              childLabel[row*7+column].setText(i.toString());

              // Hicri = ON
              if (showHicriCalendar) {
                childLabelHicri[row*7+column].setText("<html>"+ outputFormatter.ofPattern("dd").format(islamyDate) + 
                    "<br>"+ outputFormatter.ofPattern("MMMM").format(islamyDate)+"</html>");
                lblMonth.setText(months[month] + " " + String.valueOf(year) + " / " +  outputFormatter.ofPattern("MMMM").format(islamyDate2) 
                    + " " + outputFormatter.ofPattern("yyyy").format(islamyDate2)         ); //Refresh the month label (at the top)
              }
             
              imgMinus[row*7+column].setVisible(true);
              imgPlus[row*7+column].setVisible(true);
              imgGoz[row*7+column].setVisible(true);
          }
          



      }
      
      static class btnPrev_Action implements ActionListener{
          public void actionPerformed (ActionEvent e){
              if (currentMonth == 0){ //Back one year
                  currentMonth = 11;
                  currentYear -= 1;
              }
              else{ //Back one month
                  currentMonth -= 1;
              }
              refreshCalendar(currentMonth, currentYear,person);
          }
      }
      static class btnNext_Action implements ActionListener{
          public void actionPerformed (ActionEvent e){
              if (currentMonth == 11){ //Foward one year
                  currentMonth = 0;
                  currentYear += 1;
              }
              else{ //Foward one month
                  currentMonth += 1;
              }
              refreshCalendar(currentMonth, currentYear,person);
          }
      }
  
}
