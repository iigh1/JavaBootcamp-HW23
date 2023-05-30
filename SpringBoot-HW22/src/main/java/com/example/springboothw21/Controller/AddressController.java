package com.example.springboothw21.Controller;


import com.example.springboothw21.DTO.AddressDTO;
import com.example.springboothw21.Model.Address;
import com.example.springboothw21.Repository.AddressRepository;
import com.example.springboothw21.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping("/add")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDTO dto){
        addressService.addAddress(dto);
        return ResponseEntity.status(200).body("address added");
    }

    @PutMapping("update")
    public ResponseEntity updateAddress(@Valid @RequestBody AddressDTO dto){
        addressService.updateAddress(dto);
        return ResponseEntity.status(200).body("address updated");
    }


}
