package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class KeyboardController
 */
@WebServlet("/keyboard")
public class KeyboardController extends BaseController {

	@Override
	protected String webServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jo = new JSONObject();
		jo.put("type", "text");
		request.setAttribute("data", jo.toString());
		return "json";
	}
	

}
