package model;

import java.text.ParseException;

import com.jaunt.Element;
import com.jaunt.NotFound;

//Inteface declaring all methods that any webparser should implement
public interface WebParser {

	void setTit(Element tit);	//Set Element which refer to <div> tag which contain results 
	Element getTit();
	double getPrice() throws NotFound, ParseException;
	String getImageLink() throws NotFound;
	String getTitle() throws NotFound;
	boolean isOutOfStock() throws NotFound;
	String getLinkToStore() throws NotFound;
}
