package lr6;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface = "lr6.MyWebService")
public class MyWebServiceImplementation implements MyWebService {
    @Override
    public Double calculate(Double n1, Operator op, Double n2) {
        switch (op) {
            case MINUS: return n1 - n2;
            case PLUS: return n1 + n2;
            case DIV: return n1 / n2;
            case MUL: return n1 * n2;
        }
        return null;// never happens
    }
    
    public static void main(String... args) {
        Endpoint.publish( "http://localhost:1986/wss/hello"
                        , new MyWebServiceImplementation()
                        );
    }
}
