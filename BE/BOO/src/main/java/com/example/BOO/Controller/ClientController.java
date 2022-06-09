package com.example.BOO.Controller;

import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Category;
import com.example.BOO.Model.Client;
import com.example.BOO.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository ;


    @GetMapping()
    public List<Client> getClients(){
         return clientRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id){
        return clientRepository.findById(id).map(client -> {
            return new ResponseEntity<>(client , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Client with Id: " + id+ " does not exist")) ;
    }

    @PostMapping
    public  ResponseEntity<Client> createClient(@RequestBody Client client){
         return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient ( @PathVariable Integer id, @RequestBody Client client){
        Optional<Client> cli = clientRepository.findById(id) ;
        if(cli.isEmpty() ){
            throw new ResourceNotFoundException("Client with id: " + id+ " does not exist") ;
        }
        else {
            Client newClient  = cli.get() ;
            newClient.setNumber(client.getNumber());
            newClient.setRole(client.getRole());
            return   new ResponseEntity<>(createClient(newClient).getBody(), HttpStatus.OK) ;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient ( @PathVariable Integer id){
        Optional<Client> cli = clientRepository.findById(id) ;
        if(cli.isEmpty() ){
            throw new ResourceNotFoundException("Client with id: " + id+ " does not exist") ;
        }
        else {
            clientRepository.deleteById(id);
            return   new ResponseEntity<>(cli.get(), HttpStatus.OK) ;
        }
    }



}
