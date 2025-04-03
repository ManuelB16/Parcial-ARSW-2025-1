package edu.eci.arsw.myrestaurant.restcontrollers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServices;
 
@RestController

@RequestMapping(value = "/orders")

public class OrdersAPIController {

    @Autowired

    RestaurantOrderServices ros;
 
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allOrders() {
        try {
            if (ros.getTablesWithOrders().isEmpty()) {
                return new ResponseEntity<>("No hay órdenes registradas", HttpStatus.NO_CONTENT);
            }
            JSONArray jsonArray = new JSONArray();
            for (Integer orderId : ros.getTablesWithOrders()) {
                JSONObject json = new JSONObject(ros.getTableOrder(orderId));
                json.put("mesa", orderId);
                json.put("valorTotal", ros.calculateTableBill(orderId));
                jsonArray.put(json);
            }
            return new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error interno del servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
