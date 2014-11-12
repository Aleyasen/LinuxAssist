package model;

//class Answer to store code and its vote
public class Answer {
	protected String code;
	protected String vote;

	public Answer() {
		// TODO Auto-generated constructor stub
	}

	public Answer(String code, String vote) {
		this.code = code;
		this.vote = vote;
	}
	
	public Answer(String code) {
		this.code = code;
		this.vote = "None";
	}
	
	public String getCode() {
		return code;
	}

	public String getVote() {
		return vote;
	}
	
	//Print out the data of the Answer
	public void print(){
		System.out.println(code + " (vote:" + vote + ")");
	}

}
