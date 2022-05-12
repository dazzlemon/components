package lr4;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductFacade
extends AbstractFacade<Product> implements ProductFacadeLocal {
    @PersistenceContext(unitName = "lr4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public boolean insertProduct(
        Integer id, String name, int quantity, int priceInUsdCents
    ) {
        try{
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setQuantity(quantity);
            product.setPriceInUsdCents(priceInUsdCents);
            em.persist(product);
            return true;
        } catch (Exception ex) {
            System.out.println("Didn't insert data: " + ex);
        }
        return false;
    }
}