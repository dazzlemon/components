package lr4;

import java.math.BigDecimal;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface ProductFacadeLocal {
    void create(Product product);
    void edit(Product employee);
    void remove(Product employee);
    Product find(Object id);
    List<Product> findAll();
    List<Product> findRange(int[] range);
    int count();
    boolean insertProduct(
        Integer id, String name, int quantity, int priceInUsdCents);
}
