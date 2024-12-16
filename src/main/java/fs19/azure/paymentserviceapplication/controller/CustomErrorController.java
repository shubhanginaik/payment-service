package fs19.azure.paymentserviceapplication.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

  @Autowired
  private ErrorAttributes errorAttributes;

  @RequestMapping("/error")
  @ResponseBody
  public String handleError(HttpServletRequest request) {
    ServletWebRequest webRequest = new ServletWebRequest(request);
    Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    Integer statusCode = (Integer) errorAttributes.get("status");
    String message = (String) errorAttributes.get("message");
    return String.format("Error code: %d, Message: %s", statusCode, message);
  }
}