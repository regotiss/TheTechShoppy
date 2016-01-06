package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class WebParserAmazon implements WebParser{
	
	private Element tit;
	private double price;
	
	
	
	public static Elements start(String url,int searchType) throws ResponseException, NotFound{
		UserAgent userAgent = new UserAgent(); //creates the headless browser
		Elements tit;
		
		String wrds[]=url.split("\\s+");
		String newurl="";
		for(String s:wrds)
			newurl+=s+"+";
		newurl=newurl.trim();
		newurl=newurl.substring(0, newurl.length()-1);
			
		userAgent.visit("http://www.amazon.in/s/ref=nb_sb_noss?field-keywords="+newurl);     //visits the webpage of provided url
		
		tit = userAgent.doc.findEach("<div class=\"a-fixed-left-grid\">");       
		
		return tit;
				
	}
	
	//returns link of image of a required book
	public String getImageLink() throws NotFound{
		
		System.out.println("found:"+tit.findFirst("<img>").getAt("alt"));
		return tit.findFirst("<img>").getAt("src");	
		
	}
	
	//returns title of a required book
	public String getTitle() throws NotFound{
		return tit.findFirst("<div class=\"a-fixed-left-grid-col a-col-right\"").findFirst("<a>").getAt("title");
	}
	
	//returns link of page from which we order of a required book
	public String getLinkToStore() throws NotFound{

		return tit.findFirst("<a>").getAt("href");
	}
	
	//find price of book and converting into required format
	public void findOutPrice() throws NotFound, ParseException {

		Elements allDiv=tit.findFirst("<div class=\"a-fixed-left-grid-col a-col-right\">").findEach("<div class=\"a-row a-spacing-none\">");
		Element sec=allDiv.getElement(2).findFirst("<span>");
		
	
		String pp=sec.getText().trim();
		pp="$"+pp;
		this.price=NumberFormat.getCurrencyInstance(Locale.US).parse(pp).doubleValue();
		
	}
	public double getPrice() throws NotFound, ParseException{
		
		findOutPrice();
		return price;
	}

	public Element getTit() {
		return tit;
	}
	public void setTit(Element tit) {
		this.tit = tit;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean isOutOfStock() throws NotFound {
		return false;
	}
	
	public static void main(String args[]) throws NotFound, ResponseException, ParseException{
		
		WebParserAmazon parser=new WebParserAmazon();
		
		Elements tit=start("computer networks by tanenbaum",0);
		System.out.println("size amazon :"+tit.size());
		parser.setTit(tit.getElement(0));
		System.out.println("Link to store:"+parser.getLinkToStore());
		System.out.println("Price:"+parser.getPrice());
		System.out.println("image link:"+parser.getImageLink());
		System.out.println("title:"+parser.getTitle());
	}

}
