package fanal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Font;

public class JoinFrame extends JFrame {
	private JTextField textField;

	JoinFrame() {
		setTitle("ȸ������ ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container Join = getContentPane();
		JLabel JLJoin = new JLabel("ȸ������");
		JLJoin.setFont(new Font("HY�߰��", Font.BOLD, 20));
		JLJoin.setBounds(130, 10, 91, 29);
		getContentPane().setLayout(null);
		
		JPanel form = new JPanel();
		form.setBounds(12, 49, 312, 213);
		JLabel LaId = new JLabel(" I D : ");
		LaId.setBounds(27, 8, 31, 15);
		JTextField TxId = new JTextField("",20);
		TxId.setBounds(87, 5, 213, 21);
		JLabel LaPassword = new JLabel(" P W : ");
		LaPassword.setBounds(20, 63, 38, 15);
		JPasswordField TxPassword = new JPasswordField("",20);
		TxPassword.setBounds(87, 64, 213, 21);
		JLabel LaName = new JLabel(" Name : ");
		LaName.setBounds(10, 120, 57, 15);
		JTextField TxName = new JTextField("",15);
		TxName.setBounds(87, 117, 213, 21);
		JLabel LaEmail = new JLabel(" Email : ");
		LaEmail.setBounds(10, 172, 48, 15);
		JTextField TxEmail = new JTextField("",20);
		TxEmail.setBounds(87, 169, 213, 21);
		form.setLayout(null);
		form.add(LaId);
		form.add(TxId);
		form.add(LaPassword);
		form.add(TxPassword);
		form.add(LaName);
		form.add(TxName);
		form.add(LaEmail);
		form.add(TxEmail);

		
		
		
		JButton IdCheck = new JButton("�ߺ�Ȯ��");
		IdCheck.setBounds(87, 30, 97, 23);
		form.add(IdCheck);
		
		IdCheck.addActionListener(new ActionListener() {
			DBInfo info = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = TxId.getText();
				try {
					int result = info.IdCheck(id);
					if(result==0)
					{
						JOptionPane.showMessageDialog(null, "����Ҽ� ���� ���̵��Դϴ�.", "���Ұ�", JOptionPane.ERROR_MESSAGE);
						TxId.setText("");
					}
					else if(result==1)
						
					{
						JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
					}
				} 
				catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		

		
		
		
		
		JButton JoinBtn = new JButton("�����ϱ�");
		JoinBtn.setBounds(22, 276, 284, 23);
		JoinBtn.addActionListener(new ActionListener() {
			DBInfo Join = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String id = TxId.getText();
					String pw = TxPassword.getText();
					String name = TxName.getText();
					String email = TxEmail.getText();
					Join.JoinAccess(id, pw, name, email);
					
					JOptionPane.showMessageDialog(null, "���� �����մϴ�.");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Join.add(JLJoin);
		Join.add(form);
		Join.add(JoinBtn);
		setSize(352, 367);
		setVisible(true);
	}
}
