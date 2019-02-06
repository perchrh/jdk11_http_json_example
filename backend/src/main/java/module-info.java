module org.digitalsprouts.example.backend{
    requires java.net.http;
    requires jersey.server;
    requires java.ws.rs;
    requires java.xml.bind;
    requires javax.servlet.api;
    requires hk2.api;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jakarta.inject;

    exports org.digitalsprouts.example.backend.service;
    exports org.digitalsprouts.example.backend.api;
}