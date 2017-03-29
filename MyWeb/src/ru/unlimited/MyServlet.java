package ru.unlimited;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet 
{
	public String readStringProperly(String mistake) 
	{
		String result = "";
		for(int i = 0; i < mistake.length(); i++)
		{
			if(mistake.charAt(i) == '<')
				result+= '[';
			else
			{
				if(mistake.charAt(i) == '>')
					result+= ']';
				else
					result+= mistake.charAt(i);
			}
			
		}
		return result;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html"); 
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
		} 
		catch (ClassNotFoundException e) 
		{
		}
		String server = "localhost";
		String port = "1521";
		String sid = "XE";
		String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
		Locale.setDefault(Locale.ENGLISH);
		Connection con  = null;
		try 
		{
			 con = DriverManager.getConnection(url, "system", "worldcomputing");
		} 
		catch (SQLException e) 
		{
		}
		java.sql.Statement state = null;
		try 
		{
			state = con.createStatement();
		} 
		catch (SQLException e)
		{
		}
		String sql = "SELECT HR.s_product.name  , CONCAT('$',HR.s_item.price) AS PRICE, HR.s_item.ord_id AS ORDER_ID " + 
				"FROM HR.s_product, HR.s_item " + 
				"WHERE HR.s_item.product_id = HR.s_product.id ";
		ResultSet rs = null;
		try 
		{
			rs = state.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
		}
		try 
		{
			while(rs.next())
			{
				out.println("<h3>" + "Product name: "  + rs.getString(1) + ", Price: " + rs.getString(2) + ", ID: " + rs.getInt(3) + "<br></h3>");
			}
		} 
		catch (SQLException e) 
		{
		}
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		String answer = "<outer>";
		
		
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
		} 
		catch (ClassNotFoundException e) 
		{
		}
		
		String server = "localhost";
		String port = "1521";
		String sid = "XE";
		String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
		Locale.setDefault(Locale.ENGLISH);
		Connection con  = null;
		try 
		{
			 con = DriverManager.getConnection(url, "system", "worldcomputing");
		} 
		catch (SQLException e) 
		{
		}
		
		java.sql.Statement state = null;
		try 
		{
			state = con.createStatement();
		} 
		catch (SQLException e)
		{
		}
		
		String sql = "SELECT HR.s_product.name  , CONCAT('$',HR.s_item.price) AS PRICE, HR.s_item.ord_id AS ORDER_ID " + 
				"FROM HR.s_product, HR.s_item " + 
				"WHERE HR.s_item.product_id = HR.s_product.id ";
		ResultSet rs = null;
		try 
		{
			rs = state.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
		}
		try 
		{
			while(rs.next())
			{
				answer+=("<tag><h3>" + "Product name: "  + readStringProperly(rs.getString(1)) + ", Price: " + 
						readStringProperly(rs.getString(2)) + ", ID: " + rs.getInt(3) + "</h3></tag>");
			}
		} 
		catch (SQLException e) 
		{
		}
		answer+="</outer>";
		out.println(answer);
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
		}
		
	}

}