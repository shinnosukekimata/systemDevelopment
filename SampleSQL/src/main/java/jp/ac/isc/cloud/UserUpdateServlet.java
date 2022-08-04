package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection users = null;
		try {
			users = DBConnection.openConnection();
			String id = request.getParameter("updateId");
			String name = request.getParameter("updateName");
			String picture = request.getParameter("updatePicture");
			Statement state = users.createStatement();
			if(!name.equals("")) {
				state.executeUpdate("UPDATE user_table SET name='" + name + "' WHERE id ='" + id + "'");
			}
			if(!picture.equals("")) {
				state.executeUpdate("UPDATE user_table SET picture='" + picture + "' WHERE id ='" + id + "'");
			}
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
