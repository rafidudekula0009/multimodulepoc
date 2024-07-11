package org.dealer.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.dealer.view.generator.MyPlainHtmlView;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class HelloController {

	RestTemplate rest = new RestTemplate();
	String REST_URI = "http://localhost:8080/seller/help/";
	String REST_URI_CONTAINER = "http://host.docker.internal:8080/seller/help/";
	String REST_URI_REGISTER_CUST = "http://localhost:8080/seller/help/register_customer/{0}/{1}/{2}";
	String REST_URI_CONTAINER_REGISTER_CUST = "http://host.docker.internal:8080/seller/help/register_customer/{0}/{1}/{2}";

	// http://localhost:8080/dealer/hello_with_localhost/rafi
	@RequestMapping(value = "/contact_seller_with_localhost/{name}", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String getHelloMsg(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
		System.out.println("dealer getHelloMsg for localhost invoked!!!!!");
		String resp = rest.getForObject(REST_URI + name, String.class);
		return resp;
	}

	@RequestMapping(value = "/contact_seller_with_docker_host/{name}", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String getHelloMsg1(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
		System.out.println("dealer getHelloMsg1 for docker(host.docker.internal) invoked!!!!!");
		String resp = rest.getForObject(REST_URI_CONTAINER + name, String.class);
		return resp;
	}

	@RequestMapping(value = "/seller_request/register_customer_with_local/{name}/{email}/{address}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ModelAndView registerCustWithLocalHost(HttpServletResponse response, @PathVariable("name") String name,
			@PathVariable("email") String email, @PathVariable("address") String address) throws IOException {
		System.out.println("dealer registerCustWithLocalHost for localhost invoked!!!!!");
		System.out.println("uri=> "+REST_URI_REGISTER_CUST+"message formatted to => " + MessageFormat.format(REST_URI_CONTAINER, name, email, address));
		
		System.out.println("uri=> "+REST_URI_REGISTER_CUST+"manually formatted to => " + "http://localhost:8080/seller/help/register_customer/"+ name+"/"+ email+"/"+ address);

		//By using MessageFormat it will replace the localhost with docker host name (http://host.docker.internal:8080/seller/help)
		String uri = MessageFormat.format(REST_URI_REGISTER_CUST, name, email, address);
		String resp = rest.getForObject(uri, String.class);

		return new ModelAndView(new MyPlainHtmlView(resp));
	}

	@RequestMapping(value = "/seller_request/register_customer_with_docker/{name}/{email}/{address}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ModelAndView registerCustWithDockerHost(HttpServletResponse response, @PathVariable("name") String name,
			@PathVariable("email") String email, @PathVariable("address") String address) throws IOException {

		System.out.println("dealer registerCustWithDockerHost for docker(host.docker.internal) invoked!!!!!");
		System.out.println("message formatted to => " + MessageFormat.format(REST_URI_CONTAINER, name, email, address));

		String uri = MessageFormat.format(REST_URI_CONTAINER_REGISTER_CUST, name, email, address);
		String resp = rest.getForObject(uri, String.class);

		return new ModelAndView(new MyPlainHtmlView(resp));
	}
}