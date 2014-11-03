package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Source {
	
	protected String url;
	protected String codeSelector;
	protected double weight;
	protected String voteSelector;
	protected String answerSelector;

	public Source() {
		// TODO Auto-generated constructor stub
	}

	public Source(String url, String codeSelector) {
		super();
		this.url = url;
		this.codeSelector = codeSelector;
	}
	
	public Source(String url, String codeSelector, String voteSelector) {
		super();
		this.url = url;
		this.codeSelector = codeSelector;
		this.voteSelector = voteSelector;
		
	}
	
	public Source(String url, String codeSelector, String voteSelector, String answerSelector) {
		super();
		this.url = url;
		this.codeSelector = codeSelector;
		this.voteSelector = voteSelector;
		this.answerSelector = answerSelector;
		
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodeSelector() {
		return codeSelector;
	}

	public void setSelector(String codeSelector) {
		this.codeSelector = codeSelector;
	}

	public String getVoteSelector() {
		return voteSelector;
	}

	public void setVoteSelector(String voteSelector) {
		this.voteSelector = voteSelector;
	}
	
	public String getAnswerSelector() {
		return answerSelector;
	}

	public void setAnswerSelector(String answerSelector) {
		this.answerSelector = answerSelector;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void print(){
		System.out.println ("url" + ": " + url);
		System.out.println ("codeSelector" + ": " + codeSelector);
		System.out.println ("voteSelector" + ": " + voteSelector);
		System.out.println ("answerSelector" + ": " + answerSelector);
		System.out.println ("weight" + ": " + weight);
	}

	public static List<Source> ReadSourcesFromFile(String inputFile) {
		List<Source> sources = new ArrayList<Source>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));

			StringBuilder sb = new StringBuilder();
			String line;
			line = br.readLine();

			while (line != null) {
				String[] split = line.split("\\t");
				if (split.length < 4) {
					line = br.readLine();
					continue;
				}
				String url = split[0];
				String codeSelector = split[1];
				String voteSelector = split[2];
				String answerSelector = split[3];
				Source src = new Source(url, codeSelector, voteSelector, answerSelector);
				sources.add(src);
				line = br.readLine();
			}
			String everything = sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sources;
	}
}
