package lr6;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MyWebService {
    @WebMethod
    public Double calculate(Double n1, Operator op, Double n2);
}

enum Operator { PLUS
              , MINUS
              , DIV
              , MUL
              }