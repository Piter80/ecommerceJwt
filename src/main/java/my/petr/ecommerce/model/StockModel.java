package my.petr.ecommerce.model;

import lombok.Data;

@Data
public class StockModel {

  private String product_name = "";
  private String variant_name = "";
  private String stock = "0";


}