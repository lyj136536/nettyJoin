package com.netty.server;

import com.netty.server.powerserver.EchoServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ServerApplication.class, args);
		String[] a={"9999"};
		EchoServer.main(a);
	}

}
