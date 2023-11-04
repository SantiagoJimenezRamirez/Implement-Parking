package com.parking.controller;

import com.parking.entity.UserEntity;
import com.parking.service.ClienteServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    ClienteServiceImplements clienteServiceImplements;

    @GetMapping("/cliente")
    public List<UserEntity> getClientes(){
        return clienteServiceImplements.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<UserEntity> saveCliente(@RequestBody UserEntity cliente){
        UserEntity new_cliente = clienteServiceImplements.guardar(cliente);
        new_cliente.getHoraLlegada();
        return new ResponseEntity<>(new_cliente, HttpStatus.CREATED);

    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id){
        UserEntity clienteById =  clienteServiceImplements.getById(id);
        return ResponseEntity.ok(clienteById);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody UserEntity cliente){
        UserEntity clienteById = clienteServiceImplements.getById(id);
        clienteById.setComentario(cliente.getComentario());
        clienteById.setPlaca(cliente.getPlaca());

        UserEntity cliente_update = clienteServiceImplements.guardar(clienteById);
        return new ResponseEntity<>(cliente_update, HttpStatus.CREATED);

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<HashMap<String, Boolean>> deleteById(@PathVariable Long id ){
        this.clienteServiceImplements.deleteById(id);

        HashMap<String, Boolean> statusCliente = new HashMap<>();
        statusCliente.put("Eliminado", true);

        return  ResponseEntity.ok(statusCliente);
    }

}
