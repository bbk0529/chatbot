package model;
public class TeamVO {
    private String team_name;
    private String team_name_kr;
    private String lastResult;
    private String teamRankingGroup; 
    private String teamCode;
    private long    gainGoal;
    private long    gameCount;
    private long    gainPoint;
    private long    foul;
    private long    lost;
    private long    won;
    private long rank;
    private long drawn;
    private String manager;
    public String getTeam_name() {
        return team_name;
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
    public String getTeam_name_kr() {
        return team_name_kr;
    }
    public void setTeam_name_kr(String team_name_kr) {
        this.team_name_kr = team_name_kr;
    }
    public String getLastResult() {
        return lastResult;
    }
    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }
    public String getTeamRankingGroup() {
        return teamRankingGroup;
    }
    public void setTeamRankingGroup(String teamRankingGroup) {
        this.teamRankingGroup = teamRankingGroup;
    }
    public String getTeamCode() {
        return teamCode;
    }
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }
    public long getGainGoal() {
        return gainGoal;
    }
    public void setGainGoal(long gainGoal) {
        this.gainGoal = gainGoal;
    }
    public long getGameCount() {
        return gameCount;
    }
    public void setGameCount(long gameCount) {
        this.gameCount = gameCount;
    }
    public long getGainPoint() {
        return gainPoint;
    }
    public void setGainPoint(long gainPoint) {
        this.gainPoint = gainPoint;
    }
    public long getFoul() {
        return foul;
    }
    public void setFoul(long foul) {
        this.foul = foul;
    }
    public long getLost() {
        return lost;
    }
    public void setLost(long lost) {
        this.lost = lost;
    }
    public long getWon() {
        return won;
    }
    public void setWon(long won) {
        this.won = won;
    }
    public long getRank() {
        return rank;
    }
    public void setRank(long rank) {
        this.rank = rank;
    }
    public long getDrawn() {
        return drawn;
    }
    public void setDrawn(long drawn) {
        this.drawn = drawn;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    @Override
    public String toString() {
        return team_name + team_name_kr + "최근 경기 결과는 " + lastResult + "입니다.<br/>" +
                "teamRankingGroup=" + teamRankingGroup + ", teamCode=" + teamCode + ", gainGoal=" + gainGoal
                + "현재까지의 게임 숫자 " + gameCount + ", 승점은 " + gainPoint + ", 파울은" + foul + ", 패배는" + lost
                + ", 승점=" + won + ", 순위=" + rank + ", 비긴숫자 : " + drawn + ", 메니져는 " + manager + "입니다."; 
    }
    
    
}
  