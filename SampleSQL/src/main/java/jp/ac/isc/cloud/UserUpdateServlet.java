package jp.ac.isc.cloud;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection users = null;
		try {
			request.setCharacterEncoding("utf-8");
			users = DBConnection.openConnection();
			String id = request.getParameter("updateId");
			String name = request.getParameter("updateName");
			String picture = request.getParameter("updatePicture");
			Statement state = users.createStatement();
			String sql="";
			if(!name.equals("")) {
				sql = "name='" + name +"'";
			}
			if(!picture.equals("")) {
				if(!sql.equals("")) {
					sql = sql + ",";
				}
				sql = sql + "picture='"+picture+"'";
			}
			if(!sql.equals("")) {
				state.executeUpdate("UPDATE user_table SET " + sql + " WHERE id ='" + id + "'");
			}
			
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
