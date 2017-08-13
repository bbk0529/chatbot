package controllers;


import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import model.GameVO;
import model.PlayerVO;
import model.TeamVO;

/**
 * Servlet implementation class ConnectionTest
 */
@WebServlet("/ConnectionTest")
public class ConnectionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;
	private StringTokenizer st;
	private StringBuffer sql;
	PlayerVO playerVO = new PlayerVO();
	TeamVO teamVO = new TeamVO();
	GameVO gameVO = new GameVO();
	boolean team_check = false;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String workspace_id = "4ff25717-9fcc-4c28-8f5f-fa6f541a425a";
        String username = "86990b95-d7b9-42f2-9996-1fcd2accc699";
        String password = "dkN5Vd8R75PV";
        String message = "손흥민 나이?";
        System.out.println(message);
        ConversationService service = new ConversationService("2017-05-26");
        service.setUsernameAndPassword(username, password);
        
       MessageRequest newMessage = new MessageRequest.Builder().inputText(message).build();
       MessageResponse response = service.message(workspace_id, newMessage).execute();
       st = new StringTokenizer(response.getText().get(0), "|");  
       System.out.println(st);
       System.out.println(response.getText().toString());
      
       sql = new StringBuffer();
       String answer = null;
      
       /*
       for(int i=0; i<st.countTokens(); i++){
    	   String token = st.nextToken();
    	   System.out.println(token);
    	   String player_name;
    	   String team_name;
    	   switch(token){
    	   case "intent_player": //선수 질문
    		   String n_token = st.nextToken().trim();
    		   player_name = st.nextToken().trim();
    		   sql.append(" SELECT player,game_count,p_goal,p_assist,team,p_no,position,age,height,weight,nationality");
    		   sql.append(" FROM tb_player");
    		   sql.append("	WHERE player = ?");
    		   try {
				executePlayerSQL(player_name);
				switch(n_token){
				case "nationality":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getNationality());
					break;
				case "team":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getTeam());
					break;
				case "position":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getPosition());
					break;
				case "goals":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getP_goal());
					break;
				case "assists":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getP_assist());
					break;
				case "age":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getAge());
					break;
				case "height":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getHeight());
					break;
				case "weight":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getWeight());
					break;
				case "p_no":
					System.out.println(playerVO.getPlayer() + " : " + playerVO.getP_no());
					break;
				case "profile":
					System.out.println(playerVO);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    		   break;
    	   case "intent_team": //팀 질문
    		   String nt_token = st.nextToken().trim();
    		   team_name = st.nextToken().trim();
    		   sql.append(" SELECT team_name,team_name_kr,lastResult,teamRankingGroup,teamCode,gainGoal,gameCount,gainPoint,foul,lost,won,rank,drawn");
    		   sql.append(" FROM tb_team");
    		   sql.append("	WHERE team_name = ?");
    		   try {
   				executeTeamSQL(team_name);
   				switch(nt_token){
   				case "lastResult":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getLastResult());
   					break;
   				case "teamRankingGroup":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getTeamRankingGroup());
   					break;
   				case "teamCode":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getTeamCode());
   					break;
   				case "gainGoal":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getGainGoal());
   					break;
   				case "gameCount":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getGameCount());
   					break;
   				case "gainPoint":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getGainPoint());
   					break;
   				case "foul":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getFoul());
   					break;
   				case "lost":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getLost());
   					break;
   				case "won":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getWon());
   					break;
   				case "rank":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getRank());
  					break;
   				case "drawn":
   					System.out.println(teamVO.getTeam_name() + " : " + teamVO.getDrawn());
   					break;
   				}
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
    		   break;
    	   case "intent_fixture": //경기 일정
    		   sql.append(" SELECT game_date,homeTeamName,awayTeamName,homeTeamScore,awayTeamScore,stadium");
    		   sql.append(" FROM tb_game");
    		   sql.append(" WHERE game_date BETWEEN to_date(?,'YYYY-MM-DD') AND to_date(?,'YYYY-MM-DD')+7");
    		   String date_token = st.nextToken().trim();
    		   String team_name1="zz";
    		   if(st.hasMoreTokens()){
    			   team_check = true;
    			   team_name1 = st.nextToken().trim();
       			   sql.append(" AND (homeTeamName = ? OR awayTeamName = ?)");
    		   }
    		   try {
    			   List<GameVO> list = executeGameSQL(date_token, team_name1);
    			   System.out.println("경기 결과는 이렇다");
    			   for(GameVO gVO : list){
    				   System.out.println(gVO);
    			   }
    		   } catch (Exception e) {
    			   e.printStackTrace();
    		   }
    		   break;
    	   }
       }
       
    }
    
    private List<GameVO> executeGameSQL(String date_token,String team_name) throws SQLException, ClassNotFoundException {
    	Connection cn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<GameVO> list = new ArrayList<GameVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "bigdata", "bigdata");
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, date_token);
			ps.setString(2, date_token);
			if(team_check){
				ps.setString(3, team_name);
				ps.setString(4, team_name);
			}
			rs = ps.executeQuery();
			
			while(rs.next()){
				gameVO.setGame_date(rs.getDate("game_date"));
				gameVO.setHomeTeamName(rs.getString("homeTeamName"));
				gameVO.setAwayTeamName(rs.getString("awayTeamName"));
				gameVO.setHomeTeamScore(rs.getInt("homeTeamScore"));
				gameVO.setAwayTeamScore(rs.getInt("awayTeamScore"));
				gameVO.setStadium(rs.getString("stadium"));
				list.add(gameVO);
			}
		} finally {
			dbClose(rs, ps, cn);
		}
		return list;
	}

	private void executeTeamSQL(String team_name_test) throws SQLException, ClassNotFoundException {
		Connection cn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "bigdata", "bigdata");
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, team_name_test);
			rs = ps.executeQuery();
			while(rs.next()){
				teamVO.setTeam_name(rs.getString("team_name"));
				teamVO.setTeam_name_kr(rs.getString("team_name_kr"));
				teamVO.setTeamRankingGroup(rs.getString("teamRankingGroup"));
				teamVO.setTeamCode(rs.getLong("teamCode"));
				teamVO.setGainGoal(rs.getLong("gainGoal"));
				teamVO.setGameCount(rs.getLong("gameCount"));
				teamVO.setGainPoint(rs.getLong("gainPoint"));
				teamVO.setFoul(rs.getLong("foul"));
				teamVO.setLost(rs.getLong("lost"));
				teamVO.setWon(rs.getLong("won"));
				teamVO.setRank(rs.getLong("rank"));
				teamVO.setDrawn(rs.getLong("drawn"));
			}
		} finally {
			dbClose(rs, ps, cn);
		}
		
	}

	private void executePlayerSQL(String n_token) throws SQLException, ClassNotFoundException {
		Connection cn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "bigdata", "bigdata");
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, n_token);
			rs = ps.executeQuery();
			while(rs.next()){
				playerVO.setPlayer(rs.getString("player"));
				playerVO.setGame_count(rs.getInt("game_count"));
				playerVO.setP_goal(rs.getInt("p_goal"));
				playerVO.setP_assist(rs.getInt("p_assist"));
				playerVO.setTeam(rs.getString("team"));
				playerVO.setP_no(rs.getInt("p_no"));
				playerVO.setPosition(rs.getString("position"));
				playerVO.setAge(rs.getInt("age"));
				playerVO.setHeight(rs.getInt("height"));
				playerVO.setWeight(rs.getInt("weight"));
				playerVO.setNationality(rs.getString("nationality"));
			}
		} finally {
			dbClose(rs, ps, cn);
		}
	}
    
    private void dbClose(ResultSet rs, PreparedStatement ps, Connection cn) {
		if (rs != null) try{rs.close();} catch(Exception e){}
		if (ps != null) try{ps.close();} catch(Exception e){}
		if (cn != null) try{cn.close();} catch(Exception e){}
	}
*/
}
}
