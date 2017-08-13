package controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionTest
 */
@WebServlet("/BaseController")
public abstract class BaseController extends HttpServlet {

	
	// GET 방식 처리
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doProcess(req, resp);
	}
	//POST 방식 처리
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	// GET, POST 처리를 위임받아서 로직 수행하는 메서드
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); resp.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String view = this.webServlet(req, resp);
		
		if(view!= null){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/"+view+".jsp");
			rd.forward(req, resp);
		}
	}
	
	// BaseController를 상속 받을 경우 완성해야되는 메서드(사용자 로직은 webServlet메서드에서 처리한다.)
	protected abstract String webServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
    
  
