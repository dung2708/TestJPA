package TestJPA.Repository;

import TestJPA.Model.ProductDetailPropertyDetails;
import TestJPA.Model.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailPropertyDetailRepo extends JpaRepository<ProductDetailPropertyDetails, Integer> {
}
