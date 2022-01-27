package cart;

import java.util.Map;

public interface Cart {
  void add(Product product, int quantity);
  double getTotalPrice();
  Map<Product, Integer> getLines();
  double getTotalTaxAmount();
}