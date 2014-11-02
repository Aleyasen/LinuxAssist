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
    	return ok(search.render());
    }

    public static Result results(String query) throws IOException {
		
		System.out.println("Searching for \"" + query + "\"");
		Map<String, List<String>> lists = Search.search(query, sources);

		Search.printLists(lists);

		return ok(results.render(query, lists));
    }
}
