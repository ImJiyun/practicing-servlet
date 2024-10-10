package web.controller.member;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.controller.XmlBeanFactory;
import web.model.member.Member;
import web.model.member.MemberService;
import web.model.member.MyException;

public class LoginController implements Controller{
	
	MemberService memberService;
	
	public LoginController() {
		// try {
			// memberService = new MemberService();
		System.out.println(XmlBeanFactory.getBeans2());
		memberService = (MemberService) XmlBeanFactory.getBeans2().get("memberService");
		// } catch (MyException e) {
			
		//	e.printStackTrace();
		// }
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson)
			throws ServletException, IOException {
		
		String id = json.get("id").getAsString();
		String pw = json.get("pw").getAsString();
		System.out.println(id + ":" + pw);
		
		try {
			Member member = memberService.login(id, pw);
			if (member != null) { // login ok
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				retJson.addProperty("name", member.getName());
			} else { // login fail
				retJson.addProperty("msg", "다시 로그인해주세요");
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			retJson.addProperty("msg", e.getMessage());
			e.printStackTrace();
		}
	}

}
