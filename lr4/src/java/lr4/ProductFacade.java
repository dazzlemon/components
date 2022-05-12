package lr4;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.Getter;
import lombok.extern.java.Log;

@Stateless
@Log
public class ProductFacade
extends AbstractFacade<Product> implements ProductFacadeLocal {
    @Getter(onMethod = @__(@Override))
    @PersistenceContext(unitName = "lr4PU")
    private EntityManager entityManager;

    public ProductFacade() { super(Product.class); }

    @Override
    public boolean insertProduct(
        Integer id, String name, int quantity, int priceInUsdCents
    ) {
        try {
            var product = new Product();
            product.setId(id);
            product.setName(name);
            product.setQuantity(quantity);
            product.setPriceInUsdCents(priceInUsdCents);
            entityManager.persist(product);
            return true;
        } catch (Exception e) {
            log.throwing("AbstractFacade<Product>", "insertProduct", e);
        }
        return false;
    }
}