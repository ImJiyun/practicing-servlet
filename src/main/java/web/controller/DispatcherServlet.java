package web.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/main")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// DispatcherServlet -> Controller
	// Controller 안에는 Controller member들만 있는 게 좋음 
	HashMap<String, Controller> beans;
	// HashMap<String, Object> beans2;


	public void init() throws ServletException {
		// init method는 servlet config 객체 안 받는 게 더 편함
		try {
			// web에서는 경로가 중요 
			
			// DI
			String controller_path = getServletContext().getRealPath("/WEB-INF/beans.xml"); // servlet이 있는 위치에서... web-inf 
			String model_path = getServletContext().getRealPath("/WEB-INF/beans2.xml");
			
			XmlBeanFactory factory = new XmlBeanFactory(controller_path, model_path);
			
			beans = factory.getBeans(); // controller 받기 
			// beans2 = factory.getBeans2(); // model 받기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json으로 보내기 때문에 request.getParmeter 사용 불가
		// gson을 이용해서 request가 가져온 json text를 꺼내기 -> 커맨드 패턴 적용 beans.xml
		// 기존 controlling 방식 => 많은 분기 처리 불편 => command pattern
		
		// out buffer 얻기 전에 한글 설정 필요 
		request.setCharacterEncoding("utf-8"); // 들어오는 데이터 한글 처리 위해 필요 
		response.setContentType("text/html;charset=UTF-8"); // 한글 담기 위해 필요 
		
		PrintWriter out = response.getWriter(); // out buffer 얻기 
		
		JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
		String sign = json.get("sign").getAsString();
		System.out.println(sign);
		
		JsonObject retJson = new JsonObject();
		
		beans.get(sign).service(request, response, json, retJson); // controller 객체를 얻어 옴 
		// 컨트롤러 객체에게 일 시킴
		
		out.append(retJson.toString());
		out.close();
	}

}
