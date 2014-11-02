package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	public static List<String> scrape(String url, String selector, int limit) throws IOException {
		List<String> list = scrape(url, selector);
		limit = Math.min(limit, list.size());
		return list.subList(0, limit);
	}

	public static List<String> scrape(String url, String selector) throws IOException {

		List<Code> codeList = new ArrayList<Code>();

		List<String> list = new ArrayList<String>();
		// Load Document from a URL
		Document doc = Jsoup.connect(url).get();
		// Extract all elements according to the selector

		Elements ele = doc.select(selector);

		// Append text of every element to the list
		for (Element element : ele) {
			String text = element.ownText();
			if (text.startsWith("# "))
				text = text.substring(2);
			if (text.startsWith("$ "))
				text = text.substring(2);
			if (text.startsWith("sudo "))
				text = text.substring(5);

			// Delete the repeating codes
			if (!list.contains(text))
				list.add(text);
		}
		// return text/html of all elements that are true for the selector
		return list;
	}

	// Test it with the example on http://jsoup.org/
	public static void main(String[] args) throws IOException {
		List<String> list = scrape("http://www.computerhope.com/unix/ucp.htm", "pre.tcy");
		System.out.print(list);
	}

}