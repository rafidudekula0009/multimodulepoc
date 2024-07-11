package org.dealer.view.generator;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class MyPlainHtmlView implements org.springframework.web.servlet.View {

    private final String htmlDocument;

    public MyPlainHtmlView(String htmlDocument) {
        this.htmlDocument = htmlDocument;
    }

	@Override
	public void render(Map<String, ?> model, javax.servlet.http.HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServletOutputStream out = response.getOutputStream();
        out.write(this.htmlDocument.getBytes("utf-8"));
		
	}
}