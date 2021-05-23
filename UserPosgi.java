package fanal;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UserPosgi extends JFrame{

	 	//�ֹ�����
		JButton MenuBtn[] = {new JButton("�Ƹ޸�ī��"),new JButton("īǪġ��"),new JButton("ī���ī"),
							new JButton("�ٴҶ��"),new JButton("���̵�"),new JButton("����"),new JButton("������")
							,new JButton("���Ʈ"),new JButton("��������"),new JButton("��Ű")};
		
		int MenuPrice[] = {2500,3000,3000,3000,2500,1500,2000,4000,3500,1000};
		int SelectPrice[] = {0,500,1000};
		
		JRadioButton SelectBtn1[] = {new JRadioButton("Tall"),new JRadioButton("Large"),new JRadioButton("XLarge")};
		JRadioButton SelectBtn2[] = {new JRadioButton("Hot"),new JRadioButton("Ice")};
		ButtonGroup Select1= new ButtonGroup();
		ButtonGroup Select2= new ButtonGroup();
		
		//�ֹ��迭�� ���
		String OrderMenuName = null;
		String OrderMenuSize = null;
		String OrderMenuHorI = null;
		String OrderMenu[] = {null,null,null,null,null}; //�ִ�5��
		String OrderSize[] = {null,null,null,null,null}; //�ִ�5��
		String PriceTx = null;
		
		String OrderList = null;
		
		int PriceTotal = 0; // ���� �� ���ݿ� ���
		int MePrice=0,SiPrice=0; // �ֹ��Ҷ� ������ �޴� ���� ����
		int count=0; // �ֹ���Ͽ� ��ߴ��� Ȯ���ϱ� ���� �ʿ�
		int CashTotal=0,CardTotal=0;
		
		//��Ʈ�κй� �ֹ����ʿ��� ������
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		
	
	UserPosgi()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�Ϲ� ������");
		setBounds(100, 100, 800, 700);
		Container Pos = getContentPane();
		Pos.setLayout(null);
		
		JPanel LogoPanel = new JPanel();
		LogoPanel.setBounds(5, 5, 100, 70);

		ImageIcon Logo = new ImageIcon("smallLogo.png");
		JLabel LogName = new JLabel(Logo);
		LogName.setBounds(0, 0, 60, 50);
		LogoPanel.add(LogName);
		
		//�ֹ���ư �κ� �ǳ�
		JPanel OrderPanel = new JPanel();
		OrderPanel.setBounds(0, 15, 784, 639);
		OrderPanel.setLayout(null);
		
		//�޴��κ� �ǳ�
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(45, 24, 713, 369);
		MenuPanel.setLayout(new FlowLayout(0,30,45));
			
		//�ֹ� �ǳںκ�
		JPanel OrderTxPanel = new JPanel();
		OrderTxPanel.setBounds(35, 399, 713, 38);
		OrderTxPanel.setLayout(null);
		JLabel OrderTx = new JLabel("�ֹ� :");
		OrderTx.setFont(OrderTx.getFont().deriveFont(25.0f));
		OrderTx.setBounds(12, 10, 500, 25);
		JLabel OrderETx = new JLabel("");
		OrderETx.setFont(OrderETx.getFont().deriveFont(18.0f));
		OrderETx.setBounds(80, 10, 500, 20);
		OrderTxPanel.add(OrderTx);
		OrderTxPanel.add(OrderETx);
		
		//���� �ǳںκ�
		JPanel PriceTxPanel = new JPanel();
		PriceTxPanel.setBounds(35, 430, 713, 40);
		PriceTxPanel.setLayout(null);
		JLabel PriceTx = new JLabel("���� :");
		PriceTx.setFont(PriceTx.getFont().deriveFont(25.0f));
		PriceTx.setBounds(12, 20, 500, 20);
		JLabel PriceETx = new JLabel("");
		PriceETx.setBounds(80, 20, 500, 20);
		PriceETx.setFont(PriceETx.getFont().deriveFont(18.0f));
		PriceTxPanel.add(PriceTx);
		PriceTxPanel.add(PriceETx);
		
		//���� �ǳںκ�
		JPanel OrderGTxPanel = new JPanel();
		OrderGTxPanel.setBounds(35, 490, 713, 35);
		
		OrderGTxPanel.setLayout(null);
		JLabel OrderGTx = new JLabel("�ֹ���Ȳ :");
		OrderGTx.setFont(OrderGTx.getFont().deriveFont(25.0f));
		OrderGTx.setBounds(12, 10, 500, 25);
		JLabel OrderGETx = new JLabel("");
		OrderGETx.setBounds(140, 10, 500, 25);
		OrderGETx.setFont(OrderGETx.getFont().deriveFont(15.0f));
		OrderGTxPanel.add(OrderGTx);
		OrderGTxPanel.add(OrderGETx);
		
		//�ֹ���ư �κ�
		JPanel OrderBtnPanel = new JPanel();
		OrderBtnPanel.setBounds(35, 500, 713, 100);
		OrderBtnPanel.setLayout(new FlowLayout(0,30,50));
		JButton PlusBtn = new JButton("�߰�");
		PlusBtn.setPreferredSize(new Dimension(120, 50));
		JButton CashBtn = new JButton("���ݰ��");
		CashBtn.setPreferredSize(new Dimension(120, 50));
		JButton CardBtn = new JButton("ī����");
		CardBtn.setPreferredSize(new Dimension(120, 50));
		JButton EndBtn = new JButton("��������");
		EndBtn.setPreferredSize(new Dimension(120, 50));
		OrderBtnPanel.add(PlusBtn);
		OrderBtnPanel.add(CashBtn);
		OrderBtnPanel.add(CardBtn);
		OrderBtnPanel.add(EndBtn);
		
		//�ֺй�ư �ǳ� ����ֱ�
		OrderPanel.add(MenuPanel);
		OrderPanel.add(OrderTxPanel);
		OrderPanel.add(PriceTxPanel);
		OrderPanel.add(OrderGTxPanel);
		OrderPanel.add(OrderBtnPanel);
		
		// ������Ȳ ��ư �ǳ� �κ�
		JPanel SalesPanel = new JPanel();
		SalesPanel.setBounds(0, 22, 784, 639);
		SalesPanel.setLayout(null);
		
		//�޴���ư �߰� �� �׼�
				for(int i=0;i<MenuBtn.length;i++)
				{	
					int Num=i;
					MenuPanel.add(MenuBtn[i]);
					MenuBtn[i].setPreferredSize(new Dimension(100, 80));
					MenuBtn[i].addActionListener(new ActionListener() {
							
						@Override
						public void actionPerformed(ActionEvent e) {
							OrderMenuName = MenuBtn[Num].getText();
							OrderETx.setText(MenuBtn[Num].getText());
							MePrice = MenuPrice[Num];
							PriceETx.setText(String.valueOf(MePrice));
						}
					});
				}
				
				//������κ�
				JLabel SizeL = new JLabel("Size : ");
				SizeL.setFont(SizeL.getFont().deriveFont(18.0f));
				MenuPanel.add(SizeL);
				
				for(int i=0;i<SelectBtn1.length;i++)
				{
					int Num=i;
					Select1.add(SelectBtn1[i]);
					MenuPanel.add(SelectBtn1[i]);
					SelectBtn1[i].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(SelectBtn1[Num].isSelected())
							{
								OrderMenuSize = SelectBtn1[Num].getText();
								OrderETx.setText(OrderMenuName + " / " + OrderMenuSize);
								SiPrice = SelectPrice[Num];
								PriceETx.setText(String.valueOf(MePrice+SiPrice));
							}
						}
					});
					
				}
				// �� ���̽� �κ�
				JLabel HotIce = new JLabel(" Hot / Ice ");
				HotIce.setFont(HotIce.getFont().deriveFont(18.0f));
				MenuPanel.add(HotIce);
				
				for(int i=0;i<SelectBtn2.length;i++)
				{
					int Num=i;
					Select2.add(SelectBtn2[i]);
					MenuPanel.add(SelectBtn2[i]);
					SelectBtn2[i].addActionListener(new ActionListener() {
						
						
						@Override
						public void actionPerformed(ActionEvent e) {
							{
								if(SelectBtn2[Num].isSelected())
								{
								OrderMenuHorI = SelectBtn2[Num].getText();
								OrderETx.setText(OrderMenuName + " / " + OrderMenuSize + " / "+OrderMenuHorI);
								}
							}
						}
								
						
					});
				}
				
				//�ֹ��߰�
				PlusBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(OrderMenuName.equals("��Ű") ||  OrderMenuName.equals("��������")  )
						{
							
							OrderList = OrderList + " & " +OrderMenuName;
							OrderGETx.setText(OrderList);	
						}
						
						else if(OrderList==null)
						{
							OrderList = OrderMenuName +"/"+ OrderMenuSize +"/"+ OrderMenuHorI;
							OrderGETx.setText(OrderList);
						}
						
						else
						{
							OrderList = OrderList + " & " + OrderMenuName +"/"+ OrderMenuSize +"/"+ OrderMenuHorI;
							OrderGETx.setText(OrderList);	
						}
						OrderMenu[count]= OrderMenuName;
						OrderSize[count++] = OrderMenuSize;
						PriceTotal = PriceTotal + MePrice + SiPrice;
						MePrice=0;
						SiPrice=0;
						Select1.clearSelection();
						Select2.clearSelection();
						OrderETx.setText("");
						PriceETx.setText("");
						System.out.println(PriceTotal);
					}
				});
				//�ֹ�ó�� ���ݰ��
				CashBtn.addActionListener(new ActionListener() {
					DBInfo access = DBInfo.getInstance();
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("�ֹ���Ȳ");
						int result = JOptionPane.showConfirmDialog(null, "��"+String.valueOf(PriceTotal)+"�� �Դϴ�.", "Confirm", 
								JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION)
							;
						else if(result == JOptionPane.YES_OPTION)
						{
							for(int j=0;OrderMenu[j]!=null;j++)
							{
								System.out.println("�޴� : "+OrderMenu[j]+" ������ : "+OrderSize[j]);
								
								try {
									access.InventoryUse(OrderMenu[j],OrderSize[j]);
									} 
								catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									}
							
								OrderMenu[j]=null;
								OrderSize[j]=null;
							
							}
							CashTotal = CashTotal + PriceTotal;
							PriceTotal = 0;
							System.out.println("���� ���� ���� :"+CashTotal);
							OrderList = null;
							OrderGETx.setText("");
							count=0;		
						}
							else
						;
						
						
					}
				});
				
				//�ֹ�ó�� ī����
				CardBtn.addActionListener(new ActionListener() {
					DBInfo access = DBInfo.getInstance();
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("�ֹ���Ȳ");
						int result = JOptionPane.showConfirmDialog(null, "��"+String.valueOf(PriceTotal)+"�� �Դϴ�.", "Confirm", 
								JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION)
							;
						else if(result == JOptionPane.YES_OPTION)
						{
							for(int j=0;OrderMenu[j]!=null;j++)
							{
								System.out.println("�޴� : "+OrderMenu[j]+" ������ : "+OrderSize[j]);
								
								try {
									access.InventoryUse(OrderMenu[j],OrderSize[j]);
									} 
								catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									}

								
								OrderMenu[j]=null;
								OrderSize[j]=null;
								
							}
							int soosoo = PriceTotal/5;
							CardTotal = CardTotal + (PriceTotal-soosoo);
							PriceTotal = 0;
							System.out.println("���� ī����� : " + CardTotal);
							OrderList = null;
							
							OrderGETx.setText("");
							count=0;		
						}
							else
						;
						
					}
				});
				
				// �������� ���������� �Ǹ���Ȳ �����ͷ� insert
				EndBtn.addActionListener(new ActionListener() {
					DBInfo info = DBInfo.getInstance();
					@Override
					public void actionPerformed(ActionEvent e) {
						
						System.out.println("����ó�� ��");
						int result = JOptionPane.showConfirmDialog(null, "��"+String.valueOf(CardTotal+CashTotal)+"�� �Ǹ��ϼ̽��ϴ�.", "Confirm", 
								JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION)
							;
						else if(result == JOptionPane.YES_OPTION)
						{
							try {
								info.EndAccess(year, month, day, CashTotal+CardTotal);
								CashTotal=0;
								CardTotal=0;
								System.out.println("�����ϼ̽��ϴ�.");
								
								info.Daychart(year, month, day); // ��Ʈ�� ���� �ݿ�����
								info.Monthchart(year, month);
								info.Yearchart(year);
							} 
							catch (Exception e1) {
								e1.printStackTrace();
					            Excp ex = new Excp();
					            ex.data_error();
							}
							
						}
						
					}
				});
				Pos.add(LogoPanel);
				Pos.add(OrderPanel);
				setVisible(true);
				
				
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserPosgi();
	}

}
