package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.jaunt.*;
public class  WebParserFlipkart implements WebParser
{
	public static int MOBILE=1,SEARCH=0;
	private String url;
	private double price;
	private UserAgent userAgent;
	private Element tit;
	
	public Element getTit() {
		return tit;
	}
	
	public void setTit(Element tit) {
		this.tit = tit;
	}
	
	private int searchType;

	public WebParserFlipkart(String url){
		this.setUrl(url);
		
	}
	
	public WebParserFlipkart(String url,int type){
		this.setUrl(url);
		this.searchType=type;
		
	}
	
	public WebParserFlipkart(Element tit){
		this.tit=tit;
		
	}
	
	public UserAgent getUserAgent() {
		return userAgent;
	}
	
	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getPart(){
		return tit.getParent().innerHTML();
	}
	
	public void setUrl(String url){
		this.url=url;
	}

	public double getPrice() throws NotFound, ParseException{
		
		findOutPrice();
		
		return price;
	}
	
	public String getLinkToStore() throws NotFound{

		return tit.findFirst("<a>").getAt("href");
	}

	public String getImageLink() throws NotFound{
	
		Element img=tit.findFirst("img");
		String res="";
		if(searchType==MOBILE)
			res=img.getAt("src");
		else
			res=img.getAt("data-src");
		System.out.println("path to img="+res);
		return res; 
	}
	
	public static Elements start(String url,int searchType) throws ResponseException, NotFound{
		UserAgent userAgent = new UserAgent();
		Elements tit;
		if(searchType==MOBILE)
		{
			userAgent.visit("http://www.flipkart.com/mobiles");
			tit = userAgent.doc.findEvery("<div class=\" product-unit unit-4 browse-product \">");
		}
		else{
			String wrds[]=url.split("\\s+");
			String newurl="";
			for(String s:wrds)
				newurl+=s+"+";
			newurl=newurl.trim();
			newurl=newurl.substring(0, newurl.length()-1);
			System.out.println("http://www.flipkart.com/search?q="+newurl);
			
			userAgent.visit("http://www.flipkart.com/search?q="+newurl);
			tit = userAgent.doc.findEvery("<div class=\"product-unit unit-4 browse-product new-design \">");
		}
		return tit;
		
	}
	
	public boolean isOutOfStock() throws NotFound{
		return (tit.findFirst("<a class=\"pu-status oos fk-uppercase fk-font-11\")")!=null);
			
	}
	
	public void findOutPrice() throws NotFound, ParseException {

		Element price=tit.findFirst("<div class=\"pu-border-top\">").findFirst("<div class=\"pu-final\">");
		String pp=price.innerText().trim().substring(4);
		pp="$"+pp;
		this.price=NumberFormat.getCurrencyInstance(Locale.US).parse(pp).doubleValue();
		
	}
	
	public String getTitle() throws NotFound{
		return tit.findFirst("<div class=\"pu-details lastUnit\">").findFirst("<a>").getAt("title");
	}
	
	public static void main(String args[]) throws Exception{
		WebParserFlipkart parser=new WebParserFlipkart("computer networks by tanenbaum",WebParserFlipkart.MOBILE);
		parser.setTit(start("computer networks by tanenbaum",WebParserFlipkart.MOBILE));
		System.out.println(parser.getPrice());
		System.out.println(parser.getImageLink());
		System.out.println(" "+parser.getTitle()+" "+parser.getLinkToStore());
	}
	
}
