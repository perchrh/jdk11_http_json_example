package org.digitalsprouts.example.http_client;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URI;

public class TestServer {

    private Server server;
    private URI baseUri;

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0); // auto-bind to available port
        server.addConnector(connector);

        new WebAppContext(server, "src/main/webapp/", "/");

        server.start();

        // Determine Base URI for Server
        String host = connector.getHost();
        if (host == null) {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        baseUri = new URI(String.format("http://%s:%d/", host, port));
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URI getBaseUri() {
        return baseUri;
    }

    public static void main(String[] args) throws Exception {
        TestServer server = new TestServer();
        server.start();
        System.out.println("Server running at " + server.getBaseUri());
    }

}