package TestJPA.Repository;

import TestJPA.Model.ProductDetails;
import TestJPA.Model.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetails, Integer> {

}
