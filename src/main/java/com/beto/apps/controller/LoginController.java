package com.beto.apps.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beto.apps.models.Admin;
import com.beto.apps.service.implement.AdminImpl;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private AdminImpl adminDb;
	
	@GetMapping("")
	public ModelAndView login(ModelAndView modelAndView) {
		
		modelAndView.setViewName("/login");
		return modelAndView;
	}
	
	@PostMapping("/authenticate")
	public ModelAndView authenticate(HttpServletRequest request,ModelAndView modelAndView) {
		
		String usuario=request.getParameter("user");
		String contraseña=request.getParameter("pass");
		Admin ad=adminDb.getAdmin();
		
		if(ad.getP().equals(contraseña)&&ad.getU().equals(usuario)) {
			request.getSession().setAttribute("auth", "auth");
			modelAndView=new ModelAndView("redirect:/");
				
		}
		else {
			modelAndView.setViewName("/login");
		}
		
		return modelAndView;
	}
}
