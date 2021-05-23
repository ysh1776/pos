package fanal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class DBInfo {
	
	
	//함수에 접근하기위해 객체를 생성하는 함수생성
	private static DBInfo instance = new DBInfo();
	
	public static DBInfo getInstance()
	{
		return instance;
	}
	
	
	private	String jdbcUri = "jdbc:mysql://localhost:3306/cafe?useSSL=false";
	private	String dbId = "root";
	private	String dbPass = "121230";
	
	PreparedStatement pstmt=null;
	Connection conn=null;
	
	
	
	//생성자로 디비를 자동으로 연결
	public DBInfo() {
	try{
		Class.forName("com.mysql.jdbc.Driver");
	}
	catch(Exception e)
	{
        Excp ex = new Excp();
        ex.db_error();
	}
	}
	//회원가입
	public void JoinAccess(String id, String password, String name, String email) throws Exception
	{
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sqlJoin = "insert into posmember values(?,?,?,?,?)";
			conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
			pstmt = conn.prepareStatement(sqlJoin);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, "0");
			pstmt.executeUpdate();
		
			Employee ep = new Employee();
			ep.set_data(id, name);
			
		}
		catch(Exception e)
		{
            Excp ex = new Excp();
            ex.db_error();
		}
		finally{
			if(pstmt!=null)
				try{pstmt.close();} catch(SQLException sqle) {}
			if(conn != null)
				try{conn.close();} catch(SQLException sqle) {}
				}
	}
	
	//로그인
	public int LoginAccess(String id, String passwd) throws Exception
	{
		
		ResultSet rs = null;
		int re = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sqlLogin = "select password,Num from posmember where id = ? ";
			conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
			pstmt = conn.prepareStatement(sqlLogin);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(rs.getInt("Num")==1)
				{
					re=1;
				}
				else if(rs.getString("password").equals(passwd))
				{
					re=2;
				}
				else
				{
					re=0;
				}
			}
			
		}
		catch(Exception e)
		{
            Excp ex = new Excp();
            ex.db_error();
		}
		finally{
			if(rs!=null)
				try {rs.close();} catch(SQLException sqle) {}
			if(pstmt!=null)
				try{pstmt.close();} catch(SQLException sqle) {}
			if(conn != null)
				try{conn.close();} catch(SQLException sqle) {}
				}
		return re;
	}
	
	//아이디체크
		public int IdCheck(String id) throws Exception
		{
			
			ResultSet rs = null;
			int re=1;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "select id from posmember where id = ? ";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
				re=0;	
				}
				
			}
			catch(Exception e)
			{
	            Excp ex = new Excp();
	            ex.db_error();
			}
			finally{
				if(rs!=null)
					try {rs.close();} catch(SQLException sqle) {}
				if(pstmt!=null)
					try{pstmt.close();} catch(SQLException sqle) {}
				if(conn != null)
					try{conn.close();} catch(SQLException sqle) {}
					}
			return re;
		}
		
	
	//주문 재고사용
	public void InventoryUse(String name, String size) throws Exception
	{
		


		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sqlLogin=null;
			if(name.equals("쿠키") ||  name.equals("조각케익")||  name.equals("요거트"))
			{
				sqlLogin = "Update jaego set count=count-1 where title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				if(name.equals("쿠키"))
					pstmt.setString(1, "쿠키");
				else if(name.equals("조각케익"))
					pstmt.setString(1, "케이크");		
				else if(name.equals("요거트"))
					pstmt.setString(1, "요거트");
				pstmt.executeUpdate();	
			}
			
			else if(name.equals("아메리카노") || name.equals("우유")  )
			{
				String name2=null;
				if(name.equals("아메리카노"))
				name2 = "커피";
				else
				name2 = "우유";
				if(size.equals("Tall"))
				sqlLogin = "Update jaego set count=count-1 where title=?";
				else if(size.equals("Large"))
				sqlLogin = "Update jaego set count=count-2 where title=?";
				else if(size.equals("XLarge"))
				sqlLogin = "Update jaego set count=count-3 where title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, name2);
				pstmt.executeUpdate();	
			}
			else if(name.equals("카푸치노") || name.equals("에이드") || name.equals("핫초코"))
			{
				String name2 = null,name3=null;
				if(name.equals("카푸치노"))
				{
					name2="커피";
					name3="파우더";
				}
				else if(name.equals("에이드"))
				{
					name2="과일";
					name3="탄산수";
				}
				else if(name.equals("핫초코"))
				{
					name2="초코분말";
					name3="우유";
				}
				if(size.equals("Tall"))
					sqlLogin = "Update jaego set count=count-1 where title=? or title=?";
					else if(size.equals("Large"))
					sqlLogin = "Update jaego set count=count-2 where title=? or title=?";
					else if(size.equals("XLarge"))
					sqlLogin = "Update jaego set count=count-3 where title=? or title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, name2);
				pstmt.setString(2, name3);
				pstmt.executeUpdate();	
			}
			else if(name.equals("카페모카"))
			{
				if(size.equals("Tall"))
					sqlLogin = "Update jaego set count=count-1 where title=? or title=? or title=?";
					else if(size.equals("Large"))
					sqlLogin = "Update jaego set count=count-2 where title=? or title=? or title=?";
					else if(size.equals("XLarge"))
					sqlLogin = "Update jaego set count=count-3 where title=? or title=? or title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, "커피");
				pstmt.setString(2, "파우더");
				pstmt.setString(3, "휘핑크림");
				pstmt.executeUpdate();
			}
			else if(name.equals("바닐라라떼"))
			{
				if(size.equals("Tall"))
					sqlLogin = "Update jaego set count=count-1 where title=? or title=? or title=?or title=?";
					else if(size.equals("Large"))
					sqlLogin = "Update jaego set count=count-2 where title=? or title=? or title=? or title=?";
					else if(size.equals("XLarge"))
					sqlLogin = "Update jaego set count=count-3 where title=? or title=? or title=? or title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, "커피");
				pstmt.setString(2, "우유");
				pstmt.setString(3, "시럽");
				pstmt.setString(4, "휘핑크림");
				pstmt.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
            Excp ex = new Excp();
            ex.db_error();
		}
		finally{
			if(pstmt!=null)
				try{pstmt.close();} catch(SQLException sqle) {}
			if(conn != null)
				try{conn.close();} catch(SQLException sqle) {}
				}
	}
	
	//재고 나타내기
	 void print(DefaultTableModel model) {
		 
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "select * from jaego";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				
				ResultSet rs = pstmt.executeQuery();
			 
			 int i=0;
			
			 while(rs.next()) {
				 model.addRow(new Object[] {rs.getInt("id"),rs.getString("title"),rs.getInt("count"),rs.getInt("bal")});
			 }
			 
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
		}
	 // 발주신청
	public void Balju(String name,int count)
	 {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "Update jaego set bal=? where title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setInt(1, count);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
		
			 
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
	 }
	 // 수령확인
	 void Confirm()
	 {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "Update jaego set count=count+bal,bal=0";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.executeUpdate();
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
	 }
	 // 월별 차트 데이터 뽑기
	 int Monthchart(int year,int month) {
		 int data = 0;
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "select * from sales where year=? AND month=?";
				
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, String.valueOf(year));
				pstmt.setString(2, String.valueOf(month));
				
				ResultSet rs = pstmt.executeQuery();
			 
			
			
			 while(rs.next()) {
				data += rs.getInt("money");
			 }
			
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
		return data;
		}
		 
	 // 일별 차트 데이터 뽑기
	 int Daychart(int year,int month,int day) {
		 int data = 0;
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "select * from sales where year=? AND month=? AND day=?";
				
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, String.valueOf(year));
				pstmt.setString(2, String.valueOf(month));
				pstmt.setString(3, String.valueOf(day));
				
				ResultSet rs = pstmt.executeQuery();
			 
			
			
			 while(rs.next()) {
				data += rs.getInt("money");
			 }
			
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
		return data;
		}
	 
	 // 년별 차트 데이터 뽑기
	 int Yearchart(int year) {
		 int data = 0;
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				String sqlLogin = "select * from sales where year=?";
				
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, String.valueOf(year));
				ResultSet rs = pstmt.executeQuery();
				
			 while(rs.next()) {
				data += rs.getInt("money");
			 }
			
		}
		 catch (Exception e)
		 {
	            Excp ex = new Excp();
	            ex.db_error();
		}
		 finally {
			
			}
		return data;
		}
	 
		//영업종료
		public void EndAccess(int year,int month,int day,int money) throws Exception
		{
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String sqlJoin = "insert into sales values(?,?,?,?)";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlJoin);
				pstmt.setString(1, String.valueOf(year));
				pstmt.setString(2, String.valueOf(month));
				pstmt.setString(3, String.valueOf(day));
				pstmt.setInt(4, money);
				pstmt.executeUpdate();
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error!");
			}
			finally{
				if(pstmt!=null)
					try{pstmt.close();} catch(SQLException sqle) {}
				if(conn != null)
					try{conn.close();} catch(SQLException sqle) {}
					}
		}	 
	 
}
	


