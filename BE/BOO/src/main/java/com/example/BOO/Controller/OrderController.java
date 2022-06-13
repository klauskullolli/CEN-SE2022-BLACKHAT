package com.example.BOO.Controller;

import com.example.BOO.Exception.BadRequestException;
import com.example.BOO.Exception.DublicatedOrderExeption;
import com.example.BOO.Exception.OrderNoAmountExeption;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.*;
import com.example.BOO.Repository.ClientRepository;
import com.example.BOO.Repository.OrderRepository;
import com.example.BOO.Repository.ProductRepository;
import com.example.BOO.Service.OrderService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository ;

    @Autowired
    ProductRepository productRepository ;

    @Autowired
    ClientRepository clientRepository ;

    @Autowired
    OrderService orderService ;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        return orderRepository.findById(id).map(order ->new ResponseEntity<>(order, HttpStatus.OK)).
                orElseThrow(()->new ResourceNotFoundException("Order with Id: " + id+ " does not exist"));
    }


    @PostMapping("/client/{client_id}/product")
    public ResponseEntity<Order> createOrder(@PathVariable Integer client_id, @RequestParam(value = "product_id") int product_id, @RequestParam(value = "amount") int amount, @RequestBody(required = false) Map<String ,Object> map){
        Optional<Product> productOptional = productRepository.findById(product_id) ;
        Optional<Client> clientOptional = clientRepository.findById(client_id);

        if(clientOptional.isPresent() && productOptional.isPresent()){
            if(clientOptional.get().getOrder()==null){
                Order  order = new Order() ;
                Product product = productOptional.get();
                Client client = clientOptional.get();

                Sell sell = new Sell() ;

                if (amount > product.getAmount()){
                    amount= product.getAmount() ;
                }

                Double price = amount*product.getPrice() ;

                sell.setAmount(amount);
                sell.setPrice(price);

                if(!map.isEmpty()){
                    String description = (String) map.get("description") ;
                    sell.setDescripion(description);
                }

                sell.setOrder(order);
                sell.setProduct(product);

                order.setClient(client);
                order.getSells().add(sell);
                order = orderRepository.save(order) ;

                Integer prodcut_amaunt = product.getAmount() - sell.getAmount() ;
                product.setAmount(prodcut_amaunt);
                productRepository.save(product) ;
                return new ResponseEntity<>(order ,HttpStatus.CREATED) ;
            }
            else {
             throw  new DublicatedOrderExeption("Order already exist") ;
            }


        }else{
            if(clientOptional.isEmpty()){
                throw new ResourceNotFoundException("Client with id: " + client_id+ " does not exist") ;
            }
            if (productOptional.isEmpty()){
                throw new ResourceNotFoundException("Product with id: " + product_id+ " does not exist") ;
            }
        }
        return null ;
    }



    @PutMapping("{id}/product")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Integer id, @RequestParam(value = "product_id") int product_id, @RequestParam(value = "amount") int amount, @RequestBody(required = false) Map<String ,Object> map ){
        Optional<Order> orderOptional = orderRepository.findById(id) ;
        if(orderOptional.isPresent()){

            Order order = orderOptional.get();
            Optional<Product> productOptional = productRepository.findById(product_id) ;

            if(productOptional.isPresent()){
                Product product = productOptional.get();
                if(product.getAmount()!=0){
                    Sell sell = null ;


                    if (order.getSells().size()>0){

                        Sell ordersell = order.getSells().stream().filter(sell1 -> sell1.getProduct().getId()==product_id).findAny().orElse(null) ;
                        sell = ordersell ;
                    }

                    if (sell==null){
                        sell = new Sell();
                        if (amount > product.getAmount()){
                            amount= product.getAmount() ;
                        }

                        Double price = amount*product.getPrice() ;

                        sell.setAmount(amount);
                        sell.setPrice(price);

                        if(!map.isEmpty()){
                            String description = (String) map.get("description") ;
                            sell.setDescripion(description);
                        }

                        sell.setOrder(order);
                        sell.setProduct(product);

                        order.getSells().add(sell);
                        order = orderRepository.save(order) ;

                        Integer prodcut_amaunt = product.getAmount() - sell.getAmount() ;
                        product.setAmount(prodcut_amaunt);
                        productRepository.save(product) ;
                        return new ResponseEntity<>(order ,HttpStatus.OK) ;

                    }
                    else{
                        if (amount > product.getAmount()){
                            amount= product.getAmount() ;
                        }

                        Double price = amount*product.getPrice() ;

                        sell.setAmount(amount);
                        sell.setPrice(price);

                        if(!map.isEmpty()){
                            String description = (String) map.get("description") ;
                            sell.setDescripion(description);
                        }

                        order = orderRepository.save(order) ;

                        Integer prodcut_amaunt = product.getAmount() - sell.getAmount() ;
                        product.setAmount(prodcut_amaunt);
                        productRepository.save(product) ;

                        return new ResponseEntity<>(order ,HttpStatus.OK) ;
                    }


                }
                else{
                  throw new OrderNoAmountExeption("Product with id: "+ product_id + " doesn't have any amount") ;
                }


            }else{
                throw  new ResourceNotFoundException("Product with Id: " + id+ " does not exist") ;
            }
        }
        else {
            throw  new ResourceNotFoundException("Order with Id: " + id+ " does not exist") ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder (@PathVariable Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id) ;
        if(orderOptional.isEmpty() ){
            throw new ResourceNotFoundException("Order with id: " + id+ " does not exist") ;
        }
        else {
            orderRepository.deleteById(id);
            return   new ResponseEntity<>(orderOptional.get(), HttpStatus.OK) ;
        }
    }


    @GetMapping("/{id}/sells")
    public ResponseEntity<?> getSellsFromOrder(@PathVariable Integer id ){
        return orderRepository.findById(id).map(order ->new ResponseEntity<>(order.getSells(), HttpStatus.OK)).
                orElseThrow(()->new ResourceNotFoundException("Order with Id: " + id+ " does not exist"));
    }

    @GetMapping("{id}/ready-notify")
    public ResponseEntity<?> notifyOrder(@PathVariable Integer id){
        return orderRepository.findById(id).map(order ->{
            Map<String, String> response = new HashMap<>() ;
            Message.Status status  = orderService.notifyOrderReady(order.getId()) ;
            if (status == Message.Status.DELIVERED){
                response.put("message", "Message was sent successfully") ;
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{
                response.put("message", "Message was not sent successfully") ;
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }).
                orElseThrow(()->new ResourceNotFoundException("Order with Id: " + id+ " does not exist"));
    }

    @GetMapping("{id}/confirm")
    public ResponseEntity<?> confirmOrder(@PathVariable Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id) ;
        if(orderOptional.isPresent()){

            Map<String, Object> response = new HashMap<>();
            Bill bill = orderService.confirmOrder(orderOptional.get()) ;

            if(bill!=null){
                response.put("message", "Bill created successfully") ;
                response.put("bill", bill ) ;

                return new  ResponseEntity<>(response, HttpStatus.CREATED) ;
            }
            else {
                response.put("message", "Bill wasn't created successfully") ;
                return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST) ;
            }

        }else{
            throw  new ResourceNotFoundException("Order with Id: " + id+ " does not exist");
        }

    }

    @PostMapping("{id}/assignTo/seller")
    public ResponseEntity<?> assignOrderToSeller(@PathVariable Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id) ;
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            if(order.getSeller()!=null) throw new BadRequestException("Order with Id: " + id+ " is assigned to a seller already") ;
            else {
                Seller seller = orderService.asignOrderToSeller(order);
                if (seller == null) throw new BadRequestException("There is no seller to assign the order") ;
                else{
                    return new ResponseEntity<>(seller, HttpStatus.OK) ;
                }
            }
        }
        else{
            throw  new ResourceNotFoundException("Order with Id: " + id+ " does not exist");
        }
    }




}
