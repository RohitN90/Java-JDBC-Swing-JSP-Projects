import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usNam;
	private JTextField passwor1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("Love Bytes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/Images/Yellow and Black Fun Modern Restaurant Food Logo (1).png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 74, 149, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 132, 149, 24);
		contentPane.add(lblNewLabel_1);
		
		usNam = new JTextField();
		usNam.setHorizontalAlignment(SwingConstants.CENTER);
		usNam.setFont(new Font("Segoe UI", Font.BOLD, 15));
		usNam.setBounds(163, 70, 209, 33);
		contentPane.add(usNam);
		usNam.setColumns(10);
		
		passwor1 = new JTextField();
		passwor1.setHorizontalAlignment(SwingConstants.CENTER);
		passwor1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		passwor1.setColumns(10);
		passwor1.setBounds(163, 127, 209, 35);
		contentPane.add(passwor1);
		
		JButton btnNewButton = new JButton("SignUp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String us = usNam.getText();
//				String ps1 = passwor1.getText();
//				String ps2 = passwor2.getText();
					
					try {
						
						Connection con = MyClass.letConnection();
						
						String que = "insert into logindetails values(?,?)";
						PreparedStatement ps = con.prepareStatement(que);
						ps.setString(1,us);
						ps.setString(2, passwor1.getText().toString());
						
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Your Registered :) ");
						ps.close();
						con.close();
						
						
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(194, 192, 119, 24);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Get Started by SignUp");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(163, 10, 193, 33);
		contentPane.add(lblNewLabel_3);
	}
}
