package edu.toronto.cs.se.ci.playground.sources;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.budget.basic.Dollars;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class WhitePagesReverseAddress extends BasicSource<Address, List<String>, Void> {

	@Override
	public List<String> getResponse(Address input) throws UnknownException {
		try {
			String street = input.getStreetNumber() + " " + input.getRoute();
			String where = input.getCity() + " " + input.getProvence();
			String url = "http://www.whitepages.ca/search/FindNearby?street=" + URLEncoder.encode(street, "UTF-8") + "&where=" + URLEncoder.encode(where, "UTF-8");
			Response response = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36")
					.timeout(12000)
					.followRedirects(true)
					.execute();
			
			Document doc = response.parse();
			
			Elements results = doc.select("#result_1 .basic_info a");
			
			List<String> names = new ArrayList<>();
			for (Element e : results) {
				names.add(e.html());
			}
			// source not working, needs to be fixed.
			throw new UnknownException();
			//return names;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Expenditure[] getCost(Address args) throws Exception {
		return new Expenditure[] {
			new Dollars(BigDecimal.valueOf(1, 1)), // 0.1 Dollars
			new Time(7, TimeUnit.SECONDS)
		};
	}

	@Override
	public Void getTrust(Address args, Optional<List<String>> value) {
		return null;
	}

}
