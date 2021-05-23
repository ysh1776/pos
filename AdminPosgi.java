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
	
	//재고현황 
	String ctitle[] = {"코드","품명","재고","신청수량"};
    JTable table;
    JScrollPane scroll;
    Dialog dialog;
    String cdata[][]=null;
    DefaultTableModel model = new DefaultTableModel(ctitle, 0);
    
    //주문영역
	JButton MenuBtn[] = {new JButton("아메리카노"),new JButton("카푸치노"),new JButton("카페모카"),
						new JButton("바닐라라떼"),new JButton("에이드"),new JButton("우유"),new JButton("핫초코")
						,new JButton("요거트"),new JButton("조각케익"),new JButton("쿠키")};
	
	int MenuPrice[] = {2500,3000,3000,3000,2500,1500,2000,4000,3500,1000};
	int SelectPrice[] = {0,500,1000};
	
	JRadioButton SelectBtn1[] = {new JRadioButton("Tall"),new JRadioButton("Large"),new JRadioButton("XLarge")};
	JRadioButton SelectBtn2[] = {new JRadioButton("Hot"),new JRadioButton("Ice")};
	ButtonGroup Select1= new ButtonGroup();
	ButtonGroup Select2= new ButtonGroup();
	
	//주문배열에 사용
	String OrderMenuName = null;
	String OrderMenuSize = null;
	String OrderMenuHorI = null;
	String OrderMenu[] = {null,null,null,null,null};
	String OrderSize[] = {null,null,null,null,null};
	String PriceTx = null;
	
	String OrderList = null;
	
	int PriceTotal = 0; // 계산시 총 가격에 사용
	int PriceTotal2 =0;
	int MePrice=0,SiPrice=0; // 주문할때 선택한 메뉴 가격 저장
	int count=0; // 주문목록에 몇개했는지 확인하기 위해 필요
	int CashTotal=0,CardTotal=0;
	
	AdminPosgi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("관리자 포스기");
		setBounds(100, 100, 800, 700);
		Container Pos = getContentPane();
		Pos.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 21);
		
		//메뉴바 버튼부분
		JButton OrderBtn = new JButton("주문");
		JButton SalseBtn = new JButton("판매현황");
		JButton InventoryBtn = new JButton("재고현황");
		menuBar.add(OrderBtn);
		menuBar.add(SalseBtn);
		menuBar.add(InventoryBtn);
		
		//주문버튼 부분 판넬
		JPanel OrderPanel = new JPanel();
		OrderPanel.setBounds(0, 22, 784, 639);
		OrderPanel.setLayout(null);
		
		//메뉴부분 판넬
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(45, 24, 713, 369);
		MenuPanel.setLayout(new FlowLayout(0,30,45));
			
		//주문 판넬부분
		JPanel OrderTxPanel = new JPanel();
		OrderTxPanel.setBounds(35, 399, 713, 38);
		OrderTxPanel.setLayout(null);
		JLabel OrderTx = new JLabel("주문 :");
		OrderTx.setFont(OrderTx.getFont().deriveFont(25.0f));
		OrderTx.setBounds(12, 10, 500, 25);
		JLabel OrderETx = new JLabel("");
		OrderETx.setFont(OrderETx.getFont().deriveFont(18.0f));
		OrderETx.setBounds(80, 10, 500, 20);
		OrderTxPanel.add(OrderTx);
		OrderTxPanel.add(OrderETx);
		
		//가격 판넬부분
		JPanel PriceTxPanel = new JPanel();
		PriceTxPanel.setBounds(35, 430, 713, 40);
		PriceTxPanel.setLayout(null);
		JLabel PriceTx = new JLabel("가격 :");
		PriceTx.setFont(PriceTx.getFont().deriveFont(25.0f));
		PriceTx.setBounds(12, 20, 500, 20);
		JLabel PriceETx = new JLabel("");
		PriceETx.setBounds(80, 20, 500, 20);
		PriceETx.setFont(PriceETx.getFont().deriveFont(18.0f));
		PriceTxPanel.add(PriceTx);
		PriceTxPanel.add(PriceETx);
		
		//가격 판넬부분
		JPanel OrderGTxPanel = new JPanel();
		OrderGTxPanel.setBounds(35, 490, 713, 35);
		
		OrderGTxPanel.setLayout(null);
		JLabel OrderGTx = new JLabel("주문현황 :");
		OrderGTx.setFont(OrderGTx.getFont().deriveFont(25.0f));
		OrderGTx.setBounds(12, 10, 500, 25);
		JLabel OrderGETx = new JLabel("");
		OrderGETx.setBounds(140, 10, 500, 25);
		OrderGETx.setFont(OrderGETx.getFont().deriveFont(15.0f));
		OrderGTxPanel.add(OrderGTx);
		OrderGTxPanel.add(OrderGETx);
		
		//주문버튼 부분
		JPanel OrderBtnPanel = new JPanel();
		OrderBtnPanel.setBounds(35, 500, 803, 100);
		OrderBtnPanel.setLayout(new FlowLayout(0,30,50));
		JButton PlusBtn = new JButton("추가");
		PlusBtn.setPreferredSize(new Dimension(120, 50));
		JButton CashBtn = new JButton("현금계산");
		CashBtn.setPreferredSize(new Dimension(120, 50));
		JButton CardBtn = new JButton("카드계산");
		CardBtn.setPreferredSize(new Dimension(120, 50));
		JButton EndBtn = new JButton("영업종료");
		EndBtn.setPreferredSize(new Dimension(120, 50));
		JButton txtBtn = new JButton("전표저장");
		txtBtn.setPreferredSize(new Dimension(90, 50));
		
		OrderBtnPanel.add(PlusBtn);
		OrderBtnPanel.add(CashBtn);
		OrderBtnPanel.add(CardBtn);
		OrderBtnPanel.add(EndBtn);
		OrderBtnPanel.add(txtBtn);
		
		//주분버튼 판넬 집어넣기
		OrderPanel.add(MenuPanel);
		OrderPanel.add(OrderTxPanel);
		OrderPanel.add(PriceTxPanel);
		OrderPanel.add(OrderGTxPanel);
		OrderPanel.add(OrderBtnPanel);
		
		// 매출현황 버튼 판넬 부분
		JPanel SalesPanel = new JPanel();
		SalesPanel.setBounds(0, 22, 784, 639);
		SalesPanel.setLayout(null);
		
		// 매출버튼 판넬 부분
		JPanel SalesBtnPanel = new JPanel();
		SalesBtnPanel.setBounds(28, 34, 718, 42);
		SalesBtnPanel.setLayout(new FlowLayout());
		JButton TodaySales = new JButton("일 매출");
		JButton MonthSales = new JButton("월 매출");
		JButton YearSales = new JButton("연 매출");
		SalesBtnPanel.add(TodaySales);
		SalesBtnPanel.add(MonthSales);
		SalesBtnPanel.add(YearSales);
		
		//차트부분에 필요한 데이터 정의
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		
		//월매출 차트 부분
		JPanel MonthChartPanel = new JPanel();
		MonthChartPanel.setBounds(28, 114, 718, 500);
		Chart demo = new Chart();
		JFreeChart Monthchart = demo.MonthgetChart(year);
		org.jfree.chart.ChartPanel Monthchpan = new org.jfree.chart.ChartPanel(Monthchart);
		MonthChartPanel.add(Monthchpan);
		MonthChartPanel.setVisible(false);
		
		//일매출 차트부분
		JPanel DayChartPanel = new JPanel();
		DayChartPanel.setBounds(28, 114, 718, 500);
		Chart demo2 = new Chart();
		JFreeChart Daychart = demo2.DayChart(year,month,day);
		org.jfree.chart.ChartPanel Daychpan = new org.jfree.chart.ChartPanel(Daychart);
		DayChartPanel.add(Daychpan);
		DayChartPanel.setVisible(false);
		
		// 연매출 차트부분
		JPanel YearChartPanel = new JPanel();
		YearChartPanel.setBounds(28, 114, 718, 500);
		Chart demo3 = new Chart();
		JFreeChart Yearchart = demo3.YearChart(year);
		org.jfree.chart.ChartPanel Yearchpan = new org.jfree.chart.ChartPanel(Yearchart);
		YearChartPanel.add(Yearchpan);
		YearChartPanel.setVisible(false);
		
		// 월별매출 버튼
		MonthSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DayChartPanel.setVisible(false);
				YearChartPanel.setVisible(false);
				MonthChartPanel.setVisible(true);
			}
		});
		
		// 일별매출 버튼
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
		
		// 연매출
		YearSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MonthChartPanel.setVisible(false);
				YearChartPanel.setVisible(true);
				DayChartPanel.setVisible(false);	
			}
		});
		
		// 매출현황 판넬에 다때려넣기
		SalesPanel.add(SalesBtnPanel);
		SalesPanel.add(MonthChartPanel);
		SalesPanel.add(DayChartPanel);
		SalesPanel.add(YearChartPanel);
		SalesPanel.setVisible(false);
		
		//재고현황버튼 판넬부분
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
        JButton sub = new JButton("신청");
		InvenTable.setLayout(null);
		
		
		
		
		JLabel label = new JLabel("발주시스템");
		label.setBounds(320, 0, 100, 15);
		
		
		JButton balju = new JButton("발주신청");
		balju.setPreferredSize(new Dimension(100, 40));
		balju.setBounds(270, 550, 100, 40);
		JButton confirm = new JButton("수령확인");
		confirm.setPreferredSize(new Dimension(100, 40));
		confirm.setBounds(390, 550, 100, 40);
		
		InvenTable.add(label);
		InvenTable.add(main);
		//발주신청
		balju.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog dialog=new Dialog();
				dialog.setVisible(true);
	
	
			}
		});
		//수령확인
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
		
		//메뉴버튼 추가 및 액션
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
		
		//사이즈부분
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
		// 핫 아이스 부분
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
		
		//주문추가
		PlusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(OrderMenuName.equals("쿠키") ||  OrderMenuName.equals("조각케익")  )
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
		//주문처리 현금계산
		CashBtn.addActionListener(new ActionListener() {
			DBInfo access = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("주문현황");
				int result = JOptionPane.showConfirmDialog(null, "총"+String.valueOf(PriceTotal)+"원 입니다.", "Confirm", 
						JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION)
					;
				else if(result == JOptionPane.YES_OPTION)
				{
					for(int j=0;OrderMenu[j]!=null;j++)
					{
						System.out.println("메뉴 : "+OrderMenu[j]+" 사이즈 : "+OrderSize[j]);
						
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
					System.out.println("현재 현금 매출 :"+CashTotal);
					OrderList = null;
					OrderGETx.setText("");
					count=0;		
				}
					else
				;
				
				
			}
		});
		
		//주문처리 카드계산
		CardBtn.addActionListener(new ActionListener() {
			DBInfo access = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("주문현황");
				int result = JOptionPane.showConfirmDialog(null, "총"+String.valueOf(PriceTotal)+"원 입니다.", "Confirm", 
						JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION)
					;
				else if(result == JOptionPane.YES_OPTION)
				{
					for(int j=0;OrderMenu[j]!=null;j++)
					{
						System.out.println("메뉴 : "+OrderMenu[j]+" 사이즈 : "+OrderSize[j]);
						
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
					System.out.println("현재 카드매출 : " + CardTotal);
					OrderList = null;
					
					OrderGETx.setText("");
					count=0;		
				}
					else
				;
				
			}
		});
		// 영업종료 매출정보가 판매현황 데이터로 insert
		EndBtn.addActionListener(new ActionListener() {
			DBInfo info = DBInfo.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				PriceTotal2 = CashTotal+CardTotal;
				System.out.println("마감처리 중");
				int result = JOptionPane.showConfirmDialog(null, "총"+String.valueOf(CardTotal+CashTotal)+"원 판매하셨습니다.", "Confirm", 
						JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION)
					;
				else if(result == JOptionPane.YES_OPTION)
				{
					try {
						info.EndAccess(year, month, day, CashTotal+CardTotal);
						CashTotal=0;
						CardTotal=0;
						System.out.println("수고하셨습니다.");
						
						info.Daychart(year, month, day); // 차트를 새로 반영해줌
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
		
		//주문 버튼을누르면 나오게함
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
		
		//매출현황버튼 액션
		SalseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPanel.setVisible(false);
				InventoryPanel.setVisible(false);
				SalesPanel.setVisible(true);
				model.setNumRows(0);
			}
		});
		
		//재고현황 버튼
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

class Print implements Func{				//파일입출력
	public void print() {
		try {
			
			SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초   : ");
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
	   String item[]= {"커피","우유","휘핑크림","시럽","파우더","초코분말","쿠키","케이크","요거트","과일","탄산수"};
	
	  
	   private JComboBox<String> namecombo = new JComboBox<String>();
	   private JTextField count = new JTextField(12);
	   private JButton ok = new JButton("확인");
	    
	   Dialog(){
		      
		   for(int i=0;i<item.length;i++)
			   namecombo.addItem(item[i]);
		   
		   setTitle("발주신청");
		   setLayout(new FlowLayout());
		   add(new JLabel("품명"));
		   add(namecombo);
		   add(new JLabel("수량"));
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