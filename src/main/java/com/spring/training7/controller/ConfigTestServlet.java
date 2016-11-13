package com.spring.training7.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.spring.training7.entity.TblUser;
import com.spring.training7.jpa.UserRepository;


/**
 * Servlet implementation class ConfigTestServlet
 */
@WebServlet(urlPatterns = { "/configTest", "/config" })
public class ConfigTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;
	
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Writer writer = response.getWriter();
		response.setContentType("text/html");

		List<TblUser> users = userRepository.findAll();
		for (TblUser user : users) {
			writer.write(user.toString());
			writer.write("<br />");
		}

	}

}
