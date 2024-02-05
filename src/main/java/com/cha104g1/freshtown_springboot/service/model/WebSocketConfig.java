package com.cha104g1.freshtown_springboot.service.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig {
	
	@Bean
    public ServerEndpointExporter serverEndpoint() {
    	return new ServerEndpointExporter();
    }
	
	
}
