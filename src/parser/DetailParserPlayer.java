package parser;

import java.sql.SQLException;

import ibatis.PlayerDAO;
import model.PlayerVO;

public class DetailParserPlayer extends DetailParser{

	private String intent_detail;
	private String target;
	
	public DetailParserPlayer(String intent_detail, String target) {
		this.intent_detail = intent_detail;
		this.target = target;
	}
	
	@Override
	public String parsing () throws SQLException{
	
		PlayerVO playerVO = new PlayerDAO().select(target, intent_detail);
		
		if (intent_detail.equals("team")) return (target + "선수의 팀은 " + playerVO.getTeam());
		else if (intent_detail.equals("nationality")) return (target + "선수의 국적은 " + playerVO.getNationality());
		else if (intent_detail.equals("position")) return (target + "선수의 포지션은 " + playerVO.getPosition());
		else if (intent_detail.equals("weight")) return (target + "선수의 몸무게는 " + playerVO.getWeight());
		else if (intent_detail.equals("height")) return (target + "선수의 키는 " + playerVO.getHeight());
		else if (intent_detail.equals("goals")) return (target + "선수의 득점은 " + playerVO.getP_goal());
		else if (intent_detail.equals("assists")) return (target + "선수의 어시스트는 " + playerVO.getP_assist());
		else if (intent_detail.equals("p_no")) return (target + "선수의 등번호는 " + playerVO.getP_no());
		else if (intent_detail.equals("age")) return (target + "선수의 나이는 " + playerVO.getAge());
		else return playerVO.toString();
	}
}
