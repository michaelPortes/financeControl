package com.example.moneyapi.event;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class UriServices extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;
    public UriServices(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id =  id;
    }
}
