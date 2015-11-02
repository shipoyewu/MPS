package databaseconnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.PreparedStatement;

public class DataBase {
	 	static String driver = null;
	    static String url = null;
	    static String username = null;
	    static String password = null;
	    
	 //连接前的初始化，加载驱动
	    static {
	        setDataBase();
	        try {
	            Class.forName(driver);
	            System.out.println("驱动加载成功");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    private static void setDataBase() {
	        String filePath = DataBase.class.getResource("").toString();
	        try {
	            filePath = DataBase.class.getResource("").toURI().getPath();
	        } catch (URISyntaxException ex) {
	            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        File file = new File(filePath + "sql.txt");
	        String files = null;
	        int index = 0;
	        int flag = 1;
	        try {
	            FileInputStream filein = new FileInputStream(file);
	            BufferedReader read = new BufferedReader(new InputStreamReader(filein));
	            try {
	                while ((files = read.readLine()) != null) {
	                    index = files.indexOf("=");
	                    switch (flag) {
	                        case 1:
	                        	driver = files.substring(index + 1, files.length());
	                        case 2:
	                            url = files.substring(index + 1, files.length());
	                        case 3:
	                            username = files.substring(index + 1, files.length());
	                        case 4:
	                            password = files.substring(index + 1, files.length());
	                    }
	                    flag++;
	                }
	            } catch (IOException ex) {
	                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    //得到数据库连接
	    public static Connection getConnection() {
	        Connection conn = null;
	        try {
	            conn = (Connection) DriverManager.getConnection(url, username, password);
	            //System.out.println("连接创建成功");
	        } catch (SQLException e) {
	            System.out.println("连接创建失败");
	            e.printStackTrace();
	        }
	        return conn;
	    }
	    
//	 // ִ数据库查询
//		public static ResultSet excuteQuery(String sql) {
//			Statement stm = null;
//			ResultSet resultSet = null;
//			Connection con = getConnection();
//			try {
//				// 创建一个执行数据库的对象stm
//				stm = con.createStatement();
//				/*
//				 * stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//				 * ResultSet.CONCUR_READ_ONLY);
//				 */
//				resultSet = stm.executeQuery(sql);
//			} catch (SQLException e) {
//				System.out.println("数据查询失败");
//				e.printStackTrace();
//			}
//			return resultSet;
//		}
//
//		// 数据库更新
//		public static boolean executeUpdate(String sql) {
//			
//			try {
//				stm = con.createStatement();
//				/*
//				 * stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//				 * ResultSet.CONCUR_READ_ONLY);
//				 */
//				stm.executeUpdate(sql);
//				con.commit();
//			} catch (Exception e) {
//				System.out.println("数据更新失败");
//				e.printStackTrace();
//			}
//		}
//
//	    //关闭结果集
//	    public static void closeStatement() { // 关闭结果集
//			if (stm != null) { // 如果stm 结果集对象不为null
//				try {
//					stm.close(); // 关闭stm 结果集对象
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	    // 关闭Connection连接
//	    public static void closeConnection() {
//			if (con != null) { // 如果conn 连接对象不为空
//				try {
//					con.close(); // 关闭conn 连接对象对象
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	    public static void free(ResultSet rs, Connection conn, java.sql.PreparedStatement pre) {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("关闭失败");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                System.out.println("关闭失败");
	                e.printStackTrace();
	            } finally {
	                try {
	                    if (pre != null) {
	                        pre.close();
	                    }
	                } catch (SQLException e) {
	                    System.out.println("关闭失败");
	                }
	            }
	        }
	    }
	    public static void freeStatement(Connection conn, Statement stmt) {

	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("关闭失败");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) {
	                	stmt.close();
	                }
	            } catch (SQLException e) {
	                System.out.println("关闭失败");
	            }
	        }
	    }
	  public static void main(String[] args){
		  System.out.println("\ntest\n");
		  DataBase con = new DataBase();
		  con.getConnection();
	  }  
	    
}
