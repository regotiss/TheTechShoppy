package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.WebUtil;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int noOfWeb=2;		// number of websites referred for comparing products
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String url=request.getParameter("book");	//name of selected book or name of book entered in search box
				
				try {
					
					//List of results from Amazon
					
					List<ProductBean> listAmazon=WebUtil.getProductList(url,WebUtil.Amazon);
					request.setAttribute("ProductAmazon", listAmazon);
					
					
					//List of results from Flipkart
					
					List<ProductBean> listFlipkart=WebUtil.getProductList(url,WebUtil.Flipkart);
					request.setAttribute("ProductFlipkart", listFlipkart);

					//Finding Best result between amazon and flipkart
					
					ProductBean res[]=new ProductBean[noOfWeb];
					res[0]=listAmazon.get(0);
					res[1]=listFlipkart.get(0);
					
					String seq[]=new String[noOfWeb];
					double bpriceAmazon=res[0].getPrice();
					double bpriceFlipkart=res[1].getPrice();
				
					if(bpriceAmazon<bpriceFlipkart){
						
						seq[0]="amazon.png";
						seq[1]="flipkart.jpg";
					}
					else
					{
						seq[1]="amazon.png";
						seq[0]="flipkart.jpg";
						res[1]=listAmazon.get(0);
						res[0]=listFlipkart.get(0);
					}
					
					request.setAttribute("bestresult",res);
					request.setAttribute("seq", seq);
					
					//Redirecting to Result Page i.e. dispatching request to Result.jsp
					
					RequestDispatcher view=request.getRequestDispatcher("Result.jsp");
					view.forward(request,response);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
