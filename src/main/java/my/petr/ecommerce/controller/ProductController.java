package my.petr.ecommerce.controller;

import java.util.List;
import my.petr.ecommerce.model.Product;
import my.petr.ecommerce.services.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  EcommerceService ecommerceService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Product> index() {
    return ecommerceService.getProducts();
  }

  @RequestMapping("/{id}")
  public Product view(@PathVariable("id") long id) {
    return ecommerceService.getProduct(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public Product edit(@PathVariable("id") long id, @RequestBody Product product){

    Product updatedProduct = ecommerceService.getProduct(id);
    if(updatedProduct == null){
      return null;
    }
    updatedProduct.setName(product.getName());
    updatedProduct.setPrice(product.getPrice());
    return ecommerceService.saveProduct(updatedProduct);
  }



}
