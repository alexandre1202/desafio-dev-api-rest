package br.com.dockbank.bankaccount.filter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestFilter implements Filter {
  
  @Override
  public void init(FilterConfig config) throws ServletException {}
     
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    // TODO: Here you can read the request content as per your need from httpRequest object
    logPostOrPutRequestBody((HttpServletRequest) request);
    System.out.println("request = " + request + ", response = " + response + ", filterChain = " + filterChain);
  }
  
  @Override
  public void destroy() {}

  private void logPostOrPutRequestBody(HttpServletRequest httpRequest) throws IOException {
    if(Arrays.asList("POST", "PUT").contains(httpRequest.getMethod())) {
      String characterEncoding = httpRequest.getCharacterEncoding();
      Charset charset = Charset.forName(characterEncoding);
      String bodyInStringFormat = readInputStreamInStringFormat(httpRequest.getInputStream(), charset);
      System.out.println(("Request body: {}" + bodyInStringFormat));
    }
  }

  private String readInputStreamInStringFormat(InputStream stream, Charset charset) throws IOException {
    final int MAX_BODY_SIZE = 1024;
    final StringBuilder bodyStringBuilder = new StringBuilder();
    if (!stream.markSupported()) {
      stream = new BufferedInputStream(stream);
    }

    stream.mark(MAX_BODY_SIZE + 1);
    final byte[] entity = new byte[MAX_BODY_SIZE + 1];
    final int bytesRead = stream.read(entity);

    if (bytesRead != -1) {
      bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE), charset));
      if (bytesRead > MAX_BODY_SIZE) {
        bodyStringBuilder.append("...");
      }
    }
    stream.reset();

    return bodyStringBuilder.toString();
  }
}