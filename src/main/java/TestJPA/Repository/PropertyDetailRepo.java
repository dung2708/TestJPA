package TestJPA.Repository;

import TestJPA.Model.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailRepo extends JpaRepository<PropertyDetails, Integer> {
}
