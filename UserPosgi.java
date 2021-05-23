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
		String OrderMenu[] = {null,null,null,null,null}; //최대5개
		String OrderSize[] = {null,null,null,null,null}; //최대5개
		String PriceTx = null;
		
		String OrderList = null;
		
		int PriceTotal = 0; // 계산시 총 가격에 사용
		int MePrice=0,SiPrice=0; // 주문할때 선택한 메뉴 가격 저장
		int count=0; // 주문목록에 몇개했는지 확인하기 위해 필요
		int CashTotal=0,CardTotal=0;
		
		//차트부분및 주문에필요한 데이터
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		
	
	UserPosgi()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("일반 포스기");
		setBounds(100, 100, 800, 700);
		Container Pos = getContentPane();
		Pos.setLayout(null);
		
		JPanel LogoPanel = new JPanel();
		LogoPanel.setBounds(5, 5, 100, 70);

		ImageIcon Logo = new ImageIcon("smallLogo.png");
		JLabel LogName = new JLabel(Logo);
		LogName.setBounds(0, 0, 60, 50);
		LogoPanel.add(LogName);
		
		//주문버튼 부분 판넬
		JPanel OrderPanel = new JPanel();
		OrderPanel.setBounds(0, 15, 784, 639);
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
		OrderBtnPanel.setBounds(35, 500, 713, 100);
		OrderBtnPanel.setLayout(new FlowLayout(0,30,50));
		JButton PlusBtn = new JButton("추가");
		PlusBtn.setPreferredSize(new Dimension(120, 50));
		JButton CashBtn = new JButton("현금계산");
		CashBtn.setPreferredSize(new Dimension(120, 50));
		JButton CardBtn = new JButton("카드계산");
		CardBtn.setPreferredSize(new Dimension(120, 50));
		JButton EndBtn = new JButton("영업종료");
		EndBtn.setPreferredSize(new Dimension(120, 50));
		OrderBtnPanel.add(PlusBtn);
		OrderBtnPanel.add(CashBtn);
		OrderBtnPanel.add(CardBtn);
		OrderBtnPanel.add(EndBtn);
		
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
				Pos.add(LogoPanel);
				Pos.add(OrderPanel);
				setVisible(true);
				
				
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserPosgi();
	}

}
