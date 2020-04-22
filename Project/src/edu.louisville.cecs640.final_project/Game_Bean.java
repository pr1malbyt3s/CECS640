package edu.louisville.cecs640.final_project;

/* Agent_Bean class used to set and access Free Agent attributes.
 * Contains a getter and setter method for reach table attribute.
 */

public class Agent_Bean {
	private String name;
	private String team;
	private int age;
	private String pos;
	private int g;
	private int a;
	private int pts;
	private int pm;
	private int cap;
	
	public Agent_Bean() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public int getG() {
		return g;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public int getA() {
		return a;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public int getPts() {
		return pts;
	}
	
	public void setPts(int pts) {
		this.pts = pts;
	}
	
	public int getPm() {
		return pm;
	}
	
	public void setPm(int pm) {
		this.pm = pm;
	}
	
	public int getCap() {
		return cap;
	}
	
	public void setCap(int cap) {
		this.cap = cap;
	}
}
