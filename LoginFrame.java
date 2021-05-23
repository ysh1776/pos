package fanal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	LoginFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cafe P.O.S Login!");
		Container Log = getContentPane();
		Log.setLayout(null);

		
		
		JPanel LogoPanel = new JPanel();
		LogoPanel.setBounds(140, 26, 210, 150);

		ImageIcon Logo = new ImageIcon("Logo.png");
		JLabel LogName = new JLabel(Logo);
		LogName.setBounds(0, 0, 100, 100);
		LogoPanel.add(LogName);
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBounds(93, 200, 300, 48);
		JLabel LogId = new JLabel("ID :         ");
		LogId.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		LogId.setBackground(SystemColor.window);
		JTextField TxId = new JTextField("",20);
		TxId.setColumns(10);
		
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setBounds(80, 260, 323, 35);
		JLabel LogPass = new JLabel("PassWord :");
		LogPass.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		LogPass.setBackground(SystemColor.window);
		JPasswordField TxPassword = new JPasswordField("",15);
		TxPassword.setColumns(10);
		LoginPanel.add(LogId);
		LoginPanel.add(TxId);
		PasswordPanel.add(LogPass);
		PasswordPanel.add(TxPassword);
		
		
		JPanel SubmitPanel = new JPanel();
		SubmitPanel.setBounds(170, 320, 188, 38);
		JButton LoginBtn = new JButton("Login");
		JButton JoinBtn = new JButton("Join");
		SubmitPanel.add(LoginBtn);
		SubmitPanel.add(JoinBtn);
		
		JPanel LogError = new JPanel();
		LogError.setBounds(137, 292, 206, 25);
		JLabel ErrorLabel = new JLabel("¾ÆÀÌµð ¶Ç´Â ºñ¹Ð¹øÈ£ ¿À·ùÀÔ´Ï´Ù.");
		ErrorLabel.setFont(new Font("±¼¸²", Font.BOLD, 12));
		ErrorLabel.setForeground(Color.RED);
		LogError.add(ErrorLabel);
		LogError.setVisible(false);
		JoinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JoinFrame Join = new JoinFrame();
			}
			
		});
		LoginBtn.addActionListener(new ActionListener() {
			DBInfo Login = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					String id = TxId.getText();
					String pw = TxPassword.getText();
					int result = Login.LoginAccess(id, pw);
					if (result ==1)
					{
						AdminPosgi pos = new AdminPosgi();
						LogError.setVisible(false);
						
					}
					else if(result == 2)
					{
						UserPosgi Userpos = new UserPosgi();
						LogError.setVisible(false);
					}
					else
					{
						LogError.setVisible(true);
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		Log.add(LogoPanel);
		Log.add(LoginPanel);
		Log.add(PasswordPanel);
		Log.add(SubmitPanel);
		Log.add(LogError);
		setSize(500,500);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame();
	}

}
