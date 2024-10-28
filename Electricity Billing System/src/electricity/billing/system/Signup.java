package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Signup  extends JFrame implements ActionListener {

    Choice loginAsCho;
    JTextField meterText,EmployerText,userNameText,nameText,passwordText;

    JButton create,back;


    Signup()

    {
        super("Signup Page");

        getContentPane().setBackground(new Color(168,202,255));

        JLabel createAs = new JLabel("Create account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginAsCho = new Choice();
        loginAsCho.add("Admin");
        loginAsCho.add("Customer");
        loginAsCho.setBounds(170,50,120,20);
        add(loginAsCho);

        JLabel meterNo  = new JLabel("Meter Number");
        meterNo.setBounds(30,100,125,20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new JTextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30,100,125,20);
        Employer.setVisible(true);
        add(Employer);

        EmployerText= new JTextField();
        EmployerText.setBounds(170,100,125,20);
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(30,140,125,20);
        add(userName);

        userNameText = new JTextField();
        userNameText.setBounds(170,140,125,20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);


        nameText = new JTextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup1  where meter_no = '"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });




        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);

     loginAsCho.addItemListener(e -> {
         String user =loginAsCho.getSelectedItem();
         if(user.equals("Customer"))
         {
             Employer.setVisible(false);
             EmployerText.setVisible(false);
             nameText.setEditable(false);
             meterNo.setVisible(true);
             meterText.setVisible(true);


         }
         else{
             Employer.setVisible(true);
             EmployerText.setVisible(true);
             meterNo.setVisible(false);
             meterText.setVisible(false);
         }
     });

     create = new JButton("Create");
     create.setBackground(new Color(66, 127, 219));
     create.setForeground(Color.black);
     create.setBounds(50,285,100,25);
     create.addActionListener(this);
     add(create);



     back = new JButton("Back");
     back.setBackground(new Color(66, 127, 219));
     back.setForeground(Color.black);
     back.setBounds(180,285,100,25);
     back.addActionListener(this);
     add(back);


     ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
     Image boyImg = boyIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
     ImageIcon boyIcon1 = new ImageIcon(boyImg);
     JLabel boyLabel = new JLabel(boyIcon1);
     boyLabel.setBounds(320,30,250,250);
     add(boyLabel);













        setSize(600,380);
        setLocation(500,200);
        setLayout(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {

            String sLoginAs =loginAsCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter =meterText.getText();








            try {
                database c = new database();
                String query= null;
                if (loginAsCho.equals("Admin")) {
                    query = "insert into Signup1 value('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sLoginAs + "')";
                }
                else {
                    query = "update Signup1 set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sLoginAs+"' where meter_no = '"+smeter+"'";
                }
                c.statement.executeUpdate(query);

                JOptionPane.showInternalMessageDialog(null,"Account Created");

                setVisible(false);

                new Login();


            } catch (SQLException E) {
                E.printStackTrace();
            }

        }

        else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }


    }

    public static void main(String[] args) {

        new Signup();
    }
}
