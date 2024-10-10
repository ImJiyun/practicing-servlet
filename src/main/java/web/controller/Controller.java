package web.controller;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
	
	// 하나의 메서드로 통일해서 일을 시킬 수 있도록 
	void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson) throws ServletException, IOException ;
	
}
