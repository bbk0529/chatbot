package parser;

import java.sql.SQLException;

import ibatis.TeamDAO;
import model.TeamVO;

public class DetailParserTeam extends DetailParser {

	private String intent_detail;
	private String target;
	
	
	public DetailParserTeam(String intent_detail, String target) {
		this.intent_detail = intent_detail;
		this.target = target;
	}

	@Override
	public String parsing () throws SQLException{
			TeamVO teamVO  = new TeamDAO().select(target, intent_detail);
			String team_name_kr =teamVO.getTeam_name_kr(); 
			if (intent_detail.equals("lastResult")) return (team_name_kr + "팀의 최종전적은" + teamVO.getLastResult());
			else if (intent_detail.equals("teamRankingGroup")) return (team_name_kr + "팀의 랭킹 그룹은 " + teamVO.getTeamRankingGroup());
			else if (intent_detail.equals("teamCode")) return (team_name_kr + " 팀의 코드 " + teamVO.getTeamCode());
			else if (intent_detail.equals("gainGoal")) return (team_name_kr + "팀의 득점은 " + teamVO.getGainGoal());
			else if (intent_detail.equals("gameCount")) return (team_name_kr + "팀의 게임수는 " + teamVO.getGameCount());
			else if (intent_detail.equals("gainPoint")) return (team_name_kr +  "팀의 승점은" + teamVO.getGainPoint());
			else if (intent_detail.equals("foul")) return (team_name_kr + "의 파울 숫자는 " + teamVO.getFoul());
			else if (intent_detail.equals("lost")) return (team_name_kr + "의 진 게임 숫자는 " + teamVO.getLost());
			else if (intent_detail.equals("won")) return (team_name_kr + "의 이긴 게임 숫자는" + teamVO.getWon());
			else if (intent_detail.equals("rank")) return (team_name_kr + "팀의 순위는" + teamVO.getRank());
			else if (intent_detail.equals("drawn")) return (team_name_kr + "비긴 숫자는 " + teamVO.getDrawn());
			else if (intent_detail.equals("Result")) return (team_name_kr + "의 이긴 게임 숫자는 " + teamVO.getWon() + "승" + "이고 비긴 숫자는 " + teamVO.getDrawn() + "무" + "이며 진 숫자는 " + teamVO.getLost() + "패 입니다.");
			else if (intent_detail.equals("manager")) return (team_name_kr + "팀의 감독은 " + teamVO.getManager());
			else return teamVO.toString();
	}
}
