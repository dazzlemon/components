package lr6;

import javax.xml.ws.Endpoint;

public class MyWebServicePublisher {
    public static void main(String... args) {
        Endpoint.publish( "http://localhost:1337/wss/hello"
                        , new MyWebServiceImplementation());
    }
}
