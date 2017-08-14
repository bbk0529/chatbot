package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// log4j 변수 설정
	/*private static Logger logger = Logger.getLogger(DispatcherServlet.class);*/
	
	private static Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	

	private static Map<String, AbstractController> controllerMap =
			new HashMap<String, AbstractController>();

	@Override
	public void init() throws ServletException {
		logger.info("DispatcherServlet.init() 수행중...");
		InputStream is = null;
		Properties pr = new Properties();
		String filePath = this.getClass().getResource("").getPath();
		try {
			is = new FileInputStream(
					filePath + "dispatcher-servlet.properties");
			pr.load(is);
			for(Object obj:pr.keySet()) {
				String key = ((String)obj).trim();
				String classPath = (pr.getProperty(key)).trim();
				
				try {
					Class className = Class.forName(classPath);
					controllerMap.put(key, (AbstractController)className.newInstance()); // 미리 instance를 만들어서 key, instance형태로 다 입력해둠. 
					logger.info("loaded : " + key);
				} catch(Exception e) {
					e.printStackTrace();
					logger.info("failure : " + key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) try{is.close();} catch(Exception e){};
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contextPath = request.getContextPath();
		String action = request.getRequestURI().trim().substring(contextPath.length());
		logger.info("Dispatcher / service : " + action);

		AbstractController controller = null;
		ModelAndView mav = null;

		controller = controllerMap.get(action); // CONTROLLER에는 MAP TYPE으로 ACTION 이름과 해당 파일LINK 가 있음. dispatcher-servlet.properties 
		if (controller == null) {
			logger.info("수행할 액션이 없거나 AbstractController의 서브타입이 아닙니다.");
			return;
		}
		mav = controller.handleRequestInternal(request, response);
		
		if (mav != null) {
			for(String key : mav.getModel().keySet()){
				request.setAttribute(key, mav.getModel().get(key)); // 전달해주는 객체 instance 등 담기
			}
			request.setAttribute("test", "Test message");
			RequestDispatcher dispatcher = 	request.getRequestDispatcher(mav.getViewName()); // 이동할 위치  
			dispatcher.forward(request, response);
			return;
		} else {
			logger.info("DispatcherServlet에서 길을 잃었다네");
		}
	}	
}
