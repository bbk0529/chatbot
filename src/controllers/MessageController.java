package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import model.GameVO;
import model.PlayerVO;
import model.TeamVO;

/**
 * Servlet implementation class MessageController
 */
@WebServlet("/message")
public class MessageController extends BaseController {
	private DataSource ds = null;
	private StringTokenizer st;
	private StringBuffer sql;
	PlayerVO playerVO = new PlayerVO();
	TeamVO teamVO = new TeamVO();
	
	boolean team_check = false;
	
	@SuppressWarnings("unchecked")
	@Override
	protected String webServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String workspace_id = "4ff25717-9fcc-4c28-8f5f-fa6f541a425a";
	    String username = "86990b95-d7b9-42f2-9996-1fcd2accc699";
	    String password = "dkN5Vd8R75PV";
		
		BufferedReader br = req.getReader();
		
		String line = null;
		StringBuffer param = new StringBuffer();
		
		while((line=br.readLine())!=null){
			param.append(line);
		}
		
		//String message = param.toString();
		//System.out.println("message : " + message);

	    
	    String message = req.getQueryString();
		
		JSONObject result = new JSONObject();
		JSONObject msg = new JSONObject();
		
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
        service.setUsernameAndPassword(username, password);
        MessageRequest newMessage = new MessageRequest.Builder().inputText(message).build();
        MessageResponse response = service.message(workspace_id, newMessage).execute();
        st = new StringTokenizer(response.getText().get(0), "|");  
        sql = new StringBuffer();
        String answer = null;
        
        for(int i=0; i<st.countTokens(); i++){
     	   String token = st.nextToken();
     	   String player_name;
     	   String team_name;
     	   switch(token){
     	   case "intent_player": //선수 질문
     		   String n_token = st.nextToken().trim();
     		   player_name = st.nextToken().trim();
     		   sql.append(" SELECT player,game_count,p_goal,p_assist,team,p_no,position,age,height,weight,nationality");
     		   sql.append(" FROM sofi_player");
     		   sql.append("	WHERE player = ?");
     		   try {
 				executePlayerSQL(player_name);
 				switch(n_token){
 				case "nationality":
 					answer =  playerVO.getPlayer() + " 선수의 국적은 '" + playerVO.getNationality() + "'입니다.";
 					break;
 				case "team":
 					answer =  playerVO.getPlayer() + " 선수의 팀은 '" + playerVO.getTeam() + "'입니다.";
 					break;
 				case "position":
 					answer =  playerVO.getPlayer() + " 선수의 포지션은 '" + playerVO.getPosition() + "'입니다.";
 					break;
 				case "goals":
 					answer =  playerVO.getPlayer() + " 선수의 골은 " + playerVO.getP_goal() + "번 입니다.";
 					break;
 				case "assists":
 					answer =  playerVO.getPlayer() + " 선수의 어시스트는 " + playerVO.getP_assist() + "입니다.";
 					break;
 				case "age":
 					answer =  playerVO.getPlayer() + " 선수의 나이는 " + playerVO.getAge() + "살 입니다.";
 					break;
 				case "height":
 					answer =  playerVO.getPlayer() + " 선수의 키는 " + playerVO.getHeight() + "cm입니다.";
 					break;
 				case "weight":
 					answer =  playerVO.getPlayer() + " 선수의 몸무게는 " + playerVO.getWeight() + "kg입니다.";
 					break;
 				case "p_no":
 					answer =  playerVO.getPlayer() + " 선수의 등번호는 " + playerVO.getP_no() + "번 입니다.";
 					break;
 				case "profile":
 					answer =  playerVO.toString();
 					break;
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
     		   break;
     	   case "intent_team": //팀 질문
     		   String nt_token = st.nextToken().trim();
     		   team_name = st.nextToken().trim();
     		   sql.append(" SELECT team_name,team_name_kr,lastResult,teamRankingGroup,teamCode,gainGoal,gameCount,gainPoint,foul,lost,won,rank,drawn,manager");
     		   sql.append(" FROM sofi_team");
     		   sql.append("	WHERE team_name = ?");
     		   try {
    				executeTeamSQL(team_name);
    				switch(nt_token){
    				case "lastResult":
    					answer =  teamVO.getTeam_name() + " 팀의 최근 전적은 " + teamVO.getLastResult() + "입니다.";
    					break;
    				case "teamRankingGroup":
    					answer =  teamVO.getTeam_name() + " 팀의 랭킹그룹은 " + teamVO.getTeamRankingGroup() + "입니다.";
    					break;
    				case "teamCode":
    					answer =  teamVO.getTeam_name() + " 팀의 코드는 " + teamVO.getTeamCode()+"입니다.";
    					break;
    				case "gainGoal":
    					answer =  teamVO.getTeam_name() + " 팀의 득점골은  " + teamVO.getGainGoal() + "입니다.";
    					break;
    				case "gameCount":
    					answer =  teamVO.getTeam_name() + " 팀의 게임수는 " + teamVO.getGameCount() + "입니다.";
    					break;
    				case "gainPoint":
    					answer =  teamVO.getTeam_name() + " 팀의 득점은 " + teamVO.getGainPoint() + "점 입니다.";
    					break;
    				case "foul":
    					answer =  teamVO.getTeam_name() + " 팀의 파울 수는  " + teamVO.getFoul() + "개 입니다.";
    					break;
    				case "Result":
    					answer = teamVO.getTeam_name() + " 팀의 전적은 " + teamVO.getWon()+"승 "+teamVO.getLost()+"패 "+teamVO.getDrawn()+"무 입니다.";
    					break;
    				case "rank":
    					answer =  teamVO.getTeam_name() + " 팀의 순위는 " + teamVO.getRank() + "위 입니다.";
    					break;
    				case "manager":
    					answer = teamVO.getTeam_name()+ "팀의 감독은 '"+teamVO.getManager() + "'입니다.";
    					break;
    				
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
     		   break;
     	   case "intent_fixture": //경기 일정
     		   sql.append(" SELECT game_date,homeTeamName,awayTeamName,homeTeamScore,awayTeamScore,stadium");
     		   sql.append(" FROM sofi_game");
     		   sql.append(" WHERE game_date BETWEEN date_format(?,'%Y-%m-%d') AND date_format(?,'%Y-%m-%d')+7");
     		   String date_token = st.nextToken().trim();
     		   String team_name1="zz";
     		   if(st.hasMoreTokens()){
     			   team_check = true;
     			   team_name1 = st.nextToken().trim();
        			   sql.append(" AND (homeTeamName = ? OR awayTeamName = ?)");
     		   }
     		   try {
     			   System.out.println(date_token);
     			   List<GameVO> result_list = executeGameSQL(date_token, team_name1);
     			   for(GameVO gVO : result_list){
     				   answer += gVO.toString() +"\n===============\n";
     			   }
     		   } catch (Exception e) {
     			   e.printStackTrace();
     		   }
     		   break;
     	   }
		
		}
        System.out.println("return :"+answer);
        msg.put("text", answer);
        result.put("message", msg);
        req.setAttribute("data", result.toString());
        return "json";
	}
//####################################################################################################
	  private List<GameVO> executeGameSQL(String date_token,String team_name) throws SQLException, ClassNotFoundException {
	    	Connection cn =null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<GameVO> list = new ArrayList<GameVO>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/chatbot_data","root","2013111622");
				//cn = DriverManager.getConnection("jdbc:mysql://n1cop.cafe24.com:3306/n1cop","n1cop","n1stu!maestro");
				ps = cn.prepareStatement(sql.toString());
				ps.setString(1, date_token);
				ps.setString(2, date_token);
				if(team_check){
					ps.setString(3, team_name);
					ps.setString(4, team_name);
				}
				rs = ps.executeQuery();
				
				while(rs.next()){
					GameVO gameVO = new GameVO();
					gameVO.setGame_date(rs.getString("game_date"));
					gameVO.setHomeTeamName(rs.getString("homeTeamName"));
					gameVO.setAwayTeamName(rs.getString("awayTeamName"));
					gameVO.setHomeTeamScore(rs.getString("homeTeamScore"));
					gameVO.setAwayTeamScore(rs.getString("awayTeamScore"));
					gameVO.setStadium(rs.getString("stadium"));
					System.out.println(gameVO+"\n");
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
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/chatbot_data","root","2013111622");
				//cn = DriverManager.getConnection("jdbc:mysql://n1cop.cafe24.com:3306/n1cop","n1cop","n1stu!maestro");
				ps = cn.prepareStatement(sql.toString());
				ps.setString(1, team_name_test);
				rs = ps.executeQuery();
				while(rs.next()){
					teamVO.setTeam_name(rs.getString("team_name"));
					teamVO.setTeam_name_kr(rs.getString("team_name_kr"));
					teamVO.setTeamRankingGroup(rs.getString("teamRankingGroup"));
					teamVO.setTeamCode(rs.getString("teamCode"));
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
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/chatbot_data","root","2013111622");
				//cn = DriverManager.getConnection("jdbc:mysql://n1cop.cafe24.com:3306/n1cop","n1cop","n1stu!maestro");
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
}
