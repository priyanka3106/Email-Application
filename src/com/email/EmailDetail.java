package com.email;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.database.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmailDetail extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String to = null,subject = null,content = null,emaildate = null;
					String loginemail = null;
					String tableheader = null;
					EmailDetail frame = new EmailDetail(to,subject,content,emaildate,loginemail,tableheader);
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
	public EmailDetail(String to,String subject,String content,String emaildate,String loginemail,String tableheader) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel subjectLabel = new JLabel("");
		subjectLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		subjectLabel.setBounds(41, 35, 591, 74);
		contentPane.add(subjectLabel);
		subjectLabel.setText(subject);
		
		JLabel tolabel = new JLabel("<dynamic>");
		tolabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tolabel.setBounds(41, 144, 296, 74);
		contentPane.add(tolabel);
		tolabel.setText(to);
		
		JLabel contentlabel = new JLabel("<dynamic>");
		contentlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentlabel.setBounds(41, 258, 591, 298);
		contentPane.add(contentlabel);
		contentlabel.setText(content);
		
		JLabel datelabel = new JLabel("<dynamic>");
		datelabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		datelabel.setBounds(41, 203, 296, 74);
		contentPane.add(datelabel);
		datelabel.setText(emaildate);
		
		JButton btnReplay = new JButton("Reply");
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ComposeEmail ce=new ComposeEmail(to,subject,loginemail);
				ce.setVisible(true);
			}
		});
		btnReplay.setForeground(Color.WHITE);
		btnReplay.setBackground(new Color(30, 144, 255));
		btnReplay.setBounds(378, 601, 124, 45);
		contentPane.add(btnReplay);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				DbConnection db=new DbConnection();
				Connection conn;
				try {
					conn = db.getConnection();
					PreparedStatement pr = null;
					if(tableheader.equals("From")) {
						pr=conn.prepareStatement("delete from inboxtable where subject= ? and contents=?");
					}else if((tableheader.equals("To"))) {
					  pr=conn.prepareStatement("delete from senttable where subject= ? and contents=?");	
					}
					pr.setString(1, subject);
					pr.setString(2, content);
					int i=pr.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(btnDelete, "Deleted Successfully !!!");
						dispose();
						dashboard ds=new dashboard(loginemail);
						ds.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(btnDelete, "Not Deleted !!!");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(538, 601, 124, 45);
		contentPane.add(btnDelete);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				 dashboard dash=new dashboard(loginemail);
                 dash.setVisible(true);
                 
                 
			}
		});
		btnNewButton.setBounds(10, 10, 85, 21);
		contentPane.add(btnNewButton);
	}
}
