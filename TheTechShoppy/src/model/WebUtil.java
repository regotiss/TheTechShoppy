package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

public class WebUtil {

	public static int Flipkart=0,Amazon=1;
	public static void main(String[] args) throws NotFound, ResponseException, ParseException {
		List<ProductBean> list=getProductList(null,WebUtil.Amazon,WebParserFlipkart.MOBILE);
		System.out.println(list.size());

	}
	public static List<ProductBean> getProductList(String url,int website,int searchType) throws ResponseException, NotFound, ParseException{
		
		List<ProductBean>  list=new ArrayList<>();
		Elements tit;		
		
		WebParser parser;
		if(website==Flipkart){
			parser=new WebParserFlipkart(url,searchType);
			tit=WebParserFlipkart.start(url, searchType);
		}
		else{ 
			parser=new WebParserAmazon();
			tit=WebParserAmazon.start(url, searchType);
		}
			
		for(Element div:tit){
			try {
				parser.setTit(div);
				//getting one by one book details from list of results
				{
					ProductBean found=new ProductBean(parser.getPrice(),parser.getImageLink(),parser.getTitle(),parser.getLinkToStore());
					list.add(found);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("size:"+list.size());
		return list;
	}
	public static List<ProductBean> getProductList(String url,int website) throws NotFound, ResponseException, ParseException {
		
		return getProductList(url,website,WebParserFlipkart.SEARCH);
	}

}
