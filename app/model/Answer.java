package model;

import java.util.*;

//class Answer to store code and its vote
//public class Answer implements Comparable<Answer> {
public class Answer {
	protected String code;
	protected int vote;

	public Answer() {
		// TODO Auto-generated constructor stub
	}

	public Answer(String code, int vote) {
		this.code = code;
		this.vote = vote;
	}
	
	public Answer(String code) {
		this.code = code;
		this.vote = -1;
		//this.vote = "None";
	}
	
	public String getCode() {
		return code;
	}

	public int getVote() {
		return vote;
	}
	
	//Print out the data of the Answer
	public void print(){
		if(vote == -1) 
			System.out.println(code);
		else	
			System.out.println(code + " (vote:" + vote + ")");
	}

	
	/**
	@Override
	public int compareTo(Answer other) {
		if ("None".equals(this.vote)) {
			return -1;
		}

		if ("None".equals(other.vote)) {
			return -1;
		}

		return this.vote.compareTo(other.vote);
	}
	**/
}
