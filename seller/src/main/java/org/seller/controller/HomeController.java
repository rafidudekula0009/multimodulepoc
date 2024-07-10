package org.seller.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.org.example.MyUtil;
@Controller
@CrossOrigin(origins = "*")
public class HomeController {

	RestTemplate rest = new RestTemplate();
	 String REST_URI="http://localhost:8080/util/pingcheck/";
	 String REST_URI_CONTAINER="http://host.docker.internal:8080/util/pingcheck/";
	 
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/testUtil/{name}")
	@ResponseBody
	public String testUtil(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		return MyUtil.printName(name);
	}
	
	@RequestMapping(value="/ping_util_with_localhost/{name}", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String pingCheckWithLocalHost(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		System.out.println("util pingCheckWithLocalHost for localhost invoked!!!!!");
		String resp=rest.getForObject(
	            REST_URI+name,
	            String.class);
		return resp;
	}
	
	@RequestMapping(value="/ping_util_with_docker_host/{name}", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String pingCheckWithDockerHost(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		System.out.println("util pingCheckWithDockerHost for docker(host.docker.internal) invoked!!!!!");
		String resp=rest.getForObject(
				REST_URI_CONTAINER+name,
	            String.class);
		return resp;
	}
}
