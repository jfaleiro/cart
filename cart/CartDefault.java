package cart;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartDefault implements Cart {
  
  public CartDefault() {
    this(0.0d);
  }
  
  public CartDefault(final double taxRate) {
    this.taxRate = taxRate;
  }
  
  final ConcurrentHashMap<Product, Integer> lines = new ConcurrentHashMap<Product, Integer>();
  final double taxRate;
  
  public void add(Product product, int quantity) {
  	lines.compute(product, (p, v) -> (v == null) ? quantity : v + quantity);  
  }
  
  private BigDecimal calculateTotalPriceBeforeTaxes() {
    return lines
      .keySet()
      .stream()
      .map((p) -> BigDecimal.valueOf(p.price).multiply(BigDecimal.valueOf(lines.get(p))))
      .reduce(BigDecimal.ZERO, (a, p) -> a.add(p));  
  }
  
  public double getTotalPrice() {
    return this.calculateTotalPriceBeforeTaxes()
      .add(BigDecimal.valueOf(getTotalTaxAmount()))
      .setScale(2, RoundingMode.HALF_UP)
      .doubleValue();
  }
  
  public double getTotalTaxAmount() {
    return BigDecimal.valueOf(this.taxRate)
      .multiply(this.calculateTotalPriceBeforeTaxes())
      .setScale(2, RoundingMode.HALF_UP)
      .doubleValue();
  }

  
  public Map<Product, Integer> getLines() {
    return this.lines;
  }

}
