module org.digitalsprouts.example.backend{
    requires java.net.http;
    requires java.ws.rs;
    requires java.xml.bind;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires jersey.server; // jersey http-server
    requires javax.servlet.api; // jersey dependency
    requires hk2.api; // jersey dependency
    requires jakarta.inject; // jersey dependency

    exports org.digitalsprouts.example.backend.service;
    exports org.digitalsprouts.example.backend.api;
}