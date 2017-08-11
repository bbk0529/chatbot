package ibatis;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import model.GameVO;

public class GameDAO {
		
		
// Ibatis Test 용 Code	
/*	public static void main(String[] args) throws SQLException {
		GameDAO gameDAO = new GameDAO();
		List <GameVO> list = new ArrayList<>();
		list = gameDAO.selectGame("본머스", "2017-01-01");
		for (GameVO gameVO : list) System.out.println(gameVO);
	} */ 
	
	public  List<GameVO> select(String teamName, String date) throws SQLException {
		GameVO gameVO = new GameVO();
		gameVO.setHomeTeamName(teamName);
		gameVO.setGame_date(date);
		SqlMapClient sqlmap = QueryHandler.getInstance();
		return sqlmap.queryForList("game.selectGame", gameVO);
	}
}
