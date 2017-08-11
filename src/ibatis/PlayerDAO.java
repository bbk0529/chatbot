package ibatis;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import model.PlayerVO;

public class PlayerDAO {
	// Ibatis Test 용 Code
/*
	public static void main(String[] args) throws SQLException {
		PlayerDAO playerDAO = new PlayerDAO();
		
		PlayerVO playerVO = new PlayerVO();
		playerVO = playerDAO.selectPlayer("Sakho Diafra");
		System.out.println(playerVO);
	}*/

	public  PlayerVO select(String playerName, String dummy) throws SQLException {
		SqlMapClient sqlmap = QueryHandler.getInstance();
		return (PlayerVO) sqlmap.queryForObject("player.selectPlayer", playerName);
	}
}
