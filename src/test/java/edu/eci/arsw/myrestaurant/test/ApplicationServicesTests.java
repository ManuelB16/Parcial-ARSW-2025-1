package edu.eci.arsw.myrestaurant.test;

import edu.eci.arsw.myrestaurant.beans.BillCalculator;
import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.services.OrderServicesException;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApplicationServicesTests {

    
    RestaurantOrderServicesStub ros;
    @Autowired
    @Test
    public void testBillCalculator() throws OrderServicesException {
        ros = new RestaurantOrderServicesStub();
        ros.setBillCalculator(new BillCalculator() {
            @Override
            public int calculateBill(Order o, java.util.Map<String, edu.eci.arsw.myrestaurant.model.RestaurantProduct> productsMap) {
                return 0;
            }
        });
        Order o = new Order(1);
        o.addDish("Pizza", 2);
        o.addDish("Hamburguesa", 1);
        System.out.println(ros.getTableOrder(1));
    }

}
