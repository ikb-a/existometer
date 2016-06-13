package edu.toronto.cs.se.ci.playground;

import java.util.List;

import org.json.JSONObject;

import edu.toronto.cs.se.ci.Source;
import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.playground.sources.GMapsGeocode;
import edu.toronto.cs.se.ci.playground.sources.GoogleHits;
import edu.toronto.cs.se.ci.playground.sources.NameEmailOnGithub;
import edu.toronto.cs.se.ci.playground.sources.WhitePagesReverseAddress;
import edu.toronto.cs.se.ci.playground.sources.util.BingSearchJSON;
import edu.toronto.cs.se.ci.playground.sources.BingHits;

public class MuckingAbout {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws UnknownException {
		Address one = new Address("1550", "Nottinghill Gate", "Oakville", "ON", "Canada", "L6M 1S2");
		
		System.out.println(BingSearchJSON.search("I am the very model"));

		if (false == true) {
			Source<Address, List<String>, Void> white = new WhitePagesReverseAddress();
			try {
				System.out.println(white.getOpinion(one).getValue());
			} catch (UnknownException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}

		if (false == true) {
			Source<Contact, Boolean, ContactTrust> gitHub = new NameEmailOnGithub();
			Contact bob = new Contact("David Liu", "liudavid@cs.toronto.edu", one);
			try {
				System.out.println(gitHub.getOpinion(bob).getValue());
			} catch (UnknownException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

		// Google Search API No Longer Working
		if (false == true) {
			Source<String, Integer, Void> googleSearch = new GoogleHits();
			try {
				System.out.println(googleSearch.getOpinion("I am the very model of a modern major general").getValue());
			} catch (UnknownException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (false)

		{
			Source<String, Integer, Void> bing = new BingHits();
			try {
				System.out.println(bing.getOpinion("I am the very model of a modern major general").getValue());
			} catch (UnknownException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (false == true) {
			Source<Address, JSONObject, Void> geoCode = new GMapsGeocode();
			try {
				Opinion<JSONObject, Void> resultOne = geoCode.getOpinion(one);
				System.out.println(resultOne.getValue());
			} catch (UnknownException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
