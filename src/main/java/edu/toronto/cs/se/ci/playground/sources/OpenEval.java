package edu.toronto.cs.se.ci.playground.sources;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.playground.sources.util.BingSearchJSON;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class OpenEval extends BasicSource<List<String>, Boolean, Double> {

	public static void main(String[] args) throws UnknownException {
		JSONObject test = new JSONObject(
				"{\"d\":{\"results\":[{\"WebOffset\":\"0\",\"Web\":[{\"DisplayUrl\":\"dilbert.com\",\"Description\":\"Official site of the Dilbert comic strip featuring Scott Adam's daily cartoon. Also includes Dogbert's anti-career zone, a one-month Dilbert archive, the Dilbert ...\",\"Title\":\"Homepage | Dilbert by Scott Adams\",\"ID\":\"24c95eab-c037-4fae-90d4-6ffb146f43ac\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=0&$top=1\"},\"Url\":\"http://dilbert.com/\"},{\"DisplayUrl\":\"https://en.wikipedia.org/wiki/Dilbert\",\"Description\":\"Dilbert is an American comic strip written and illustrated by Scott Adams, first published on April 16, 1989. The strip is known for its satirical office humor about ...\",\"Title\":\"Dilbert - Wikipedia, the free encyclopedia\",\"ID\":\"f5437a96-809f-479e-accc-1e5be107016c\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=1&$top=1\"},\"Url\":\"https://en.wikipedia.org/wiki/Dilbert\"},{\"DisplayUrl\":\"www.imdb.com/title/tt0118984\",\"Description\":\"With Daniel Stern, Larry Miller, Gordon Hunt, Chris Elliott. Cubicle denizen Dilbert toils away at Path-E-Tech which makes undefined products. The focus is on his ...\",\"Title\":\"Dilbert (TV Series 1999\\u20132000) - IMDb\",\"ID\":\"5864d8d9-d69c-49cd-bea3-efe52ea49073\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=2&$top=1\"},\"Url\":\"http://www.imdb.com/title/tt0118984/\"},{\"DisplayUrl\":\"www.arcamax.com/thefunnies/dilbert\",\"Description\":\"Created by Scott Adams, Dilbert is about the world's most famous -- and funny -- dysfunctional office.\",\"Title\":\"Dilbert | Comics | ArcaMax Publishing\",\"ID\":\"da256e73-127b-4ff4-ba22-4bbba49b5417\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=3&$top=1\"},\"Url\":\"http://www.arcamax.com/thefunnies/dilbert/\"},{\"DisplayUrl\":\"https://www.facebook.com/dilbert\",\"Description\":\"Dilbert. 158,031 likes \u00B7 4,991 talking about this. The Official Dilbert Facebook Page Posts by creator Scott Adams are signed \\\"Scott\\\". http://dilbert.com\",\"Title\":\"Dilbert - Facebook\",\"ID\":\"4d158679-c446-40de-b863-377465404a38\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=4&$top=1\"},\"Url\":\"https://www.facebook.com/dilbert\"},{\"DisplayUrl\":\"www.youtube.com/user/dilbert\",\"Description\":\"He's alive! Creator Scott Adams' world famous comic strip cubicle dweller Dilbert now walks and talks and sometimes gets the last line in a new animated vers...\",\"Title\":\"Dilbert - YouTube\",\"ID\":\"0f24ed68-6c0a-4e3e-ae33-1be92b142a02\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=5&$top=1\"},\"Url\":\"http://www.youtube.com/user/dilbert\"},{\"DisplayUrl\":\"www.tv.com/shows/dilbert\",\"Description\":\"Dilbert is a cubicle dwelling employee for a large soulless corporation. Each episode the show goes on many adventures that will leave you waiting for more.\",\"Title\":\"Dilbert - TV.com\",\"ID\":\"df00999f-cfe8-4609-b3ff-272c1fe5c8bd\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=6&$top=1\"},\"Url\":\"http://www.tv.com/shows/dilbert/\"},{\"DisplayUrl\":\"dilbert.com/search\",\"Description\":\"The Official Dilbert Website featuring Scott Adams Dilbert strips, animation, mashups and more starring Dilbert, Dogbert, Wally, The Pointy Haired Boss, Alice, Asok ...\",\"Title\":\"Search | Dilbert by Scott Adams\",\"ID\":\"45b623a9-fcc0-4d5b-93d3-d4253095f83e\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=7&$top=1\"},\"Url\":\"http://dilbert.com/search\"},{\"DisplayUrl\":\"blog.dilbert.com\",\"Description\":\"Dilbert by Jake Tapper? Posted May 23rd, 2016 @ 2:19am in #jake tapper #veterans. If you\\u2019re wondering why Dilbert looks different today, it\\u2019s because I asked CNN ...\",\"Title\":\"Scott Adams' Blog\",\"ID\":\"b78a39f7-2409-4692-b4fc-51aae188cdbc\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=8&$top=1\"},\"Url\":\"http://blog.dilbert.com/\"},{\"DisplayUrl\":\"https://en.wikipedia.org/wiki/Dilbert_(character)\",\"Description\":\"Dilbert is a fictional character and the main character and protagonist of the Dilbert comic strip. He is a white collar office worker who has a rare medical ...\",\"Title\":\"Dilbert (character) - Wikipedia, the free encyclopedia\",\"ID\":\"dbe40982-0924-4f2b-a4fe-dccb495d68b9\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=9&$top=1\"},\"Url\":\"https://en.wikipedia.org/wiki/Dilbert_(character)\"},{\"DisplayUrl\":\"www.gocomics.com/dilbert-classics\",\"Description\":\"Welcome to GoComics.com, the world's largest comic strip site for online classic strips like Calvin and Hobbes, Dilbert, Non Sequitur, Get Fuzzy, Luann, Pearl Before ...\",\"Title\":\"Dilbert Classics Comic Strip on GoComics.com\",\"ID\":\"958f1e03-2646-4bc7-b6cb-1f196f63cd89\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=10&$top=1\"},\"Url\":\"http://www.gocomics.com/dilbert-classics\"},{\"DisplayUrl\":\"blog.dilbert.com/about\",\"Description\":\"Dilbert. Dilbert launched in 1989 in a handful of newspapers. Now Dilbert appears in over 2,000 newspapers, in 57 countries, and in 19 languages\",\"Title\":\"About Scott Adams | Scott Adams Blog\",\"ID\":\"4fc4259b-2dbb-40c1-82d4-69de7c124024\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=11&$top=1\"},\"Url\":\"http://blog.dilbert.com/about\"},{\"DisplayUrl\":\"www.universaluclick.com/comics/dilbert\",\"Description\":\"Available for print / Web / mobile . Dilbert by Scott Adams is the most photocopied, pinned-up, downloaded, faxed and e-mailed comic strip in the world.\",\"Title\":\"Dilbert - Universal Uclick - Home\",\"ID\":\"eda75c5d-2ca6-426e-ac9b-dd5790e8d883\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=12&$top=1\"},\"Url\":\"http://www.universaluclick.com/comics/dilbert/\"},{\"DisplayUrl\":\"www.chron.com/entertainment/comics-games/comic/Dilbert\",\"Description\":\"Dilbert. May. Su; Mo; Tu; We; Th; Fr; Sa; 1; 2; 3; 4; 5; 6; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 17; 18; 19; 20; 21; 22; 23; 24; 25; 26; 27; 28; 29; 30; 31 ; June. Su ...\",\"Title\":\"Dilbert - Houston Chronicle\",\"ID\":\"c73b0c42-fdf6-4379-acb1-45d85ecef28c\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=13&$top=1\"},\"Url\":\"http://www.chron.com/entertainment/comics-games/comic/Dilbert/\"},{\"DisplayUrl\":\"www.uclick.com/client/wpc/dt\",\"Description\":\"Comics - Washington Post comics, Tom Toles, Cul de Sac and editorial cartoons. Web comics including Archie, Family Circus, Marmaduke, Over the Hedge, Soup to Nutz ...\",\"Title\":\"Dilbert - Universal Uclick\",\"ID\":\"d628e325-20af-4380-81ea-9b7957a4e180\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=14&$top=1\"},\"Url\":\"http://www.uclick.com/client/wpc/dt/\"},{\"DisplayUrl\":\"https://en.wikiquote.org/wiki/Dilbert\",\"Description\":\"Dilbert is a series of comic strips, drawn by Scott Adams. They often depict an exaggeration of life in the business world. Briefly expanded into television with a ...\",\"Title\":\"Dilbert \\u2013 Wikiquote\",\"ID\":\"b99e1840-39b5-4814-8488-f0b87eab9798\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=15&$top=1\"},\"Url\":\"https://en.wikiquote.org/wiki/Dilbert\"},{\"DisplayUrl\":\"www.bfmartin.ca/finder\",\"Description\":\"Text-based search engine offering keyword searching of the Dilbert archives. Results include date published and pertinent collection.\",\"Title\":\"Dilbert Strip Finder: BFMartin.ca\",\"ID\":\"bdbad22c-b7b5-444b-ba0e-3a60881c807a\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=16&$top=1\"},\"Url\":\"http://www.bfmartin.ca/finder/\"},{\"DisplayUrl\":\"https://coronalabs.com/dilbert\",\"Description\":\"May 14 to July 12, 2013Yes, this is the opportunity you have been waiting for, the ability to create a game with the world famous cartoon engineer, Dilbert.You will get\",\"Title\":\"Dilbert | Corona Labs!\",\"ID\":\"1fc7f5fe-9e74-499c-9106-4a1bcc8b5575\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=17&$top=1\"},\"Url\":\"https://coronalabs.com/dilbert/\"},{\"DisplayUrl\":\"www.montrealgazette.com/life/diversions/comics/dilbert.html\",\"Description\":\"Find latest lifestyle information on fashion, relationships, family, food, outdoors and pets. Read Montreal Gazette to get more information on lifestyles.\",\"Title\":\"Life - Diversions - Comics - Dilbert - The Gazette\",\"ID\":\"4a45062b-2a1c-4b28-94e6-c0f6b9c53a1d\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=18&$top=1\"},\"Url\":\"http://www.montrealgazette.com/life/diversions/comics/dilbert.html\"},{\"DisplayUrl\":\"www.businessinsider.com/funniest-dilbert-comics-on-idiot-bosses...\",\"Description\":\"For National Boss Day, Dilbert creator Scott Adams shares his favorite Pointy-Haired Boss comic strips.\",\"Title\":\"Funniest Dilbert Comics On Idiot Bosses - Business Insider\",\"ID\":\"c5b614f4-ff0d-4165-ac96-18a4b5dadafa\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=19&$top=1\"},\"Url\":\"http://www.businessinsider.com/funniest-dilbert-comics-on-idiot-bosses-2014-10\"},{\"DisplayUrl\":\"feed.dilbert.com/dilbert/blog\",\"Description\":\"I realize a number of my blog readers don\\u2019t think climate change is a problem. Hold that objection until the end. \\u2014 Let\\u2019s say you think climate change is the ...\",\"Title\":\"Scott Adams Blog - Dilbert\",\"ID\":\"22ab14a6-ae5c-489b-af42-64167988b6e0\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=20&$top=1\"},\"Url\":\"http://feed.dilbert.com/dilbert/blog\"},{\"DisplayUrl\":\"https://thedilbertstore.com\",\"Description\":\"Item Price Qty Item Total; There currently are no items added to your shopping cart. Subtotal: $0.00 *Estimated Order Total (does not include sales tax):\",\"Title\":\"Shopping Cart - The Official Dilbert Store\",\"ID\":\"52dcc297-bc67-483e-b4cc-dae80a834690\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=21&$top=1\"},\"Url\":\"https://thedilbertstore.com/\"},{\"DisplayUrl\":\"www.youtube.com/watch?v=g8vHhgh6oM0\",\"Description\":\"\\\"The Knack\\\" clip from Dilbert S01E09: Dilbert is diagnosed with \\\"The Knack\\\" at a young age and is destined to become an engineer! Oh no\",\"Title\":\"Dilbert - The Knack \\\"The Curse of the Engineer\\\" - YouTube\",\"ID\":\"23aac212-8781-483b-b2db-183b9fb44c3e\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=22&$top=1\"},\"Url\":\"http://www.youtube.com/watch?v=g8vHhgh6oM0\"},{\"DisplayUrl\":\"www.salon.com/2016/06/08/no_joke_dilbert_creator_scott_adams_just...\",\"Description\":\"Scott Adams, the creator of \\u201cDilbert,\\u201d wants you to know he does not love Donald Trump. Sure, he probably sleeps with Trump\\u2019s picture under his ...\",\"Title\":\"Dilbert has gone fascist: The strange unrequited love ...\",\"ID\":\"ebcb39e4-8204-4382-9dae-14b77bf37cbc\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=23&$top=1\"},\"Url\":\"http://www.salon.com/2016/06/08/no_joke_dilbert_creator_scott_adams_just_loves_donald_trump_so_why_cant_he_just_admit_it/\"},{\"DisplayUrl\":\"dilbertblog.typepad.com\",\"Description\":\"Dilbert humor, business absurdity, the meaning of life\",\"Title\":\"The Dilbert Blog\",\"ID\":\"9c97715e-0f38-4518-8b4e-63c68116ecbb\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=24&$top=1\"},\"Url\":\"http://dilbertblog.typepad.com/\"},{\"DisplayUrl\":\"www.cs.vu.nl/~frankh/dilbert.html\",\"Description\":\"Favourite Daily Dilbert's I get a daily Dilbert cartoon by email (thanks to Dick de Ridder for organising the feed from the Dilbert site) Here I collect the Dilbert ...\",\"Title\":\"Favourite Daily Dilbert's\",\"ID\":\"a6d59b9d-318a-40bd-9da0-2efdf33244d7\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=25&$top=1\"},\"Url\":\"http://www.cs.vu.nl/~frankh/dilbert.html\"},{\"DisplayUrl\":\"tvtropes.org/pmwiki/pmwiki.php/ComicStrip/Dilbert\",\"Description\":\"Scott Adams' cult newspaper comic about Dilbert, an engineer cog in a soulless and bureaucratic corporate machine. The strip is principally a Satire of the \\u2026\",\"Title\":\"Dilbert (Comic Strip) - TV Tropes\",\"ID\":\"51bc1168-7df9-4892-b1e0-8cb8f4038e76\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=26&$top=1\"},\"Url\":\"http://tvtropes.org/pmwiki/pmwiki.php/ComicStrip/Dilbert\"},{\"DisplayUrl\":\"www.tv.com/shows/dilbert/watch\",\"Description\":\"Watch Dilbert Online: Watch full length episodes, video clips, highlights and more.\",\"Title\":\"Watch Dilbert Online - TV.com\",\"ID\":\"5745bfeb-c88c-42e7-aba4-e4c844bd04b2\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=27&$top=1\"},\"Url\":\"http://www.tv.com/shows/dilbert/watch/\"},{\"DisplayUrl\":\"https://twitter.com/DailyDilbert\",\"Description\":\"2,525 tweets \\u2022 0 photos/videos \\u2022 53.4K followers. Check out the latest Tweets from Dilbert (@DailyDilbert)\",\"Title\":\"Dilbert (@DailyDilbert) | Twitter\",\"ID\":\"a022eaac-c1f8-4e38-a8a1-6a10c2077e83\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=28&$top=1\"},\"Url\":\"https://twitter.com/DailyDilbert\"},{\"DisplayUrl\":\"https://www.quora.com/What-are-the-best-most-humorous-Dilbert-cartoons\",\"Description\":\"Dilbert never fails to lighten your mood. Here's my favourite (I had also written a post depicting the best digs he has taken at startups and VCs, my field of ...\",\"Title\":\"What are the best/most humorous Dilbert cartoons? - Quora\",\"ID\":\"6678c48a-e744-4b9a-a3fb-94ae01794a54\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=29&$top=1\"},\"Url\":\"https://www.quora.com/What-are-the-best-most-humorous-Dilbert-cartoons\"},{\"DisplayUrl\":\"www.edmontonjournal.com/life/diversions/comics/dilbert.html\",\"Description\":\"Find the latest lifestyle news and learn what it takes to have a healthy body, mind and spirit. Find expert advice on relationships, dating and family.\",\"Title\":\"Life - Diversions - Comics - Dilbert - Edmonton Journal\",\"ID\":\"dee14f38-b844-4b47-8b88-3852b8e104aa\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=30&$top=1\"},\"Url\":\"http://www.edmontonjournal.com/life/diversions/comics/dilbert.html\"},{\"DisplayUrl\":\"www.crackle.com/dilbert#!\",\"Description\":\"Dilbert continues having the egg dream, and learns about another guy with that dream who turned into a chicken. Dilbert works on a project named Acorn, but Dilmom ...\",\"Title\":\"Watch Dilbert, Season 1, Episode 1 Online Free - Crackle\",\"ID\":\"075bfae1-c3cd-4113-a3a9-9dc8a6389afc\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=31&$top=1\"},\"Url\":\"http://www.crackle.com/dilbert#!\"},{\"DisplayUrl\":\"www.businessinsider.com/scott-adams-favorite-dilbert-comics-2013-10\",\"Description\":\"Dilbert, the well-known comic strip by cartoonist Scott Adams about the office everyman and his crew of incompetent colleagues, was the first syndicated ...\",\"Title\":\"Scott Adams\\u2019 Favorite Dilbert Comics - Business Insider\",\"ID\":\"c9e208e3-5365-4b4d-8223-22b6ba30b067\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=32&$top=1\"},\"Url\":\"http://www.businessinsider.com/scott-adams-favorite-dilbert-comics-2013-10\"},{\"DisplayUrl\":\"https://twitter.com/Dilbert_Daily\",\"Description\":\"The latest Tweets from Scott Adams (@Dilbert_Daily). Cartoonist and creator Scott Adams\",\"Title\":\"Scott Adams (@Dilbert_Daily) | Twitter\",\"ID\":\"36a7919c-401c-4313-97ae-05278d4a2975\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=33&$top=1\"},\"Url\":\"https://twitter.com/Dilbert_Daily\"},{\"DisplayUrl\":\"www.calgaryherald.com/life/diversions/comics/dilbert.html\",\"Description\":\"All the rage fashion, style, shopping, clothing, accessories from the Calgary Herald\",\"Title\":\"Life - Diversions - Comics - Dilbert - Calgary Herald\",\"ID\":\"4e07fdd0-c78c-4782-aa34-933ccba94f5e\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=34&$top=1\"},\"Url\":\"http://www.calgaryherald.com/life/diversions/comics/dilbert.html\"},{\"DisplayUrl\":\"kisscartoon.me/Cartoon/Dilbert\",\"Description\":\"Watch online and download Dilbert cartoon in high quality. Various formats from 240p to 720p HD (or even 1080p). HTML5 available for mobile devices\",\"Title\":\"Dilbert cartoon | Watch Dilbert cartoon online in high quality\",\"ID\":\"4d1fbc69-096f-4140-839c-8df39c32beb2\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=35&$top=1\"},\"Url\":\"http://kisscartoon.me/Cartoon/Dilbert\"},{\"DisplayUrl\":\"https://www.pinterest.com/stephendale/dilbert\",\"Description\":\"Selected Dilbert Cartoons - usually (but not always) related to KM. | See more about Scott Adams, Comic Strips and Boss.\",\"Title\":\"Dilbert on Pinterest | Scott Adams, Comic Strips and Boss\",\"ID\":\"d3fec7fc-24b9-4b03-9a24-0cc060512d2e\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=36&$top=1\"},\"Url\":\"https://www.pinterest.com/stephendale/dilbert/\"},{\"DisplayUrl\":\"www.imdb.com/title/tt0118984/episodes\",\"Description\":\"Dilbert tries to get a birthday gift for his mother. In desperation, he goes to the mall. The mall holds bad memories for him since when he was a child his father ...\",\"Title\":\"Dilbert (TV Series 1999\\u20132000) - Episodes - IMDb\",\"ID\":\"e8bf2824-526d-4cd1-bc33-3e3b493eb704\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=37&$top=1\"},\"Url\":\"http://www.imdb.com/title/tt0118984/episodes\"},{\"DisplayUrl\":\"www.amazon.com/s?ie=UTF8&page=1&rh=i:aps,k:dilbert\",\"Description\":\"Available for Pre-order. This item will be released on November 1, 2016.\",\"Title\":\"Amazon.com: dilbert\",\"ID\":\"010f18c0-89cd-43a4-9a6c-ac87ead73267\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=38&$top=1\"},\"Url\":\"http://www.amazon.com/s?ie=UTF8&page=1&rh=i%3Aaps%2Ck%3Adilbert\"},{\"DisplayUrl\":\"homepage.eircom.net/~odyssey/Quotes/Popular/Comics/Dilbert.html\",\"Description\":\"DILBERT QUOTES. Dogbert: \\\"Women like men who boast about their accomplishments, but they hate men who boast. I will be your designated bragger, allowing you (Dilbert ...\",\"Title\":\"Dilbert Quotes - Eir\",\"ID\":\"e32261ec-8838-44e1-94d8-08b8bd737dc5\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=39&$top=1\"},\"Url\":\"http://homepage.eircom.net/~odyssey/Quotes/Popular/Comics/Dilbert.html\"},{\"DisplayUrl\":\"dilbert.wikia.com/wiki/Dilbert\",\"Description\":\"Dilbert is the main protagonist of the Dilbert comic strip and TV series. An engineer working as part of a cliche cubicle workforce in a fictional company, he is ...\",\"Title\":\"Dilbert - Dilbert Wiki - Wikia\",\"ID\":\"bde6c24a-8449-4e7e-add6-84de2c2ac86d\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=40&$top=1\"},\"Url\":\"http://dilbert.wikia.com/wiki/Dilbert\"},{\"DisplayUrl\":\"www.ottawacitizen.com/life/diversions/comics/dilbert.html\",\"Description\":\"Find latest lifestyle information on fashion, relationships, family, food, outdoors and pets. Read Ottawa Citizen to get more information on lifestyles.\",\"Title\":\"Life - Diversions - Comics - Dilbert - Ottawa Citizen\",\"ID\":\"b97642a0-f9bf-44b1-b64f-1e5959bfe033\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=41&$top=1\"},\"Url\":\"http://www.ottawacitizen.com/life/diversions/comics/dilbert.html\"},{\"DisplayUrl\":\"www.dilbertfiles.com\",\"Description\":\"Send large files to anyone with an email address. Its very fast and you can send video, email files and transfer music easier then ever!\",\"Title\":\"DilbertFiles.com | Send Large Files and Big File Transfer ...\",\"ID\":\"337faa8f-9982-4dba-9689-52ed16d33288\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=42&$top=1\"},\"Url\":\"http://www.dilbertfiles.com/\"},{\"DisplayUrl\":\"www.allgreatquotes.com/dilbert_quotes.shtml\",\"Description\":\"63% of all statistics are made up... including this one. Dilbert: All of your co-workers are fools. You must learn to pity and tolerate ...\",\"Title\":\"Dilbert Quotes, Famous Dilbert Quotes Quotations Dilbert ...\",\"ID\":\"de6a076e-8fc1-4480-a227-dd829d1d8d9c\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=43&$top=1\"},\"Url\":\"http://www.allgreatquotes.com/dilbert_quotes.shtml\"},{\"DisplayUrl\":\"www.eetimes.com/author.asp?section_id=36&doc_id=1285058\",\"Description\":\"What I love about Dilbert is the way it so closely parallels the way my employer works. And probably the way that most employers work. At least once every couple of ...\",\"Title\":\"Is this the best Dilbert cartoon ever? | EE Times\",\"ID\":\"3f7fbb28-7e5b-42c8-9128-2a0fa2ba011f\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=44&$top=1\"},\"Url\":\"http://www.eetimes.com/author.asp?section_id=36&doc_id=1285058\"},{\"DisplayUrl\":\"https://www.ideo.com/work/dilberts-ultimate-cubicle\",\"Description\":\"Since 1989, Scott Adams\\u2019s comic strip Dilbert has chronicled the life of his title character in the impersonal \\u201ccubicle farms\\u201d of the modern office.\",\"Title\":\"Dilbert\\u2019s Ultimate Cubicle | IDEO\",\"ID\":\"1dd260ff-8f95-4ca5-9528-714ce0df4393\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=45&$top=1\"},\"Url\":\"https://www.ideo.com/work/dilberts-ultimate-cubicle\"},{\"DisplayUrl\":\"www.dictionary.com/browse/dilbert\",\"Description\":\"Dilbert definition at Dictionary.com, a free online dictionary with pronunciation, synonyms and translation. Look it up now!\",\"Title\":\"Dilbert | Define Dilbert at Dictionary.com\",\"ID\":\"c3126499-5976-4151-94e7-3e6c9e3ba403\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=46&$top=1\"},\"Url\":\"http://www.dictionary.com/browse/dilbert\"},{\"DisplayUrl\":\"www.sidereel.com/tv-shows/dilbert\",\"Description\":\"Dilbert is an animated television series spin-off of the comic strip of the same name. The first episode was broadcast on January 25, 1999, making it UPN's highest ...\",\"Title\":\"Watch Dilbert Episodes Online | SideReel\",\"ID\":\"e5540cdf-fcfa-400b-be86-0ee71890722a\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=47&$top=1\"},\"Url\":\"http://www.sidereel.com/tv-shows/dilbert\"},{\"DisplayUrl\":\"www.computerweekly.com/dilbert\",\"Description\":\"Read the latest edition of Dilbert on ComputerWeekly.com. Dilbert cartoons are updated daily.\",\"Title\":\"Dilbert Daily Comic Strip &amp; Cartoons | ComputerWeekly.com\",\"ID\":\"09cdd874-07ac-4d67-b6fa-7c1148592052\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=48&$top=1\"},\"Url\":\"http://www.computerweekly.com/dilbert\"},{\"DisplayUrl\":\"https://kimmo.suominen.com/blog/2013/06/dilbert-rss\",\"Description\":\"Since yesterday the Dilbert RSS feed no longer includes an image element, so I revived my feed of long ago: https://kimmo.suominen.com/stuff/dilbert-daily.xml\",\"Title\":\"Dilbert RSS - Kimmo Suominen\",\"ID\":\"7bf149c1-3f3f-45ad-99ed-00c0d960b466\",\"__metadata\":{\"type\":\"WebResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/ExpandableSearchResultSet(guid'4a1750d6-5c8a-489d-a77e-6a759478f24e')/Web?$skip=49&$top=1\"},\"Url\":\"https://kimmo.suominen.com/blog/2013/06/dilbert-rss/\"}],\"WebTotal\":\"4320000\",\"AlteredQuery\":\"\",\"AlterationOverrideQuery\":\"\",\"ID\":\"4a1750d6-5c8a-489d-a77e-6a759478f24e\",\"__metadata\":{\"type\":\"ExpandableSearchResult\",\"uri\":\"https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Composite?Query='Dilbert'&$skip=0&$top=1\"}}]}}");
		//System.out.println(test);
		OpenEval bob = new OpenEval();
		List<String> urls = bob.getFirstNUrls(test, 10);
		System.out.println(urls);
	}

	private static final String JSON_URL_KEY = "Url";

	// add parameters so that it knows which webpages to check. Possible have
	// map of used keywords to articles read?
	private List<String> ExtractCBI(List<String> predicateInstance, String keyword) throws UnknownException {
		List<String> wordBags = new ArrayList<String>();
		String query = this.createQuery(predicateInstance, keyword);
		JSONObject searchResults = BingSearchJSON.search(query);

		return null;
	}

	private List<String> getFirstNUrls(JSONObject bingSearchResults, int n) {
		List<String> urls = new ArrayList<String>();
		try {
			JSONArray webResults = bingSearchResults.getJSONObject("d").getJSONArray("results").getJSONObject(0).getJSONArray("Web");
			int numToRead = Math.min(webResults.length(), n);
			for (int x = 0; x < numToRead; x++) {
				urls.add(webResults.getJSONObject(x).getString(JSON_URL_KEY));
			}

		} catch (JSONException e) {
			return new ArrayList<String>();
		}
		return urls;
	}

	/**
	 * Create the query searched for by the ExtractCBI method.
	 * 
	 * IDEA: use string format instead of simple concatenation?
	 * 
	 * @param predicateInstance
	 *            The input arguments to the predicate
	 * @param keyword
	 *            The keyword being used to augment the search
	 * @return A query created from the arguments in the predicateInstance and
	 *         the keyword.
	 */
	private String createQuery(List<String> predicateInstance, String keyword) {
		StringBuilder sb = new StringBuilder();
		for (String argument : predicateInstance) {
			sb.append(argument + " ");
		}
		sb.append(keyword);
		return sb.toString();
	}

	@Override
	public Boolean getResponse(List<String> input) throws UnknownException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expenditure[] getCost(List<String> args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTrust(List<String> args, Optional<Boolean> value) {
		// TODO Auto-generated method stub
		return null;
	}

}
