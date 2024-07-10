package org.seller.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.org.example.MyUtil;
@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		System.out.println(MyUtil.printName("Rafi"));
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/testUtil/{name}")
	@ResponseBody
	public String testUtil(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		return MyUtil.printName(name);
	}
}
