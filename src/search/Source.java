package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Source {

	protected String url;
	protected String codeSelector;
	protected double weight;
	protected String scoreSelector;
	protected String answerSelector;

	public Source() {
		// TODO Auto-generated constructor stub
	}

	public Source(String url, String selector) {
		super();
		this.url = url;
		this.codeSelector = selector;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScoreSelector() {
		return scoreSelector;
	}

	public void setScoreSelector(String scoreSelector) {
		this.scoreSelector = scoreSelector;
	}

	public String getAnswerSelector() {
		return answerSelector;
	}

	public void setAnswerSelector(String answerSelector) {
		this.answerSelector = answerSelector;
	}

	public String getCodeSelector() {
		return codeSelector;
	}

	public void setCodeSelector(String codeSelector) {
		this.codeSelector = codeSelector;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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
				if (split.length < 2) {
					line = br.readLine();
					continue;
				}
				String url = split[0];
				String selector = split[1];
				Source src = new Source(url, selector);
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
