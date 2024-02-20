package edu.kh.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex1")
public class PizzaOrder extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pizza = req.getParameter("pizza");
		String type = req.getParameter("type");
		String orderName = req.getParameter("orderName");
		String orderAdress = req.getParameter("orderAdress");
		String [] opts = req.getParameterValues("opt");
		
		int price = 0;
		
		switch(pizza) {
		case "페퍼로니" : price += 15000; break;
		case "쉬림프" : price += 18000; break;
		case "포테이토" : price += 17000; break;
		case "불고기" : price += 16000; break;
		case "고구마" : price += 14000; break;
		}
		
		if(type.equals("cheese")) price += 1500;
		
		
		for(String opt : opts) {
			
			switch(opt) {
			case "피클" : 
			case "핫소스" : price += 500;
			case "디핑소스" : price += 800;
			case "콜라" : price += 1500;
			}
		}
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append(String.format("<h2>%s님의 영수증</h2>", orderName));
		sb.append("</head>");
		
		sb.append("<body>");
		sb.append(String.format("<h3>주문자명 : %s</h3>\n", orderName));
		sb.append(String.format("<h3>주소     : %s</h3>\n", orderAdress));
		
		sb.append("<ul>");
		sb.append(String.format("<li>주문한 피자 : %s</li>", pizza));
		
		String temp = type.equals("original") ? "오리지널" : "치즈크러스트";
		sb.append(String.format("<li>도우 : %s</li>", temp));
		
		if(opts != null) {
			sb.append("<li>");
			sb.append("선택한 옵션 : ");
			for(String opt : opts) sb.append(opt + " ");
			sb.append("</li>");
		}
		sb.append("</ul>");
		
		sb.append(String.format("<h3>합계: %d </h3>", price));
		
		sb.append("</body>");
		sb.append("</html>");
		
		out.print(sb.toString());
		
		
	}
	
	
	
}
