package edu.louisville.cecs640.final_project;

/* Player_Bean class used to set and access Player attributes.
 * Contains a getter and setter method for reach table attribute.
 */

public class Player_Bean {
	private String name;
	private int num;
	private int age;
	private String pos;
	private int gp;
	private int g;
	private int a;
	private int pts;
	private int pim;
	private double xgf;
	private double xga;
	
	public Player_Bean() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
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
	
	public int getGP() {
		return gp;
	}
	
	public void setGP(int gp) {
		this.gp = gp;
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
	
	public int getPim() {
		return pim;
	}
	
	public void setPim(int pim) {
		this.pim = pim;
	}
	
	public double getXGF() {
		return xgf;
	}
	
	public void setXGF(double xgf) {
		this.xgf = xgf;
	}
	
	public double getXGA() {
		return xga;
	}
	
	public void setXGA(double xga) {
		this.xga = xga;
	}
}
