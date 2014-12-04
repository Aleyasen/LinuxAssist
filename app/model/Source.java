package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Source {
	
	MongoClient mongo = new MongoClient( "localhost" , 27017 );
	DB db = mongo.getDB("testdb");
	DBCollection table = db.getCollection("sources");
	DBCursor cursor = table.find();
	
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

	@Override
	public String toString() {
		return this.url;
	}

	public static List<Source> ReadSourcesFromFile(InputStream input) {
		List<Source> sources = new ArrayList<Source>();
		
		try {
			//BufferedReader br = new BufferedReader(new FileReader(inputFile));
			//BufferedReader br = new BufferedReader(new FileReader(input));

			BufferedReader br = new BufferedReader(new InputStreamReader(input));


			StringBuilder sb = new StringBuilder();
			String line;
			line = br.readLine();
			
			

			while (line != null) {
				String[] split = line.split("\\t");
				String url = null, codeSelector = null, voteSelector=null, answerSelector=null;
				if (split.length == 2) {
					url = split[0];
					codeSelector = split[1];
					
				} else if (split.length == 4) {
					url = split[0];
					codeSelector = split[1];
					voteSelector = split[2];
					answerSelector = split[3];	
				}

				Source src = new Source(url, codeSelector, voteSelector, answerSelector);
				
				//mongoDB
				BasicDBObject document = new BasicDBObject();
				document.put("source", url);
				document.put("code selector", codeSelector);
				table.insert(document);
				
				sources.add(src);
				line = br.readLine();
			}
			String everything = sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(sources);

		return sources;
	}
	/**
	public String convertStreamToString(InputStream input) { 
	    // ???
	
		StringWriter writer = new StringWriter();
		IOUtils.copy(input, writer, encoding);
		String theString = writer.toString();
	}
	**/
}
