package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.HealthDTO;
import model.HealthDAO;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       String msg = "ユーザー情報一覧";
	       req.setCharacterEncoding("utf-8");
	       String btn = req.getParameter("btn");
	       HealthDAO hdao = new HealthDAO();
	       hdao.connect();
	       
	      try { 
	       if(btn.equals("登録")) {
	    	   String name = req.getParameter("name");
	    	   double height = Double.parseDouble(req.getParameter("height"));
	    	   double weight = Double.parseDouble(req.getParameter("weight"));
	    	   double h =height/100;
	    	   double bmi = Math.floor((weight/(h*h))*100)/100;
	    		hdao.insert(name, height, weight, bmi);
	    		msg = name+"さんの情報を登録しました";
	    		
	       }if(btn.equals("更新")) {
	    	   int id = Integer.parseInt(req.getParameter("ID"));
	    	   String name = req.getParameter("name");
	    	   double height = Double.parseDouble(req.getParameter("height"));
	    	   double weight = Double.parseDouble(req.getParameter("weight"));
	    	   double h =height/100;
	    	   double bmi =  Math.floor((weight/(h*h))*100)/100;
	    		int result = hdao.update(id,name, height, weight, bmi);
	    		if(result==0) {
		    		   msg = "ID:"+id+"のユーザーが見つかりませんでした";
		    	   }else {
	    		msg = "ID:"+id+"の"+name+"さんの情報を更新しました";
		    	   }
	       }else if(btn.equals("削除")) {
	    	   int id = Integer.parseInt(req.getParameter("ID"));
	    	   int result = hdao.delete(id);
	    	   if(result==0) {
	    		   msg = "ID:"+id+"のユーザーが見つかりませんでした";
	    	   }else {
	    	   msg = "ID:"+id+" のユーザーを削除しました。";
	    	   }
	       }else if(btn.equals("設定")) {
	    	   int id = Integer.parseInt(req.getParameter("ID"));
	    	   double target = Double.parseDouble(req.getParameter("target"));
	    	   int result =  hdao.calculation(id, target);
	    	   if(result==0) {
	    		   msg = "ID:"+id+"のユーザーが見つかりませんでした";
	    	   }else {
	    	   msg = "ID:"+id+" のユーザーの目標BMIを設定しました";
	    	   }
	    	}

	       HealthDTO hdto = hdao.select();
	       req.setAttribute("hdto",hdto);
	       req.setAttribute("msg",msg);
	       
	       RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
	       rd.forward(req, resp);
	      }catch(NumberFormatException e) {
	    	  msg = "入力された値が不正です。値を正しく入力してください。";
	          req.setAttribute("msg", msg);

	          RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
	          rd.forward(req, resp);
	      }
	      
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/first.jsp");
		ds.forward(request, response);
	}
}
