package com.example.BOO.Service;

import com.example.BOO.Config.TwilioConfiguration;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Client;
import com.example.BOO.Model.Order;
import com.example.BOO.Repository.OrderRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository ;

    @Autowired
    TwilioConfiguration twilioConfiguration ;

    public Message.Status notifyOrderReady(Integer orderId){
        Optional<Order> orderOptional = orderRepository.findById(orderId) ;
        if(orderOptional.isPresent()){
            Order order= orderOptional.get() ;
            Client client = order.getClient();
            Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

            Message message = Message.creator( new PhoneNumber(client.getNumber()),
                    new PhoneNumber(twilioConfiguration.getNumber()),
                    String.format("Hello! \nWe inform you that your order is ready. You can come and take it! \nYou are the client with id: %d. \nThank You!", client.getId())) .create() ;
            return message.getStatus() ;
        }
        else throw new ResourceNotFoundException("Product with Id: " + orderId+ " does not exist") ;
    }



}
