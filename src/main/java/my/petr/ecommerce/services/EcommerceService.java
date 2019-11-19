package my.petr.ecommerce.services;

import java.util.List;
import my.petr.ecommerce.model.Product;
import my.petr.ecommerce.model.ProductGroup;
import my.petr.ecommerce.repository.GroupRepository;
import my.petr.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcommerceService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  GroupRepository groupRepository;

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public Product getProduct(long id) {
    return productRepository.findOne(id);
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public void deleteProduct(Long id) {
    productRepository.delete(id);
  }

  public List<ProductGroup> getGroups() {
    return groupRepository.findAll();
  }

  public ProductGroup getGroup(long id) {
    return groupRepository.findOne(id);
  }

  public ProductGroup saveGroup(ProductGroup group) {
    return groupRepository.save(group);
  }
}
