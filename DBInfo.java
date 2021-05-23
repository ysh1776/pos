package fanal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class DBInfo {
	
	
	//�Լ��� �����ϱ����� ��ü�� �����ϴ� �Լ�����
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
	
	
	
	//�����ڷ� ��� �ڵ����� ����
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
	//ȸ������
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
	
	//�α���
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
	
	//���̵�üũ
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
		
	
	//�ֹ� �����
	public void InventoryUse(String name, String size) throws Exception
	{
		


		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sqlLogin=null;
			if(name.equals("��Ű") ||  name.equals("��������")||  name.equals("���Ʈ"))
			{
				sqlLogin = "Update jaego set count=count-1 where title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				if(name.equals("��Ű"))
					pstmt.setString(1, "��Ű");
				else if(name.equals("��������"))
					pstmt.setString(1, "����ũ");		
				else if(name.equals("���Ʈ"))
					pstmt.setString(1, "���Ʈ");
				pstmt.executeUpdate();	
			}
			
			else if(name.equals("�Ƹ޸�ī��") || name.equals("����")  )
			{
				String name2=null;
				if(name.equals("�Ƹ޸�ī��"))
				name2 = "Ŀ��";
				else
				name2 = "����";
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
			else if(name.equals("īǪġ��") || name.equals("���̵�") || name.equals("������"))
			{
				String name2 = null,name3=null;
				if(name.equals("īǪġ��"))
				{
					name2="Ŀ��";
					name3="�Ŀ��";
				}
				else if(name.equals("���̵�"))
				{
					name2="����";
					name3="ź���";
				}
				else if(name.equals("������"))
				{
					name2="���ںи�";
					name3="����";
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
			else if(name.equals("ī���ī"))
			{
				if(size.equals("Tall"))
					sqlLogin = "Update jaego set count=count-1 where title=? or title=? or title=?";
					else if(size.equals("Large"))
					sqlLogin = "Update jaego set count=count-2 where title=? or title=? or title=?";
					else if(size.equals("XLarge"))
					sqlLogin = "Update jaego set count=count-3 where title=? or title=? or title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, "Ŀ��");
				pstmt.setString(2, "�Ŀ��");
				pstmt.setString(3, "����ũ��");
				pstmt.executeUpdate();
			}
			else if(name.equals("�ٴҶ��"))
			{
				if(size.equals("Tall"))
					sqlLogin = "Update jaego set count=count-1 where title=? or title=? or title=?or title=?";
					else if(size.equals("Large"))
					sqlLogin = "Update jaego set count=count-2 where title=? or title=? or title=? or title=?";
					else if(size.equals("XLarge"))
					sqlLogin = "Update jaego set count=count-3 where title=? or title=? or title=? or title=?";
				conn=DriverManager.getConnection(jdbcUri,dbId,dbPass);
				pstmt = conn.prepareStatement(sqlLogin);
				pstmt.setString(1, "Ŀ��");
				pstmt.setString(2, "����");
				pstmt.setString(3, "�÷�");
				pstmt.setString(4, "����ũ��");
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
	
	//��� ��Ÿ����
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
	 // ���ֽ�û
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
	 // ����Ȯ��
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
	 // ���� ��Ʈ ������ �̱�
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
		 
	 // �Ϻ� ��Ʈ ������ �̱�
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
	 
	 // �⺰ ��Ʈ ������ �̱�
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
	 
		//��������
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
	


