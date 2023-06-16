package QLBanDoTheThao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class logoutController {
	
	@RequestMapping()
	public String login(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("var");
		return "login";
	}

}
