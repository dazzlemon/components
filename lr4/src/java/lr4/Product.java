package lr4;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = """
        SELECT p FROM Product p
        WHERE p.id = :id
    """),
    @NamedQuery(name = "Product.findByQuantity", query = """
        SELECT p FROM Product p
        WHERE p.quantity = :quantity
    """),
    @NamedQuery(name = "Product.findByPriceInUsdCents", query = """
        SELECT p FROM Product p
        WHERE p.priceInUsdCents = :priceInUsdCents
    """)
})
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EqualsAndHashCode.Include
    @ToString.Include
    @NonNull
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Getter
    @Setter
    @Basic(optional = false)
    @Lob
    @Column(name = "Name")
    private String name;
    
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "Quantity")
    private int quantity;
    
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "PriceInUsdCents")
    private int priceInUsdCents;
}
