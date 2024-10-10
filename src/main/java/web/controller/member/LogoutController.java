package web.controller.member;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.model.member.MemberService;

public class LogoutController implements Controller{
	
	// 밑의 주석 친 코드는 없어도 됨 
	/*
	MemberService memberService;
	
	public LogoutController() throws MyException {
		// memberservice = new MemberService();
		memberService = (MemberService) XmlBeanFactory.getBeans2().get("memberService");
	}
	*/
	

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		// session이 존재하면 현재 세션을 반환하고 존재하지 않으면 그냥 null을 반환 
		if (session != null) {
			session.invalidate(); // 세션 무효화 
		}
		
	}

}
