package web.controller.board;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.Controller;

public class BoardDeleteController implements Controller {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson)
			throws ServletException, IOException {
		// 글 삭제 
		
	}

}
