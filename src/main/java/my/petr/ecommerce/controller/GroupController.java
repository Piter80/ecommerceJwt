package my.petr.ecommerce.controller;

import java.util.List;
import my.petr.ecommerce.model.ProductGroup;
import my.petr.ecommerce.services.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

  @Autowired
  EcommerceService ecommerceService;

  @RequestMapping(method = RequestMethod.GET)
  public List<ProductGroup> index() {
    return ecommerceService.getGroups();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ProductGroup view(@PathVariable("id") long id) {
    return ecommerceService.getGroup(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public ProductGroup edit(@PathVariable(value = "id", required = false) long id,
      @RequestBody ProductGroup group) {

    ProductGroup updatedGroup = ecommerceService.getGroup(id);

    if (updatedGroup == null) {
      return null;
    }

    updatedGroup.setGroupName(group.getGroupName());
    updatedGroup.setPrice(group.getPrice());

    return ecommerceService.saveGroup(updatedGroup);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ProductGroup create(@RequestBody ProductGroup group) {
    if( group.getGroupVariants() != null ) {
      group.getGroupVariants().forEach(gv -> gv.setGroup(group));
    }
    return ecommerceService.saveGroup(group);
  }

}
