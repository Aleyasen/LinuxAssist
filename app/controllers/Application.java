package controllers;

import play.*;
import play.mvc.*;

import java.io.*;
import java.util.*;
import model.*;
import views.html.*;

public class Application extends Controller {
	private static final List<Source> sources = Source.ReadSourcesFromFile("others/sources.txt");

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }


    public static Result search() {
        if (false) System.exit(0);

    	return ok(search.render());
    }

    public static Result results(String query) throws IOException {
		
		System.out.println("Searching for \"" + query + "\"");
		Map<String, List<Answer>> lists = Search.search(query, sources, 0.5);

		List<Answer> all = new ArrayList<Answer>();
        int total = 0;

        for (List<Answer> sub : lists.values()) {
            all.addAll(sub);

            total += sub.size();
        }        

        Collections.sort(all);

        System.out.println("Found: " + total + " results.");

		return ok(results.render(query, all));
    }
}
