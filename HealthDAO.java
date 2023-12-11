package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bean.HealthBean;
import bean.HealthDTO;
public class HealthDAO {
	private String url = "jdbc:mysql://localhost/healthcheck";
    private String user = "root";
    private String pass = "nk061122";
    private Connection con = null;
    
    public void connect() { //DB接続
      try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	con = DriverManager.getConnection(url,user,pass);
      }catch(Exception e) {
    	  e.printStackTrace();
      }
    }
    
    public HealthDTO select() {
    	Statement stmt = null;
    	ResultSet rs = null;
    	HealthDTO dto = new HealthDTO();   
    	String sql = "SELECT * FROM user JOIN user_info ON user.user_id=user_info.user_id WHERE user.is_deleted=1;";
    	try {
    		connect();
    		
    		stmt = con.createStatement(); //ステートメントを作成
    		rs = stmt.executeQuery(sql);  //SQLを実行
    		
    		while(rs.next()) {
    			HealthBean hb = new HealthBean();
    			hb.setId(rs.getInt("user_id"));
    			hb.setName(rs.getString("name"));
    			hb.setHeight(rs.getDouble("height"));
    			hb.setWeight(rs.getDouble("weight"));
    			hb.setBmi(rs.getDouble("bmi"));
    			hb.setTargetBmi(rs.getDouble("target_bmi"));
    			hb.setTargetWeight(rs.getDouble("target_weight"));
    			dto.add(hb);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(rs != null) {rs.close();}
    	        if(stmt != null) {stmt.close();}
    	      } catch(Exception e){
    	        e.printStackTrace();
    	      }	
    	}
    	disconnect();
    	return dto;
     }
    
     public int insert(String name,double height,double weight,double bmi) {
    	 LocalDateTime dateTime = LocalDateTime.now();
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	 String created_at = dateTime.format(formatter);
    	 String sql = "INSERT INTO user (NAME,created_at,is_deleted) VALUES('"+name+"','"+created_at+"',1);";
         String sql2=  " INSERT INTO user_info (height,weight,bmi,user_id,created_at,is_deleted) VALUES ("+height+","+weight+","+bmi+",((SELECT MAX(user_id) FROM user)),'"+created_at+"',1);";
    	 
         
         return executesql(sql,sql2);
    	 
     }
     public int update(int id,String name,double height,double weight,double bmi) {
    	 Statement stmt =null;
    	 ResultSet rs = null;
    	 double target =0;
    	 String selectsql = "SELECT target_bmi FROM user_info WHERE user_id="+id+";";
    	 try {
    		 connect();
    		 stmt = con.createStatement();
    		 rs = stmt.executeQuery(selectsql);
    		 
    		 if(rs.next()) {
    			target = rs.getDouble("target_bmi");
    		 }
    	 }catch(Exception e) {
    		e.printStackTrace();
    	 }finally {
    		try {
    			if(rs != null) {rs.close();}
    	        if(stmt != null) {stmt.close();}
    	    }catch(Exception e){
    	         e.printStackTrace();
    	    }
    		disconnect();
    	 }
    	 LocalDateTime dateTime = LocalDateTime.now();
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	 String update_at = dateTime.format(formatter);
    	 String sql = "UPDATE user SET NAME='"+name+"',updated_at='"+update_at+"' WHERE user_id="+id+";";
    	 String sql2 = "UPDATE user_info SET height="+height+",weight="+weight+",bmi="+bmi+",updated_at='"+update_at+"' WHERE user_id="+id+";";
    	 if(target!=0) {
    		 double h =height/100;
    		 double w = target*(h*h);
    		 double targetWeight = -Math.floor(((weight-w)*100)/100);
    		 sql2= "UPDATE user_info SET height="+height+",weight="+weight+",bmi="+bmi+",target_weight="+targetWeight+",updated_at='"+update_at+"' WHERE user_id="+id+";";
    	   }
    	 return executesql(sql,sql2); 
     }
     public int delete(int id) {
    	 LocalDateTime dateTime = LocalDateTime.now();
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	 String update_at = dateTime.format(formatter);
    	 String sql = "UPDATE user SET is_deleted=0,updated_at='"+update_at+"'  WHERE user_id="+id+";";
    	 String sql2 = "UPDATE user_info SET is_deleted=0,updated_at='"+update_at+"' WHERE user_id="+id+";"; 
    	 return executesql(sql,sql2);
     }
     public int calculation(int id,double target) {
     	 Statement stmt = null;
     	 ResultSet rs = null;
     	 int result = 0;
     	 String selectSql = "SELECT height,weight FROM user_info WHERE user_id="+id+";";
     	 String insertSql = "UPDATE user_info SET target_bmi=(?),target_weight=(?) WHERE user_id="+id+";";
     	 try {
     		connect();
     		stmt = con.createStatement();
     		rs = stmt.executeQuery(selectSql);
     		
     		if(rs.next()){
     			double h = rs.getDouble("height")/100;
     			double w = rs.getDouble("weight");
          		double calcWeight = w-(target*(h*h));
     			calcWeight = -Math.floor(calcWeight*100)/100;
     		try{PreparedStatement pstmt = con.prepareStatement(insertSql);
     		    pstmt.setDouble(1, target);
                pstmt.setDouble(2, calcWeight);
                result = pstmt.executeUpdate();
     		    }catch(SQLException e) {
     			e.printStackTrace();
     		  }
     		}
     	 }catch(Exception e) {
    		e.printStackTrace();
    	 }finally {
    		try {
    			if(rs != null) {rs.close();}
    	        if(stmt != null) {stmt.close();}
    	        }catch(Exception e){
    	         e.printStackTrace();
    	        }
    		disconnect();
    	 }
     	 return result;
    	}
     
     public int executesql(String sql,String sql2) { //SQLの実行
    	 Statement stmt =null;
    	 Statement stmt2 =null;
    	 int result = 0;
    	 try {
    		 connect();
    		 stmt = con.createStatement();
    		 stmt2 = con.createStatement();
    		 result = stmt.executeUpdate(sql);
    		 result = stmt2.executeUpdate(sql2);
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }finally {
    		 try {
    			 if(stmt!=null) {stmt.close();}
    			 if (stmt2 != null) {stmt2.close();}
    		 }catch(Exception e) {
        		 e.printStackTrace();
    		 }
    	 disconnect();
    	 }
    	 return result;
     }
         
    public void disconnect() {  //DBの切断
    	 try{
    	   if(con != null) {con.close();}
    	 }catch(Exception e){
    	   e.printStackTrace();
    	 }
    }
}