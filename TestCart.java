import static org.junit.Assert.assertEquals;

import cart.Product;
import cart.Cart;
import cart.CartDefault;

class TestCart {
	public void testAddSingleProduct() {
      final var dove = new Product("Dove Soap", 39.99);
      
      final Cart cart = new CartDefault();
      cart.add(dove, 1);
      
      assertEquals(39.99, cart.getTotalPrice(), 0.0d);
      assertEquals(1, cart.getLines().size());
    }  
  
	public void testAddMultipleProducts() {
      final var dove = new Product("Dove Soap", 39.99);
      
      final Cart cart = new CartDefault();
      cart.add(dove, 5);
      cart.add(dove, 3);
      
      assertEquals(319.92, cart.getTotalPrice(), 0.0d);
      assertEquals(1, cart.getLines().size());
      assertEquals(Integer.valueOf(8), cart.getLines().get(dove));
    }  
  
	public void testCalculateTaxRateManyProducts() {
      final var dove = new Product("Dove Soap", 39.99);
      final var axe = new Product("Axe Deos", 99.99);
      
      final Cart cart = new CartDefault(.125);
      cart.add(dove, 2);
      cart.add(axe, 2);
      
      assertEquals(314.96, cart.getTotalPrice(), 0.0d);
      assertEquals(35.00, cart.getTotalTaxAmount(), 0.0d);
      assertEquals(2, cart.getLines().size());
    }  
  
  	public static void main(String[] args) {
    	final var test = new TestCart();
        test.testAddSingleProduct();
        test.testAddMultipleProducts();
        test.testCalculateTaxRateManyProducts();
  	}
}
