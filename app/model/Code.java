package model;


public class Code {
	protected String code;
	protected int vote;

	public Code() {
		// TODO Auto-generated constructor stub
	}

	public Code(int vote, String code) {
		this.code = code;
		this.vote = vote;
	}
	
	public Code(String code) {
		this.code = code;
		this.vote = 0;
	}
	
	public String getCode() {
		return code;
	}

	public int getVote() {
		return vote;
	}

}
