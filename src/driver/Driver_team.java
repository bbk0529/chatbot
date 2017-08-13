package driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import crawler.Crawler;
import model.TeamVO;
import parser.Sofi_JSONParser_Team;

public class Driver_team {
	public static void main(String[] args) throws Exception {
		String rawData = null;
		String urlstr = "http://sports.news.naver.com/wfootball/record/index.nhn";

		Crawler crawer = new Crawler();
		crawer.setUrlstr(urlstr);
		crawer.sofi_WebReader();
		rawData = crawer.getRawData();

		Sofi_JSONParser_Team parser = new Sofi_JSONParser_Team();
		List<TeamVO> list = parser.do_parsing(rawData);
		/*
		StringBuffer sql = new StringBuffer();
//		sql.append(" INSERT INTO tb_team");
//		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		sql.append(" UPDATE tb_team SET");
		sql.append(" lastResult = ?,");
		sql.append(" teamRankingGroup = ?,");
		sql.append(" gainGoal = ?,");
		sql.append(" gameCount = ?,");
		sql.append(" gainPoint = ?,");
		sql.append(" foul = ?,");
		sql.append(" lost = ?,");
		sql.append(" won = ?,");
		sql.append(" rank = ?,");
		sql.append(" drawn = ?");
		sql.append(" WHERE team_name_kr=?");
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "bigdata", "bigdata");
		ps = cn.prepareStatement(sql.toString());
		int cnt = 0;
		for (TeamVO teamVO : list) {
			ps.setString(1, teamVO.getLastResult());
			ps.setString(2, teamVO.getTeamRankingGroup());
			ps.setLong(3, teamVO.getGainGoal());
			ps.setLong(4, teamVO.getGameCount());
			ps.setLong(5, teamVO.getGainPoint());
			ps.setLong(6, teamVO.getFoul());
			ps.setLong(7, teamVO.getLost());
			ps.setLong(8, teamVO.getWon());
			ps.setLong(9, teamVO.getRank());
			ps.setLong(10, teamVO.getDrawn());
			rs = ps.executeQuery();
			cnt++;
			System.out.println(cnt);
		}
		dbClose(rs, ps, cn);
	}
	private static void dbClose(ResultSet rs, PreparedStatement ps, Connection cn) {
		if (rs != null) try{rs.close();} catch(Exception e){}
		if (ps != null) try{ps.close();} catch(Exception e){}
		if (cn != null) try{cn.close();} catch(Exception e){}*/
	}
}