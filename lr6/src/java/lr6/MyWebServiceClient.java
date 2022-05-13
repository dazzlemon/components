package lr6;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MyWebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:1986/wss/hello?wsdl");
        QName qname = new QName( "http://lr6/"
                               , "MyWebServiceImplementationService"
                               );
        Service service = Service.create(url, qname);
        MyWebService myWebService = service.getPort(MyWebService.class);

        double n1 = 1.;
        double n2 = 99.;
        Operator op = Operator.PLUS;
        
        String opStr = "";
        switch (op) {
            case MINUS: opStr = " - "; break;
            case PLUS:  opStr = " + "; break;
            case DIV:   opStr = " / "; break;
            case MUL:   opStr = " * ";
        }
        
        System.out.println( n1 + opStr + n2 + " = " +
                           myWebService.calculate(n1, op, n2)
                          );
    }
}
