package ru.smply.was.spring.web.controller;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="WebService")
public class WebServiceEndpoint {

    @WebMethod
    public String hello() {
        return "Hello!";
    }
}
