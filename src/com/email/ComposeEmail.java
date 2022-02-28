package com.email;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.database.DbConnection;
import com.entities.Email;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ComposeEmail extends JFrame {

	private JPanel contentPane;
	private JTextField totxt;
	private JTextField subjecttxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String email=null;
					String subject = null;
					String loginemail = null;
					ComposeEmail frame = new ComposeEmail(email,subject,loginemail);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComposeEmail(String email,String subject,String loginemail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,720, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(239, 62, 193, 45);
		contentPane.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1_3 = new JLabel("To:-");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(50, 207, 89, 27);
		contentPane.add(lblNewLabel_1_3);
		
		totxt = new JTextField();
		totxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String toemail=totxt.getText();
				DbConnection db=new DbConnection();
				try {
					Connection conn=db.getConnection();
					PreparedStatement ps=conn.prepareStatement("select email from userlogin where email=?");
					ps.setString(1,toemail);
					ResultSet result=ps.executeQuery();
					if(!result.next()) {
						JOptionPane.showMessageDialog(null, "Email does not exist");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		totxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totxt.setBounds(149, 200, 515, 45);
		contentPane.add(totxt);
		totxt.setColumns(10);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Subject:-");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(50, 274, 89, 27);
		contentPane.add(lblNewLabel_1_3_1);
		
		subjecttxt = new JTextField();
		subjecttxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subjecttxt.setColumns(10);
		subjecttxt.setBounds(149, 267, 515, 45);
		contentPane.add(subjecttxt);
		
		JTextArea contecttxt = new JTextArea();
		contecttxt.setFont(new Font("Monospaced", Font.PLAIN, 14));
		contecttxt.setBounds(149, 344, 515, 211);
		contentPane.add(contecttxt);
		
		JButton sendBtn = new JButton("Send");
		sendBtn.setForeground(new Color(255, 255, 255));
		sendBtn.setBackground(new Color(30, 144, 255));
		sendBtn.setBounds(516, 603, 124, 45);
		contentPane.add(sendBtn);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 dispose();
                 dashboard dash=new dashboard(loginemail);
                 dash.setVisible(true);
                 
			}
		});
		btnNewButton.setBounds(10, 22, 113, 45);
		contentPane.add(btnNewButton);
		
		if(subject!=null) {
			totxt.setText(email);
			totxt.setEditable(false);
			subjecttxt.setText(subject);
			subjecttxt.setEditable(false);
		}
		
		
		
		sendBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String todb=totxt.getText();
				String subdb=subjecttxt.getText();
				String contentdb=contecttxt.getText();
				String fromdb=loginemail;
				
				Email inboxmail=new Email(todb,subdb,contentdb,fromdb);
				
				DbConnection db=new DbConnection();
				try {
					Connection conn=db.getConnection();
					Date d=new Date();//java.util
			 		Timestamp tdate=new Timestamp(d.getTime());//java.sql
					PreparedStatement pr=conn.prepareStatement("insert into inboxtable values(?,?,?,?,?)");
					pr.setString(1,inboxmail.getTo());
					pr.setString(2,inboxmail.getSub());
					pr.setString(3, inboxmail.getContent());
					pr.setString(4, inboxmail.getFrom());
					pr.setTimestamp(5, tdate);
					int i=pr.executeUpdate(); 
					if(i>0) {
						Email sentmail=new Email(fromdb,subdb,contentdb,todb);
						PreparedStatement ps=conn.prepareStatement("insert into senttable values(?,?,?,?,?)");
						ps.setString(1,sentmail.getTo());
						ps.setString(2,sentmail.getSub());
						ps.setString(3,sentmail.getContent());
						ps.setString(4,sentmail.getFrom());
						ps.setTimestamp(5, tdate);
						int j=ps.executeUpdate();
						if(j>0) {
						JOptionPane.showMessageDialog(sendBtn, "Message Send Sucessfully");
						dispose();
						dashboard dash=new dashboard(loginemail);
                        dash.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(sendBtn, "Message Not Send");
						}
					}
					else {
						JOptionPane.showMessageDialog(sendBtn, "Message Not Send");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		
	}
}
