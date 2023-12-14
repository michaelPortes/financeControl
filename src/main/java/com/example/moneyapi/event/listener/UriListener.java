package com.example.moneyapi.event.listener;

import com.example.moneyapi.event.UriServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class UriListener implements ApplicationListener<UriServices> {
    @Override
    public void onApplicationEvent(UriServices uriServices) {
        HttpServletResponse response = uriServices.getResponse();
        Long id = uriServices.getId();

        addMethodLocation(response, id);
    }

    private void addMethodLocation(HttpServletResponse response, Long id){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(id).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
}
