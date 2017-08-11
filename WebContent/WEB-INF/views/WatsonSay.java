package kr.co.n1books.conversation;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
public class WatsonSay {

	
	
	
	
	@RequestMapping(value = "watsonsay",
	headers="Accept=application/json;charset=UTF-8",
	produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public AnswerVO watsonsay(String isay) throws Exception {
		System.out.println("input : " + isay);
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("../system.properties");
		Properties props = new Properties();
		props.load(is);
		
		String workspace_id = props.getProperty("conversation.workspaceid");
		String username = props.getProperty("conversation.username");
		String password = props.getProperty("conversation.password");
		
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
		service.setUsernameAndPassword(username, password); 
		
		MessageRequest newMessage = new MessageRequest.Builder().inputText(isay).build();
		MessageResponse response = service.message(workspace_id, newMessage).execute();
		List<String> retList = response.getText();
		System.out.println("------------------------");
		for(String str : retList) {
			System.out.println(str);
		}
		System.out.println("------------------------");
		
		String ret = response.getText().get(0);
		String val = "";
		System.out.println("return : " + ret);
		if (ret.equals("맨유")) {
			val = "2017년 7월 20일 시합";
		} else if (ret.equals("아스날")) {
			val = "2017년 7월 25일 시합";
		} else {
			val = ret;
		}
		AnswerVO answerVO = new AnswerVO();
		answerVO.setSay(val);
		return answerVO;
	}
}
