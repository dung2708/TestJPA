package TestJPA.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "productdetailpropertydetails")
public class ProductDetailPropertyDetails {
    @Id
    @Column(name = "productdetailpropertydetailid")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertydetailid")
    @JsonBackReference
    private PropertyDetails propertyDetails;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Products products;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productdetailid")
    @JsonBackReference
    private ProductDetails productDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(PropertyDetails propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }
}
