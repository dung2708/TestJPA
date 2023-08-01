package TestJPA.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "productid")
    private int id;
    @Column(name = "productname")
    private String productName;
    @OneToMany(mappedBy = "products")
    @JsonManagedReference
    private List<Properties> propertiesList;
    @OneToMany(mappedBy = "products")
    @JsonManagedReference
    private List<ProductDetailPropertyDetails> productDetailPropertyDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Properties> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public List<ProductDetailPropertyDetails> getProductDetailPropertyDetails() {
        return productDetailPropertyDetails;
    }

    public void setProductDetailPropertyDetails(List<ProductDetailPropertyDetails> productDetailPropertyDetails) {
        this.productDetailPropertyDetails = productDetailPropertyDetails;
    }
}
