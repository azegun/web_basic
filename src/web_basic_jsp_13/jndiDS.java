package web_basic_jsp_13;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class jndiDS {
		private static DataSource ds;

		private jndiDS() {}
		//스태틱 블럭 생성자 생성전에 스태틱 블럭 먼저생성
		static {
			try {
				InitialContext ic = new InitialContext();  //1. JNDI 서버 객체 생성
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/dbstudy"); //2.lookup
				System.out.println("ds : " + ds);
			}catch(NamingException e){
				e.printStackTrace();
			}
		}
		public static Connection getConnection() {
			try {
				return ds.getConnection();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		

}
