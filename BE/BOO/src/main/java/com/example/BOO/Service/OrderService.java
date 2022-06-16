package com.example.BOO.Service;

import com.example.BOO.Config.TwilioConfiguration;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.*;
import com.example.BOO.Repository.BillRepository;
import com.example.BOO.Repository.OrderRepository;
import com.example.BOO.Repository.SellerRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository ;

    @Autowired
    TwilioConfiguration twilioConfiguration ;

    @Autowired
    BillRepository billRepository ;

    @Autowired
    SellerRepository sellerRepository ;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Message.Status notifyOrderReady(Integer orderId){
        Optional<Order> orderOptional = orderRepository.findById(orderId) ;
        if(orderOptional.isPresent()){
            Order order= orderOptional.get() ;
            Client client = order.getClient();
            order.setPrepareTime(new Date());
            Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

            Message message = Message.creator( new PhoneNumber(client.getNumber()),
                    new PhoneNumber(twilioConfiguration.getNumber()),
                    String.format("Hello! \nWe inform you that your order is ready. You can come and take it! \nYou are the client with id: %d. \nThank You!", client.getId())) .create() ;
            try{
                orderRepository.save(order) ;
            }catch (Exception ex){
                ex.printStackTrace();
            }


            return message.getStatus() ;
        }
        else throw new ResourceNotFoundException("Product with Id: " + orderId+ " does not exist") ;
    }


    public Bill confirmOrder(Order order){

        if(order!=null && order.getSells().size()>0){

           Bill bill = new Bill() ;

           List<Sell> sells = order.getSells();

           List <BillProduct> billProducts = new ArrayList<>() ;

           for (Sell s : sells){
               BillProduct billProduct  = new BillProduct() ;
               billProduct.setBill(bill);
               billProduct.setProduct(s.getProduct());
               billProduct.setAmount(s.getAmount());
               billProduct.setPrice(s.getProduct().getPrice());
               billProduct.setProductName(s.getProduct().getName());
               billProducts.add(billProduct) ;
           }

           bill.setBillProducts(billProducts);
           bill.setSeller(order.getSeller());
           bill.setCreatedTime(new Date());

           bill = billRepository.save(bill) ;
           orderRepository.deleteById(order.getId());
           return  bill ;

        }else {
            return  null ;
        }
    }

    public  Seller asignOrderToSeller(Order order){
        List<Seller> sellers = sellerRepository.findAll().stream().filter(seller -> seller.getOnWork()).collect(Collectors.toList());
        if (sellers.size()>0){
            int sellerIndex = randInt(0 , sellers.size()-1) ;
            Seller seller = sellers.get(sellerIndex) ;
            order.setSeller(seller);
            order.setDeliveryTime(new Date());
            seller.getOrders().add(order) ;
            seller = sellerRepository.save(seller);
            return  seller ;
        }
        return null;
    }

    public static int randInt(int min, int max) {
        if(max > min){
            Random rand = new Random();

            int randomNum = rand.nextInt((max - min) + 1) + min;

            return randomNum;
        }
        else throw new RuntimeException("Max should be grater than min") ;

    }


}
