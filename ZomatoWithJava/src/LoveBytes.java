import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoveBytes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userText;
	private JTextField passWords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoveBytes frame = new LoveBytes();
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
	public LoveBytes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoveBytes.class.getResource("/Images/Yellow and Black Fun Modern Restaurant Food Logo (1).png")));
		setTitle("Love Bytes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 238, 200));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 388, 463);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Back");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 118, 388, 74);
		panel.add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTo.setBounds(0, 175, 388, 49);
		panel.add(lblTo);
		
		JLabel lblForstedBits = new JLabel("Love Bytes");
		lblForstedBits.setHorizontalAlignment(SwingConstants.CENTER);
		lblForstedBits.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblForstedBits.setBounds(0, 202, 388, 65);
		panel.add(lblForstedBits);
		
		JLabel lblNewLabel_1 = new JLabel("Order Your Favorate Food and Enjoy !");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 277, 388, 25);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(387, 0, 529, 463);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel userName = new JLabel("UserName :");
		userName.setFont(new Font("SansSerif", Font.BOLD, 20));
		userName.setBounds(23, 67, 123, 46);
		panel_1.add(userName);
		
		userText = new JTextField();
		userText.setToolTipText("Enter Email or Username");
		userText.setFont(new Font("Arial", Font.PLAIN, 20));
		userText.setBounds(23, 123, 481, 46);
		panel_1.add(userText);
		userText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblNewLabel_3.setBounds(206, 10, 123, 62);
		panel_1.add(lblNewLabel_3);
		
		JLabel passWord = new JLabel("Password :");
		passWord.setFont(new Font("SansSerif", Font.BOLD, 20));
		passWord.setBounds(23, 179, 123, 46);
		panel_1.add(passWord);
		
		JButton loginBut = new JButton("Login");
		
		loginBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection conn = MyClass.letConnection();
					
					String sql = "SELECT * FROM loginDetails WHERE username=? AND password=?";
					
					PreparedStatement pls = conn.prepareStatement(sql);
					pls.setString(1,userText.getText());
					pls.setString(2,passWords.getText());
					
					ResultSet rs = pls.executeQuery();
					
					if(rs.next()) {
						
						MainPage mp = new MainPage();
						mp.setVisible(true);
						dispose();
						
						
					}else {
						JOptionPane.showMessageDialog(loginBut, "username of password is wrong....");
						userText.setText(" ");
						passWords.setText(" ");
					}
					conn.close();
					
					
					
				} catch (SQLException e2) {
					System.out.println(e2);
				}
			}
		});
		loginBut.setForeground(new Color(85, 88, 67));
		loginBut.setBackground(new Color(167, 211, 151));
		loginBut.setFont(new Font("Segoe UI", Font.BOLD, 20));
		loginBut.setBounds(206, 313, 123, 62);
		panel_1.add(loginBut);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have a Account click here to SignUp");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SignUp up = new SignUp();
				up.setVisible(true);
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(23, 402, 481, 28);
		panel_1.add(lblNewLabel_2);
		
		passWords = new JTextField();
		passWords.setToolTipText("Enter Email or Username");
		passWords.setFont(new Font("Arial", Font.PLAIN, 20));
		passWords.setColumns(10);
		passWords.setBounds(23, 241, 481, 46);
		panel_1.add(passWords);
	}
}
