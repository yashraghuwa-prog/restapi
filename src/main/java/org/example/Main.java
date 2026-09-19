
package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static void main(String[] args) {

        URI baseUri = URI.create("http://localhost:8080/");

        ResourceConfig config = new ResourceConfig()
                .packages("org.example");

        HttpServer server =
                GrizzlyHttpServerFactory.createHttpServer(
                        baseUri,
                        config
                );

        System.out.println("Jersey server started!");
        System.out.println("http://localhost:8080");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}