package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import string_similarity.JaroWinklerStrategy;
import string_similarity.SimilarityStrategy;
import string_similarity.StringSimilarityService;
import string_similarity.StringSimilarityServiceImpl;

public class Parser {
	//Extract at most #limit number of codes in each page
	public static List<Answer> scrape(String url,  String answerSelector, String codeSelector, String voteSelector, int limit) throws IOException {
		List<Answer> list = scrape(url,  answerSelector, codeSelector, voteSelector);
		limit = Math.min(limit, list.size());
		return list.subList(0, limit);
	}
	
	//Extract answers from a given url. Take a URL and 3 selectors and return a list of Answers
	public static List<Answer> scrape(String url, String answerSelector, String codeSelector, String voteSelector ) throws IOException {

		
		List<Answer> answerList = new ArrayList<Answer>();
		// Load Document from a URL
		Document doc = Jsoup.connect(url).get();
		//Get the answer elements
		Elements answers = doc.select(answerSelector);
		for (Element answer: answers) {
			Element vote = answer.select(voteSelector).first();
			Elements codes = answer.select(codeSelector);
			for (Element code: codes){
				//One piece of code text in this answer block 
				String codeText = code.text();
				codeText = clearCode(codeText);
				Answer votedCode = new Answer (codeText, vote.text()); 
				answerList.add(votedCode);
			}
					
		}	
		return answerList;
	}

	// Test it with the example on http://jsoup.org/
	
	public static void main(String[] args) throws IOException {
		String answerSelector = "div.answer"; 
		String codeSelector = "code"; 
		String voteSelector = "span.vote-count-post";
		String url = "http://superuser.com/questions/71087/how-to-check-the-current-disk-space-used-by-my-linux";
		List<Answer> answerList = scrape(url,  answerSelector, codeSelector, voteSelector, 10);
		for (Answer answer: answerList){
			answer.print();
			
		}
	}
	
	//Check if there already exists similar code in the list of answers, given a threshold value of similarity 
	public static boolean haveSimilarCode(Map<String, List<Answer>> lists, Answer answer, double threshold){
		for (List<Answer> answers: lists.values()){
			for (Answer existAnswer: answers){
				if (similarAnswer(existAnswer, answer, threshold)) return true;
			}
		}
		return false;
	}	
	
	
	//Check if two Answers are similar
	public static boolean similarAnswer(Answer target, Answer source, double threshold){
		return similarString(target.getCode(), source.getCode(), threshold);
	}
	
	//Check if the similarity value of two strings is higher than threshold
	public static boolean similarString(String target, String source, double threshold){
		
		SimilarityStrategy strategy = new JaroWinklerStrategy();
		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
		double score = service.score(source, target);
		return score >= threshold;
	}
	
	//Clear up "#, $" stuff from the code
	public static String clearCode(String codeText){
		if (codeText.startsWith("# "))
			codeText = codeText.substring(2);
		if (codeText.startsWith("$ "))
			codeText = codeText.substring(2);
		if (codeText.startsWith("sudo "))
			codeText= codeText.substring(5);
		return codeText;
	}
		
	
	
		
	

}