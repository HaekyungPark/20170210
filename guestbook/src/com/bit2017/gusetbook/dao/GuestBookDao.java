package com.bit2017.gusetbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2017.guestbook.vo.GuestBookVo;

public class GuestBookDao {

	public boolean delete(GuestBookVo guestBookVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1.JDBC Driver Loading (JDBC Class Loading)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻어오기 (connect to DB)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.SQL문 준비
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4.데이터 바인딩
			pstmt.setLong(1, guestBookVo.getNo());
			pstmt.setString(2, guestBookVo.getPassword());
			
			//5.execute SQL
			int count =pstmt.executeUpdate();
			boolean result = (count==1);
			return result;
			
			//conn.close(); --여기다 쓰면 안됨
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - "+ e );
			return false;
		} catch(SQLException e){
			System.out.println("error2-1 : " + e);
			return false;
		}finally{ 
			//3.자원정리
			try{
				if(conn != null)
				conn.close();
			}catch(SQLException e){
				System.out.println("error2-2: " + e );
			}
		}	
	}
	public boolean modify(GuestBookVo guestBookVo){
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
	    PreparedStatement pstmt = null;
	      
	      try {                                    // {} -> 안에 있는 건 지역변수
	         //1. JDBC Driver Loading ( JDBC Class Loading )
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         //2. Connection 얻어오기 ( Connect to DB )
	         String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
	         conn = DriverManager.getConnection(url, "webdb", "webdb");
	         
	         //3. SQL문 준비
	         String sql = "update guestbook set name=?,content=? where no=?";
	         pstmt = conn.prepareStatement(sql);
	         
	         //4. binding
	         pstmt.setString(1, guestBookVo.getName());
	         pstmt.setString(2, guestBookVo.getContent());
	         pstmt.setLong(3, guestBookVo.getNo());
	         
	         //5. SQL문 실행
	         int count = pstmt.executeUpdate();
	         
	         //6.결과
	         
	         return count == 1;

	      } catch (ClassNotFoundException e) {
	         System.out.println("error: 드라이버 로딩 실패- " + e);
	         return false;
	      } catch (SQLException e) {
	         System.out.println("error: " + e);
	         return false;
	         //finally는 항상 꼭 실행된다.
	      } finally {
	         //3. 자원정리
	         try {
	            if( pstmt != null) {
	               pstmt.close();
	            }
	            if( conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            System.out.println("error: " + e);
	         }
	      } 
	}
	
	public boolean insert(GuestBookVo guestBookVo){
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
	    PreparedStatement pstmt = null;
	      
	      try {                                    // {} -> 안에 있는 건 지역변수
	         //1. JDBC Driver Loading ( JDBC Class Loading )
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         //2. Connection 얻어오기 ( Connect to DB )
	         String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
	         conn = DriverManager.getConnection(url, "webdb", "webdb");
	         
	         //3. SQL문 준비
	         String sql = "insert into guestbook values(seq_guestbook.nextval,?,?,?,sysdate)";
	         pstmt = conn.prepareStatement(sql);
	         
	         //4. binding
	         pstmt.setString(1, guestBookVo.getName());
	         pstmt.setString(2, guestBookVo.getPassword());
	         pstmt.setString(3, guestBookVo.getContent());
	        
	         
	         //5. SQL문 실행
	         int count = pstmt.executeUpdate();
	         
	         //6.결과
	         
	         return count == 1;

	      } catch (ClassNotFoundException e) {
	         System.out.println("error: 드라이버 로딩 실패- " + e);
	         return false;
	      } catch (SQLException e) {
	         System.out.println("error: " + e);
	         return false;
	         //finally는 항상 꼭 실행된다.
	      } finally {
	         //3. 자원정리
	         try {
	            if( pstmt != null) {
	               pstmt.close();
	            }
	            if( conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            System.out.println("error: " + e);
	         }
	      }
	      
	   }
		
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				//1.JDBC Driver Loading (JDBC Class Loading)
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2.Connection 얻어오기 (connect to DB)
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
				
				//3.SQL문 준비
				String sql = "SELECT no, name, content, to_char(reg_date,'yyyy-dd-mm') from guestbook order by reg_date desc";
				stmt = conn.createStatement();
				rs=stmt.executeQuery(sql);
				
				//4.결과처리
				while(rs.next()){
					Long no = rs.getLong(1);
					String name = rs.getString(2);
					String content = rs.getString(3);
					String regDate = rs.getString(4);
					
					GuestBookVo vo = new GuestBookVo();
					vo.setNo(no);
					vo.setName(name);
					vo.setContent(content);
					vo.setRegDate(regDate);
					
					list.add(vo);
				}
				//conn.close(); --여기다 쓰면 안됨
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - "+ e );
				
			} catch(SQLException e){
				System.out.println("error : " + e);
				
			}finally{ 
				//3.자원정리
				try{
					if(rs !=null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(conn != null)
					conn.close();
				}catch(SQLException e){
					System.out.println("error: " + e );
				}
				
			}
			return list;
	}
}
