package search;

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

		List<Source> sources = Source.ReadSourcesFromFile("sources.txt");

		// String query =
		// "find total disk space used personally by the current user";
		List<Query> queries = Query.ReadFromFile("queries.txt");
		System.out.println("queries#: " + queries.size());
		for (Query q : queries) {
			System.out.println(q.getId() + " <> " + q.getText() + " ########################################");
			Map<String, List<String>> lists = search(q.getText(), sources);
			System.out.println();
			printLists(lists);
			System.out.println();
		}

	}

	private static Map<String, List<String>> search(String query, List<Source> sources) throws IOException {
		// A hashMap representing the votes and commands
		HashMap<Integer, String> codeMap = new HashMap<Integer, String>();

		Map<String, List<String>> lists = new HashMap<String, List<String>>();

		for (Source src : sources) {
			lists.put(src.getUrl(), new ArrayList<String>());
		}
		System.out.println("Sources# : " + sources.size());

		String submittedQuery = "java " + query;
		System.out.println("Submitted Query = " + submittedQuery);
		List<String> urls = SearchHelper.search(submittedQuery, 10);

		for (String url : urls) {
			Source src = findSource(url, sources);
			if (src != null) {
				System.out.println(src.getUrl() + " <> " + url);
				List<String> codes = Parser.scrape(url, src.getCodeSelector(), 3);
				// change here to deduplicate the codes
				/**
				 * for (String code : codes){ if (lists.containsValue())
				 * lists.get(src.getUrl()). }
				 **/
				// what I changed
				lists.get(src.getUrl()).addAll(codes);
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

	public static void printLists(Map<String, List<String>> lists) {
		for (String key : lists.keySet()) {
			System.out.println(key);
			int index = 1;
			for (String val : lists.get(key)) {
				System.out.println("\t" + index + ") " + val);
				index++;
			}
		}
	}

}
