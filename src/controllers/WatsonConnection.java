package controllers;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class WatsonConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringTokenizer st;
	private StringBuffer sql;
	boolean team_check = false;
       
    public String service(HttpServletRequest req, HttpServletResponse res, String message) throws ServletException, IOException {
    	String workspace_id = "4ff25717-9fcc-4c28-8f5f-fa6f541a425a";
        String username = "86990b95-d7b9-42f2-9996-1fcd2accc699";
        String password = "dkN5Vd8R75PV";
        String returnedMessage = null;
        ConversationService conversationService = new ConversationService("2017-05-26");
        
        conversationService.setUsernameAndPassword(username, password);

       MessageRequest newMessage = new MessageRequest.Builder().inputText(message).build();
       System.out.println("INPUT TEXT : " + message);
       MessageResponse response = conversationService.message(workspace_id, newMessage).execute();
       returnedMessage = response.getText().toString();
       System.out.println("Message returned from WATSON :" + returnedMessage);
       return returnedMessage;
     
    }
}
