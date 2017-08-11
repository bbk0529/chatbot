package model;

public class PlayerVO {
	private String player;
	private int game_count;
	private int p_goal;
	private int p_assist;
	private String team;
	private int p_no;
	private String position;
	private int age;
	private int height;
	private int weight;
	private String nationality;
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getGame_count() {
		return game_count;
	}
	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}
	public int getP_goal() {
		return p_goal;
	}
	public void setP_goal(int p_goal) {
		this.p_goal = p_goal;
	}
	public int getP_assist() {
		return p_assist;
	}
	public void setP_assist(int p_assist) {
		this.p_assist = p_assist;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Override
	public String toString() {
/*		return "PlayerVO [player=" + player + ", game_count=" + game_count + ", p_goal=" + p_goal + ", p_assist="
				+ p_assist + ", team=" + team + ", p_no=" + p_no + ", position=" + position + ", age=" + age
				+ ", height=" + height + ", weight=" + weight + ", nationality=" + nationality + "]";*/
		return player + "선수는 " + team + " 소속이며 " + "<br />" + " 포지션은 " + position + " 등번호는 " + p_no + " 입니다. \n\n 출전경기수는 " + game_count + "경기이고, 득점은 " + p_goal + "이며," + 
		"<br />" + " 어시스트 " + p_assist + "를(을) 기록하고 있습니다. \n\n" + "국적은 " + nationality + " 이며, " + "<br />" + "나이는 " + age + "살  키는 " + height + "cm 몸무게는 " + weight + "kg" + " 입니다. "; 

}
	
		
}
