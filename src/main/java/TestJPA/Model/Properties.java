package TestJPA.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "properties")
public class Properties {
    @Id
    @Column(name = "propertyid")
    private int id;
    @Column(name = "propertyname")
    private String propertyName;
    @Column(name = "propertysort")
    private int propertySort;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Products products;
    @OneToMany(mappedBy = "properties")
    @JsonManagedReference
    private List<PropertyDetails> propertyDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getPropertySort() {
        return propertySort;
    }

    public void setPropertySort(int propertySort) {
        this.propertySort = propertySort;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public List<PropertyDetails> getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(List<PropertyDetails> propertyDetails) {
        this.propertyDetails = propertyDetails;
    }
}
