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
        MyWebService hello = service.getPort(MyWebService.class);
        System.out.println(hello.getHelloString("Daniel Safonov"));
    }
}
