package org.dealer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin(origins = "*")
public class HelloController {

	RestTemplate rest = new RestTemplate();
	 String REST_URI="http://localhost:8080/seller/help/";
	 String REST_URI_CONTAINER="http://host.docker.internal:8080/seller/help/";
	 
	 // http://localhost:8080/dealer/hello_with_localhost/rafi
	@RequestMapping(value="/hello_with_localhost/{name}", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String getHelloMsg(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		System.out.println("dealer getHelloMsg for localhost invoked!!!!!");
		String resp=rest.getForObject(
	            REST_URI+name,
	            String.class);
		return resp;
	}
	
	@RequestMapping(value="/hello_with_docker_host/{name}", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String getHelloMsg1(HttpServletResponse response, @PathVariable("name") String name) throws IOException{
		System.out.println("dealer getHelloMsg1 for docker(host.docker.internal) invoked!!!!!");
		String resp=rest.getForObject(
				REST_URI_CONTAINER+name,
	            String.class);
		return resp;
	}
}
