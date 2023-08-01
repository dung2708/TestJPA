package TestJPA.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "productdetails")
public class ProductDetails {
    @Id
    @Column(name = "productdetailid")
    private int id;
    @Column(name = "productpropertyname")
    private String productPropertyName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private float price;
    @Column(name = "shellprice")
    private float shellPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentid")
    private ProductDetails parentId;

    @OneToMany(mappedBy = "productDetails")
    @JsonManagedReference
    private List<ProductDetailPropertyDetails> productDetailPropertyDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductPropertyName() {
        return productPropertyName;
    }

    public void setProductPropertyName(String productPropertyName) {
        this.productPropertyName = productPropertyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getShellPrice() {
        return shellPrice;
    }

    public void setShellPrice(float shellPrice) {
        this.shellPrice = shellPrice;
    }

    public ProductDetails getParentId() {
        return parentId;
    }

    public void setParentId(ProductDetails parentId) {
        this.parentId = parentId;
    }

    public List<ProductDetailPropertyDetails> getProductDetailPropertyDetails() {
        return productDetailPropertyDetails;
    }

    public void setProductDetailPropertyDetails(List<ProductDetailPropertyDetails> productDetailPropertyDetails) {
        this.productDetailPropertyDetails = productDetailPropertyDetails;
    }
}
