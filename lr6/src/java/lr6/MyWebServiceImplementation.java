package lr6;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface = "lr6.MyWebService")
public class MyWebServiceImplementation implements MyWebService {
    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }
    
    public static void main(String... args) {
        Endpoint.publish( "http://localhost:1986/wss/hello"
                        , new MyWebServiceImplementation()
                        );
    }
}
