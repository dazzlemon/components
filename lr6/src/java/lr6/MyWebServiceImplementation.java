package lr6;

import javax.jws.WebService;

@WebService(serviceName = "MyWebService")
public class MyWebServiceImplementation implements MyWebService {
    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
