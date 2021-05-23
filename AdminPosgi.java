package fanal;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;

public class AdminPosgi extends JFrame {
	
	//�����Ȳ 
	String ctitle[] = {"�ڵ�","ǰ��","���","��û����"};
    JTable table;
    JScrollPane scroll;
    Dialog dialog;
    String cdata[][]=null;
    DefaultTableModel model = new DefaultTableModel(ctitle, 0);
    
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
	String OrderMenu[] = {null,null,null,null,null};
	String OrderSize[] = {null,null,null,null,null};
	String PriceTx = null;
	
	String OrderList = null;
	
	int PriceTotal = 0; // ���� �� ���ݿ� ���
	int PriceTotal2 =0;
	int MePrice=0,SiPrice=0; // �ֹ��Ҷ� ������ �޴� ���� ����
	int count=0; // �ֹ���Ͽ� ��ߴ��� Ȯ���ϱ� ���� �ʿ�
	int CashTotal=0,CardTotal=0;
	
	AdminPosgi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������ ������");
		setBounds(100, 100, 800, 700);
		Container Pos = getContentPane();
		Pos.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 21);
		
		//�޴��� ��ư�κ�
		JButton OrderBtn = new JButton("�ֹ�");
		JButton SalseBtn = new JButton("�Ǹ���Ȳ");
		JButton InventoryBtn = new JButton("�����Ȳ");
		menuBar.add(OrderBtn);
		menuBar.add(SalseBtn);
		menuBar.add(InventoryBtn);
		
		//�ֹ���ư �κ� �ǳ�
		JPanel OrderPanel = new JPanel();
		OrderPanel.setBounds(0, 22, 784, 639);
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
		OrderBtnPanel.setBounds(35, 500, 803, 100);
		OrderBtnPanel.setLayout(new FlowLayout(0,30,50));
		JButton PlusBtn = new JButton("�߰�");
		PlusBtn.setPreferredSize(new Dimension(120, 50));
		JButton CashBtn = new JButton("���ݰ��");
		CashBtn.setPreferredSize(new Dimension(120, 50));
		JButton CardBtn = new JButton("ī����");
		CardBtn.setPreferredSize(new Dimension(120, 50));
		JButton EndBtn = new JButton("��������");
		EndBtn.setPreferredSize(new Dimension(120, 50));
		JButton txtBtn = new JButton("��ǥ����");
		txtBtn.setPreferredSize(new Dimension(90, 50));
		
		OrderBtnPanel.add(PlusBtn);
		OrderBtnPanel.add(CashBtn);
		OrderBtnPanel.add(CardBtn);
		OrderBtnPanel.add(EndBtn);
		OrderBtnPanel.add(txtBtn);
		
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
		
		// �����ư �ǳ� �κ�
		JPanel SalesBtnPanel = new JPanel();
		SalesBtnPanel.setBounds(28, 34, 718, 42);
		SalesBtnPanel.setLayout(new FlowLayout());
		JButton TodaySales = new JButton("�� ����");
		JButton MonthSales = new JButton("�� ����");
		JButton YearSales = new JButton("�� ����");
		SalesBtnPanel.add(TodaySales);
		SalesBtnPanel.add(MonthSales);
		SalesBtnPanel.add(YearSales);
		
		//��Ʈ�κп� �ʿ��� ������ ����
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		
		//������ ��Ʈ �κ�
		JPanel MonthChartPanel = new JPanel();
		MonthChartPanel.setBounds(28, 114, 718, 500);
		Chart demo = new Chart();
		JFreeChart Monthchart = demo.MonthgetChart(year);
		org.jfree.chart.ChartPanel Monthchpan = new org.jfree.chart.ChartPanel(Monthchart);
		MonthChartPanel.add(Monthchpan);
		MonthChartPanel.setVisible(false);
		
		//�ϸ��� ��Ʈ�κ�
		JPanel DayChartPanel = new JPanel();
		DayChartPanel.setBounds(28, 114, 718, 500);
		Chart demo2 = new Chart();
		JFreeChart Daychart = demo2.DayChart(year,month,day);
		org.jfree.chart.ChartPanel Daychpan = new org.jfree.chart.ChartPanel(Daychart);
		DayChartPanel.add(Daychpan);
		DayChartPanel.setVisible(false);
		
		// ������ ��Ʈ�κ�
		JPanel YearChartPanel = new JPanel();
		YearChartPanel.setBounds(28, 114, 718, 500);
		Chart demo3 = new Chart();
		JFreeChart Yearchart = demo3.YearChart(year);
		org.jfree.chart.ChartPanel Yearchpan = new org.jfree.chart.ChartPanel(Yearchart);
		YearChartPanel.add(Yearchpan);
		YearChartPanel.setVisible(false);
		
		// �������� ��ư
		MonthSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DayChartPanel.setVisible(false);
				YearChartPanel.setVisible(false);
				MonthChartPanel.setVisible(true);
			}
		});
		
		// �Ϻ����� ��ư
		TodaySales.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DayChartPanel.removeAll();
				JFreeChart Daychart = demo2.DayChart(year,month,day);
				org.jfree.chart.ChartPanel Daychpan = new org.jfree.chart.ChartPanel(Daychart);
				DayChartPanel.add(Daychpan);
				
				MonthChartPanel.setVisible(false);
				YearChartPanel.setVisible(false);
				DayChartPanel.setVisible(true);
			}
		});
		
		// ������
		YearSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MonthChartPanel.setVisible(false);
				YearChartPanel.setVisible(true);
				DayChartPanel.setVisible(false);	
			}
		});
		
		// ������Ȳ �ǳڿ� �ٶ����ֱ�
		SalesPanel.add(SalesBtnPanel);
		SalesPanel.add(MonthChartPanel);
		SalesPanel.add(DayChartPanel);
		SalesPanel.add(YearChartPanel);
		SalesPanel.setVisible(false);
		
		//�����Ȳ��ư �ǳںκ�
		JPanel InventoryPanel = new JPanel();
		InventoryPanel.setBounds(0, 22, 784, 639);
		InventoryPanel.setLayout(null);
		InventoryPanel.setVisible(false);
		
		JPanel InvenTable = new JPanel();
		InvenTable.setBounds(38, 29, 600, 500);
		
        JPanel main = new JPanel();
        main.setBounds(0, 15, 702, 500);
        main.setLayout(new FlowLayout());
		
        JTable Btable = new JTable(model);
        JScrollPane scroll = new JScrollPane(Btable);
        
        Btable.getColumnModel().getColumn(0).setPreferredWidth(5);
        Btable.getColumnModel().getColumn(1).setPreferredWidth(30);
        Btable.getColumnModel().getColumn(2).setPreferredWidth(30);
        Btable.getColumnModel().getColumn(3).setPreferredWidth(30);
        
        main.add(scroll);
        
        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        JButton sub = new JButton("��û");
		InvenTable.setLayout(null);
		
		
		
		
		JLabel label = new JLabel("���ֽý���");
		label.setBounds(320, 0, 100, 15);
		
		
		JButton balju = new JButton("���ֽ�û");
		balju.setPreferredSize(new Dimension(100, 40));
		balju.setBounds(270, 550, 100, 40);
		JButton confirm = new JButton("����Ȯ��");
		confirm.setPreferredSize(new Dimension(100, 40));
		confirm.setBounds(390, 550, 100, 40);
		
		InvenTable.add(label);
		InvenTable.add(main);
		//���ֽ�û
		balju.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog dialog=new Dialog();
				dialog.setVisible(true);
	
	
			}
		});
		//����Ȯ��
		confirm.addActionListener(new ActionListener() {
			DBInfo info = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				info.Confirm();
				model.setNumRows(0);
				info.print(model);
			}
		});
		
		
		InventoryPanel.add(InvenTable);
		InventoryPanel.add(balju);
		InventoryPanel.add(confirm);
		
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
				PriceTotal2 = CashTotal+CardTotal;
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
		txtBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Print p = new Print();
				p.print();
				
			}
		});
		
		//�ֹ� ��ư�������� ��������
		OrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SalesPanel.setVisible(false);
				InventoryPanel.setVisible(false);
				OrderPanel.setVisible(true);
				model.setNumRows(0);
			}
		});
		
		//������Ȳ��ư �׼�
		SalseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				InventoryPanel.setVisible(false);
				SalesPanel.setVisible(true);
				model.setNumRows(0);
			}
		});
		
		//�����Ȳ ��ư
		InventoryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OrderPanel.setVisible(false);
				SalesPanel.setVisible(false);
				InventoryPanel.setVisible(true);
				DBInfo info = DBInfo.getInstance();
				info.print(model);
				
			}
		});
		
		Pos.add(menuBar);
		Pos.add(OrderPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(-66, -60, 210, 150);
		OrderPanel.add(panel);
		
		JLabel label_1 = new JLabel((Icon) null);
		panel.add(label_1);
		Pos.add(SalesPanel);
		Pos.add(InventoryPanel);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminPosgi();
	}

class Print implements Func{				//���������
	public void print() {
		try {
			
			SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy�� MM��dd�� HH��mm��ss��   : ");
			String format_time2 = format2.format (System.currentTimeMillis());
			

		    OutputStream output = new FileOutputStream("D:/Output.txt",true);
		    
		    byte[] b = format_time2.getBytes();
		    output.write(b);
		    
		    String t = Integer.toString(PriceTotal2);
		    byte[] by=t.getBytes();
		    output.write(by);
		    
		    String newl = "\n";
		    byte[] l = newl.getBytes();
		    output.write(l);
		   
		    output.close();
				
		} catch (Exception e) {
	            e.getStackTrace();
	            Excp ex = new Excp();
	            ex.file_error();
		}
	}
}

class Dialog extends JDialog{
	   String item[]= {"Ŀ��","����","����ũ��","�÷�","�Ŀ��","���ںи�","��Ű","����ũ","���Ʈ","����","ź���"};
	
	  
	   private JComboBox<String> namecombo = new JComboBox<String>();
	   private JTextField count = new JTextField(12);
	   private JButton ok = new JButton("Ȯ��");
	    
	   Dialog(){
		      
		   for(int i=0;i<item.length;i++)
			   namecombo.addItem(item[i]);
		   
		   setTitle("���ֽ�û");
		   setLayout(new FlowLayout());
		   add(new JLabel("ǰ��"));
		   add(namecombo);
		   add(new JLabel("����"));
		   add(count);
		   add(ok);
		   
		   
		   ok.addActionListener(new ActionListener() {
			DBInfo info = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = namecombo.getSelectedItem().toString();
				int countInfo = Integer.parseInt(count.getText());
				info.Balju(name, countInfo);
				model.setNumRows(0);
				info.print(model);
				setVisible(false);
			}
		});
		   setSize(580, 100);
	   }
	}
}