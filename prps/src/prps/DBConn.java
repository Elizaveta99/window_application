/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConn{
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	// --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
	static {
		conn = null;
                try{
		Class.forName("org.sqlite.JDBC");
                //String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
                String url = "jdbc:sqlite:C:/Users/User/Desktop/1studing/Проектирование программных систем 2 курс/lab6/prps_database_1.db";
               // String url = "jdbc:sqlite:C:\\Users\\User\\Desktop\\1studing\\Проектирование программных систем 2 курс\\lab6\\prps_database_1.db";
		conn = DriverManager.getConnection(url);
                statmt = conn.createStatement();
                }catch(Exception e){
                     System.out.println(e.getStackTrace());
                }
		System.out.println("База Подключена!");
	}

      
        
        public static String getRole(String login, String passwd)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM Authorization WHERE Login=\""+login + "\"");    
                if (resSet.next())
                    if (resSet.getString("Password").equals(passwd))
                    {
                        String role = resSet.getString("Role");
                        resSet.close();
                        return role;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return "err";
        }
        
        public static int getId(String login)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM Authorization WHERE Login=\""+login + "\"");    
                if (resSet.next())
                    {
                        int id = Integer.parseInt(resSet.getString("Id"));
                        resSet.close();
                        return id;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        public static int getHours(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM WorkingTime WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int hours = Integer.parseInt(resSet.getString("HoursAmount"));
                        resSet.close();
                        return hours;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        public static int getOrders(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM Orders WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int orders = Integer.parseInt(resSet.getString("OrdersAmount"));
                        resSet.close();
                        return orders;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        public static int getVocDaysAmount(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM Vocations WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int vocDays = Integer.parseInt(resSet.getString("VocDaysAmount"));
                        resSet.close();
                        return vocDays;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        public static int getOverHours(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM UserPayment WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int hours = Integer.parseInt(resSet.getString("OverHours"));
                        resSet.close();
                        return hours;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }

        public static int getVocPayments(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM UserPayment WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int voc = Integer.parseInt(resSet.getString("VocationPayment"));
                        resSet.close();
                        return voc;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        public static int getSalary(Integer id)
        {
            try {
                resSet = statmt.executeQuery("SELECT * FROM UserPayment WHERE User_Id=\""+id + "\"");    
                if (resSet.next())
                    {
                        int s = Integer.parseInt(resSet.getString("Salary"));
                        resSet.close();
                        return s;
                    }
                resSet.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            return 1;
        }
        
        
	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException 
        {
		conn.close();
		statmt.close();
		resSet.close();

		System.out.println("Соединения закрыты");
	}

}