package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Query {
	String id;
	String text;
	
	public Query(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static List<Query> ReadFromFile(String inputFile) {
		List<Query> queries = new ArrayList<Query>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));

			StringBuilder sb = new StringBuilder();
			String line;
			line = br.readLine();

			while (line != null) {
				String[] split = line.split("\\t", 2);
				if (split.length < 2) {
					line = br.readLine();
					continue;
				}
				String id = split[0];
				String text = split[1];
				Query query = new Query(id, text);
				queries.add(query);
				line = br.readLine();
			}
			String everything = sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queries;
	}
}
