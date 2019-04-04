module http.client.demo.main {
    requires java.net.http;
    requires java.ws.rs;
    requires java.xml.bind;
    requires jersey.server;
    requires hk2.api;
    requires jakarta.inject;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires org.digitalsprouts.example.backend;
}