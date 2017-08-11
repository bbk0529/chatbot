package ibatis;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import model.TeamVO;

public class TeamDAO {
	// Ibatis Test 용 Code
/*
	public static void main(String[] args) throws SQLException {
		TeamDAO teamDAO = new TeamDAO();
		
		TeamVO teamVO = new TeamVO();
		teamVO = teamDAO.selectTeam("Chelsea");
		System.out.println(teamVO);
	}
*/		
	public  TeamVO select(String teamName, String dummy) throws SQLException {
		SqlMapClient sqlmap = QueryHandler.getInstance();
		return (TeamVO) sqlmap.queryForObject("team.selectTeam", teamName);
	}
}
