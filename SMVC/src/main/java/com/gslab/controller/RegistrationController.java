package com.gslab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gslab.dao.UserDao;
import com.gslab.model.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Controller
public class RegistrationController {
	@Autowired
	UserDao userService;

	@RequestMapping(value = "/register" , method= RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView("register");
		mav.addObject(new User());
		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) throws Exception {
		try {
			userService.register(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
			//e.printStackTrace();
		}
		return new ModelAndView("welcome", "firstname", user.getFirstname());
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleUserRegistrationException(HttpServletRequest request, Exception ex){
		ModelAndView mav=new ModelAndView("error");
		mav.addObject("Exception", ex);
	    mav.addObject("url", request.getRequestURL());
	    return mav;
	}
}
