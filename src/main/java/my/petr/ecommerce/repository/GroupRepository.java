package my.petr.ecommerce.repository;

import my.petr.ecommerce.model.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<ProductGroup, Long> {

}
