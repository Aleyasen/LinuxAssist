package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import parser and searchHelper
public class Search {

	/**
	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Set a threshold for similarity checking. 
		//When the similarity value of 2 strings is higher than the threshold, the two strings are thought to be "similar"
		
		double threshold = 0.5;
		List<Source> sources = Source.ReadSourcesFromFile("sources.txt");
		
		System.out.println("-------------------Source Summery--------------------\n");
		for (Source source: sources){
			source.print(); System.out.println();
		}
		System.out.println("-------------------Source Summery Ends--------------------\n");
		
		List<Query> queries = Query.ReadFromFile("queries.txt");
		System.out.println("queries#: " + queries.size());
		for (Query q : queries) {
			System.out.println(q.getId() + " <> " + q.getText() + " ########################################");
			
			//Here it needs to pass a threshold into search function
			
			Map<String, List<Answer>> lists = search(q.getText(), sources, threshold);
			System.out.println();
			printLists(lists);
			System.out.println();
		}

	}
	
	//Return a Map lists, mapping each URL to its list of answers.
	//Parameters: query String, list of sources, threshold
	public static Map<String, List<Answer>> search(String query, List<Source> sources, double threshold) throws IOException {
		Map<String, List<Answer>> lists = new HashMap<String, List<Answer>>();
		for (Source src : sources) {
			lists.put(src.getUrl(), new ArrayList<Answer>());
		}
		System.out.println("Sources# : " + sources.size());
		String submittedQuery = "linux " + query;
		System.out.println("Submitted Query = " + submittedQuery);
		List<String> urls = SearchHelper.search(submittedQuery, 10);

		for (String url : urls) {
			Source src = findSource(url, sources);
			if (src != null) {
				System.out.println(src.getUrl() + " <> " + url);
				List<Answer> answers = Parser.scrape(url, src.getAnswerSelector(), src.getCodeSelector(), src.getVoteSelector(), 3);
				
				// change here to delete similar codes
				for (Answer answer: answers){
					if (!Parser.haveSimilarCode(lists, answer, threshold)){
						 lists.get(src.getUrl()).add(answer);
					}
				}
				// what I changed
			} else {
				System.out.println("NOSOURCE <> " + url);
			}
		}
		return lists;
	}

	public static Source findSource(String url, List<Source> sources) {
		for (Source src : sources) {
			if (url.contains(src.getUrl()))
				return src;
		}
		return null;
	}
	
	//Print the URLs and corresponding answers in the map lists
	public static void printLists(Map<String, List<Answer>> lists) {
		for (String key : lists.keySet()) {
			System.out.println(key);
			int index = 1;
			for (Answer answer : lists.get(key)) {
				System.out.print("\t" + index + ") ");
				answer.print();
				index++;
			}
		}
	}

}
