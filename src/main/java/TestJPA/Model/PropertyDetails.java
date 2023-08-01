package TestJPA.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "propertydetails")
public class PropertyDetails {
    @Id
    @Column(name = "propertydetailid")
    private int id;
    @Column(name = "propertydetailcode")
    private String propertyDetailCode;
    @Column(name = "propertydetaildetail")
    private String propertyDetailDetail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyid")
    @JsonBackReference
    private Properties properties;
    @OneToMany(mappedBy = "propertyDetails")
    @JsonManagedReference
    private List<ProductDetailPropertyDetails> productDetailPropertyDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyDetailCode() {
        return propertyDetailCode;
    }

    public void setPropertyDetailCode(String propertyDetailCode) {
        this.propertyDetailCode = propertyDetailCode;
    }

    public String getPropertyDetailDetail() {
        return propertyDetailDetail;
    }

    public void setPropertyDetailDetail(String propertyDetailDetail) {
        this.propertyDetailDetail = propertyDetailDetail;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<ProductDetailPropertyDetails> getProductDetailPropertyDetails() {
        return productDetailPropertyDetails;
    }

    public void setProductDetailPropertyDetails(List<ProductDetailPropertyDetails> productDetailPropertyDetails) {
        this.productDetailPropertyDetails = productDetailPropertyDetails;
    }
}
