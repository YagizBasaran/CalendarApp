package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import controller.*;
import model.Person;


 
public class LoginFrame extends JFrame implements ActionListener {
 
    
    Container container=getContentPane();
    JLabel mainTitle=new JLabel("New Generational Calendar App");
    JLabel userLabel=new JLabel("Username");
    JLabel passwordLabel=new JLabel("Password");
    public JTextField userTextField=new JTextField("");
    public JPasswordField passwordField=new JPasswordField("");
    public JButton loginButton=new JButton("Login");
    
    
    JLabel labelSignUp = new JLabel("Don't have an account yet? Sign Up.");
    JLabel labelForgetPass = new JLabel("Forgot Password?");
    
    JLabel imgLabel = new JLabel(new ImageIcon("lib/agendaNew.png"));
    JLabel imgGoz = new JLabel(new ImageIcon("lib/goz.png"));
 
 
    public LoginFrame(String title)
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Login");
        setVisible(true);
        setBounds(10,10,450,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
        mainTitle.setFont(new Font("Verdana", Font.PLAIN, 22));
        mainTitle.setForeground(Color.WHITE);
        labelSignUp.setForeground(Color.WHITE);
        labelSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelForgetPass.setForeground(Color.WHITE);
        labelForgetPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgGoz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        userLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        userTextField.setFont(new Font("Verdana", Font.PLAIN, 18));
        userLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 18));

       
        labelForgetPass.addMouseListener((MouseListener) new MouseListener() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label

                if (userTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your username !!");
                } else {
                    DBController dbController=new DBController();
                    Person rc= dbController.getPasswordDB(userTextField.getText());
                    if (rc==null) { 
                        JOptionPane.showMessageDialog(null, "Username " + userTextField.getText() + " not found. Please Signup !");
                    } else {
                        
                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.transport.protocol", "smtp");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.socketFactory.port", "587");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

                        Session session = Session.getDefaultInstance(props);
                        try {
                            InternetAddress fromAddress = new InternetAddress("bilkentcs102project@gmail.com");
                            InternetAddress toAddress = new InternetAddress(rc.getEmail());

                            Message message = new MimeMessage(session);
                            message.setFrom(fromAddress);
                            message.setRecipient(Message.RecipientType.TO, toAddress);
                            message.setSubject("Forgot Password for New Generation Calendar");
                            message.setText("Hi, Your Password is:"+ rc.getPassword() );

                            Transport.send(message, "bilkentcs102project@gmail.com","Cenkolog1.");
                        } catch (MessagingException ex) {
                            ex.printStackTrace();
                        }
                        
                        JOptionPane.showMessageDialog(null,  userTextField.getText() + "'s password has been sent to " + rc.getEmail() );
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
      
        labelSignUp.addMouseListener((MouseListener) new MouseListener() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label
               new SignUpFrame("sign Up");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        imgGoz.addMouseListener((MouseListener) new MouseListener() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label
                if (passwordField.getEchoChar()!=(char)0) {
                    passwordField.setEchoChar((char)0); //password = JPasswordField
                } else { 
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
       mainTitle.setBounds(50,5,400,30);
       userLabel.setBounds(50,150,150,30);
       passwordLabel.setBounds(50,200,100,30);
       userTextField.setBounds(150,150,250,30);
       passwordField.setBounds(150,200,200,30);
     
       loginButton.setBounds(150,380,100,30);
  
       labelSignUp.setBounds(200,300,220,30);
       labelForgetPass.setBounds(295,260,150,30);
       imgLabel.setBounds(150,30,100,100);
       imgGoz.setBounds(360,200,30,30);
       
      
 
 
    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(mainTitle); 
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);  
        container.add(loginButton);
        container.add(labelSignUp);
        container.add(labelForgetPass);
        
        container.add(imgLabel);
        container.add(imgGoz);
   }

  
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
