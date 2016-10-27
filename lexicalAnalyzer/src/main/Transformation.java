package main;

public class Transformation {

	String preStatus;
	String edge;
	String nextStatus;
	public Transformation(String preStatus, String edge, String nextStatus) {
		super();
		this.preStatus = preStatus;
		this.edge = edge;
		this.nextStatus = nextStatus;
	}
	@Override
	public String toString() {
		return "Transformation [preStatus=" + preStatus + ", edge=" + edge + ", nextStatus=" + nextStatus + "]";
	}
	
	
	
}
