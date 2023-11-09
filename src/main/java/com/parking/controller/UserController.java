package com.parking.controller;

import com.parking.entity.User;
import com.parking.entity.ParkingEntity;
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
    private User user;

    @GetMapping("/cliente")
    public List<ParkingEntity> getClientes(){
        return clienteServiceImplements.getAll();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ParkingEntity> saveCliente(@RequestBody ParkingEntity cliente){
        ParkingEntity new_cliente = clienteServiceImplements.guardar(cliente);
        new_cliente.getHoraLlegada();
        return new ResponseEntity<>(new_cliente, HttpStatus.CREATED);

    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ParkingEntity> getById(@PathVariable Long id){
        ParkingEntity clienteById =  clienteServiceImplements.getById(id);
        return ResponseEntity.ok(clienteById);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<ParkingEntity> update(@PathVariable Long id, @RequestBody ParkingEntity cliente){
        ParkingEntity clienteById = clienteServiceImplements.getById(id);
        clienteById.setComentario(cliente.getComentario());
        clienteById.setPlaca(cliente.getPlaca());

        ParkingEntity cliente_update = clienteServiceImplements.guardar(clienteById);
        return new ResponseEntity<>(cliente_update, HttpStatus.CREATED);

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<HashMap<String, Boolean>> deleteById(@PathVariable Long id ){
        this.clienteServiceImplements.deleteById(id);

        HashMap<String, Boolean> statusCliente = new HashMap<>();
        statusCliente.put("Eliminado", true);

        return  ResponseEntity.ok(statusCliente);
    }

    @PostMapping(value = "/processLogin")
    public String procesarFormulario(@ModelAttribute User user) {
        System.out.println("Funciona el User");
        System.out.println(user.toString());
        return "Home.html";
    }

}
