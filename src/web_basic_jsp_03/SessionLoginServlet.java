package web_basic_jsp_03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jsp_03/sessionLogin")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			//name 값 출력 key
			Enumeration<String> en = request.getParameterNames();
			while(en.hasMoreElements()) {
				String e = en.nextElement();
				System.out.println(e);
			}    
			// 값이 들어가있음 위,아래 혼용 사용
			Map<String, String[]>entry = request.getParameterMap();
			for(Entry<String, String[]> e : entry.entrySet()) {
				System.out.printf("%s - 5%s%n", e.getKey(), Arrays.toString(e.getValue()));
			}
			
			if(id.equals("java") && passwd.equals("java")) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				// 클라이언트의 웹주소를 변경(login.jsp -> menu.jsp forwarding)
				RequestDispatcher dispatcher =  request.getRequestDispatcher("menu.jsp");
				dispatcher.forward(request, response);
			}else {
				out.println("<script>");
				out.println("alert('아이디나 비밀번호가 일치하지 않습니다.  ')");         //  '' or   \""\ 사용
				out.println("history.back()");
				out.println("</script>");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
