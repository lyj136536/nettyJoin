package com.netty.client;

import com.netty.client.powerclient.EchoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(ClientApplication.class, args);
        String[] a={"127.0.0.1","9999"};
        EchoClient.main(a);
    }

}
