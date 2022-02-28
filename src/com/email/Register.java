package com.email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.database.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JLabel lblNewLabel_1_4;
	private JPasswordField password;
	private JPasswordField cpassword;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					Register frame = new Register();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public Register() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(186, 63, 371, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(43, 171, 89, 27);
		contentPane.add(lblNewLabel_1);
		
		firstName = new JTextField();
		
		firstName.setBounds(43, 208, 215, 38);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		Random random=new Random();
		int ran=random.nextInt(10);
		
		lastName = new JTextField();
		lastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String firstname=firstName.getText();
				String lastname=lastName.getText();
				if(!(firstname.equals("") && lastname.equals(""))) {
					
					String randno=String.valueOf(ran);
					String emailtxt=firstname.toLowerCase()+lastname.toLowerCase()+randno+"@gmail.com";
					email.setText(emailtxt);
					email.setEditable(false);	
				}
			}
		});
		lastName.setColumns(10);
		lastName.setBounds(400, 208, 215, 38);
		contentPane.add(lastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(400, 171, 89, 27);
		contentPane.add(lblNewLabel_1_1);
		
		email = new JTextField();
		email.setOpaque(false);
		email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		email.setColumns(10);
		email.setBounds(43, 334, 572, 38);
		contentPane.add(email);
		
		
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(43, 297, 89, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(47, 401, 89, 27);
		contentPane.add(lblNewLabel_1_3);
		
		lblNewLabel_1_4 = new JLabel("Confirm Password");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_4.setBounds(43, 528, 131, 27);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel validmsg4 = new JLabel("Password must be at least 8 characters long.");
		validmsg4.setForeground(Color.GREEN);
		validmsg4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		validmsg4.setBounds(333, 496, 331, 13);
		contentPane.add(validmsg4);
		
		JLabel validmsg1 = new JLabel("Password must contains one captial letters.");
		validmsg1.setForeground(Color.GREEN);
		validmsg1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		validmsg1.setBounds(333, 426, 331, 13);
		contentPane.add(validmsg1);
		
		JLabel validmsg2 = new JLabel("Password must contains three small letters.");
		validmsg2.setForeground(Color.GREEN);
		validmsg2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		validmsg2.setBounds(333, 449, 331, 13);
		contentPane.add(validmsg2);
		
		JLabel validmsg3 = new JLabel("Password must contains at least one numbers.");
		validmsg3.setForeground(Color.GREEN);
		validmsg3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		validmsg3.setBounds(333, 473, 331, 13);
		contentPane.add(validmsg3);
		
		JLabel validmsg5 = new JLabel("Password contains only at least one special charactor.");
		validmsg5.setForeground(Color.GREEN);
		validmsg5.setFont(new Font("Tahoma", Font.ITALIC, 12));
		validmsg5.setBounds(333, 519, 331, 13);
		contentPane.add(validmsg5);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		submitBtn.setBounds(517, 613, 147, 45);
		contentPane.add(submitBtn);
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstname=firstName.getText();
				String lastname=lastName.getText();
				String emaildb=email.getText();
				String password1=password.getText();
				String password2=cpassword.getText();
				boolean flag=validatePassword(password1);
				try 
				{
				if(password1.equals(password2) && flag==true)
					{
						
						
						
							    DbConnection db=new DbConnection();
							    Connection conn=db.getConnection();
								PreparedStatement pr=conn.prepareStatement("insert into userlogin values(?,?,?,?)");
								pr.setString(1,firstname);
								pr.setString(2,lastname);
								pr.setString(3,emaildb);
								pr.setString(4,password1);
								int i=pr.executeUpdate(); 
								if(i>0) 
								{
									JOptionPane.showMessageDialog(submitBtn, "You have successfully Registerd");
									dispose();
				                	  Login login=new Login();
				                	  login.setVisible(true);
								}
								else 
								{
									JOptionPane.showMessageDialog(submitBtn, "Registered Unsuccessfully");
								}	
						
					}
					else 
					{
						JOptionPane.showMessageDialog(submitBtn, "Please match the password");
					}
					
				}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(submitBtn, "Registered Unsuccessfully!!!! Try with another email");				
					e1.printStackTrace();
				}						
			}

			private boolean validatePassword(String password1) {
				
				int specialCount=0;
				int lowerCount=0;
				int upperCount=0;
				int digitCount=0;
				
			final int valid_letter=8;
			final int valid_upper=1;
			final int valid_lower=3;
			final int valid_digits=1;
			final int valid_special=1;
			
			int passwordLength=password1.length();
			for(int i=0;i<passwordLength;i++) 
			{
				char ch=password1.charAt(i);
				if(Character.isUpperCase(ch))
				{
					upperCount++;
				}
				else if(Character.isLowerCase(ch)) 
				{
					lowerCount++;
				}
				else if(Character.isDigit(ch)) 
				{
					digitCount++;
				}else if (!Character.isDigit(ch)
		                && !Character.isLetter(ch)
		                && !Character.isWhitespace(ch)) {
		                specialCount++;
		            }
			}
		
		if(upperCount>=valid_upper && lowerCount>=valid_lower && digitCount>=valid_digits && passwordLength>= valid_letter && specialCount>=valid_special) {
			return true;
			
		}else {
			if(upperCount>=valid_upper) 
		      {
				validmsg1.setForeground(Color.GREEN);
							  }
		    else
			{
				validmsg1.setForeground(Color.RED);
			
			}
			if(lowerCount>=valid_lower)
			{
				validmsg2.setForeground(Color.GREEN);
			
			}
			else 
			{
				validmsg2.setForeground(Color.RED);
			} 
			if(digitCount>=valid_digits) 
			{
				validmsg3.setForeground(Color.GREEN);
			
			}
			else
			{
				validmsg3.setForeground(Color.RED);
			
			}
			if(passwordLength>= valid_letter) 
			{
				validmsg4.setForeground(Color.GREEN);
				
			}
			else 
			{
				validmsg4.setForeground(Color.RED);	
			}
			if((specialCount>=valid_special)) {
				validmsg5.setForeground(Color.GREEN);			
			}
			else 
			{
				validmsg5.setForeground(Color.RED);	
			}
			return false;
			
		}
			
		      
			
				
			
			}
		});
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Sign in instead");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
          	  Login login=new Login();
          	  login.setVisible(true);
			}
		});
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(351, 613, 138, 45);
		contentPane.add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String password1=password.toString();
				
			
				
			}
		});
		password.setBounds(47, 438, 215, 38);
		contentPane.add(password);
		
		cpassword = new JPasswordField();
		cpassword.setBounds(43, 565, 215, 38);
		contentPane.add(cpassword);
		
		JCheckBox showpaswordCheckBox = new JCheckBox("Show Password");
		showpaswordCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(showpaswordCheckBox.isSelected()) {
					password.setEchoChar((char)0);
					cpassword.setEchoChar((char)0);
				}else {
					password.setEchoChar('*');
					cpassword.setEchoChar('*');
				}
			}
		});
		
		showpaswordCheckBox.setBounds(43, 498, 171, 21);
		contentPane.add(showpaswordCheckBox);	
		
	}
}
