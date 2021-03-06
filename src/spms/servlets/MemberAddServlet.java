package spms.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

// UI 출력 코드를 제거하고, UI 생성 및 출력을 JSP에게 위임한다.
@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
			
			Member member = (Member)request.getAttribute("member");
			memberDao.insert(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
}
