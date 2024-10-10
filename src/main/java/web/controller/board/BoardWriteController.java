package web.controller.board;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.model.member.Member;

public class BoardWriteController implements Controller {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson)
			throws ServletException, IOException {
		// 글 쓰기 작업 
		
		// 클라이언트에서 받은 데이터를 신뢰할 수 없음
		// writer가 진짜 writer인지 확인 필요 
		// 모든 request에는 세션 아이디 존재 
		HttpSession session = request.getSession(false); // 없다고 세션 재할당하는 게 아니기 때문에 false로 
		if (session != null) { // 이미 로그인 된 사용자일 때 
			Member member = (Member) session.getAttribute("member");
			System.out.println(member);
			// 사용자와 writer가 같은지 확인 
			if (member != null) {
				String writer = json.get("writer").getAsString(); // 그냥 get하면 json 형태 
			
				if (member.getName().equals(writer)) {
					String content = json.get("content").getAsString();
					System.out.println(writer + " " + content);
					retJson.addProperty("msg", "글쓰기 완료");
				} else {
					// 해킹 상황 
					retJson.addProperty("msg", "정상 요청이 아닙니다.");
				}
				
			} else {
				// 해킹 상황 
				retJson.addProperty("msg", "로그인 먼저 해주세요");
			}
			
		} else {
			retJson.addProperty("msg", "로그인 먼저 해주세요");
		}
		
	}

}
