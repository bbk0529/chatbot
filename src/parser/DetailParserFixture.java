package parser;

import java.sql.SQLException;
import java.util.List;

import ibatis.GameDAO;
import model.GameVO;

public class DetailParserFixture extends DetailParser {

	private String intent_detail;
	private String target;
	
	
	public DetailParserFixture(String intent_detail, String target) {
		this.intent_detail = intent_detail;
		this.target = target;
	}

	@Override
	public String parsing() throws SQLException {
		List<GameVO> list = new GameDAO().select(target, intent_detail);
		StringBuffer sb= new StringBuffer();
		for (GameVO  gameVO : list)
		{
			sb.append(gameVO+"<br/>");			
		}
		return sb.toString();
	}
}
