package org.seller.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.seller.entity.Customer;
import org.seller.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/help")
@CrossOrigin(origins = "*")
public class Hello {
	 @Autowired
	    private CustomerService customerService;
	 
	@RequestMapping(value="/{name}", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String getHelp(HttpServletResponse response, @PathVariable("name") String name){
		System.out.println("seller help invoked!!!!. name=> "+name);
		return "Hello "+name+", I'm the seller. How can I help you?";
	}
	
	//http://localhost:8080/seller/help/register_customer/rafi/rafi@gmail.com/ndk
		@RequestMapping(value="register_customer/{name}/{email}/{address}")
		public ModelAndView registerCustomer(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("email") String email, @PathVariable("address") String address) throws IOException{
			
			customerService.save(new Customer(name, email, address));
			
			System.out.println("------seller registerCustomer invoked!!-------");
			
			return new ModelAndView("insert_success");
		}
}
