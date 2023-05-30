package com.example.springboothw21.Service;


import com.example.springboothw21.ApiException.ApiException;
import com.example.springboothw21.DTO.AddressDTO;
import com.example.springboothw21.Model.Address;
import com.example.springboothw21.Model.Teacher;
import com.example.springboothw21.Repository.AddressRepository;
import com.example.springboothw21.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;


    public void addAddress(AddressDTO dto){
        Teacher teacher=teacherRepository.findTeacherById(dto.getTeacher_id());

        if (teacher==null){
            throw new ApiException("can't add address, teacher not found");
        }
        Address address =new Address(null,dto.getArea(),dto.getStreet(),dto.getBuildingNumber(),teacher);
        addressRepository.save(address);
    }

    public void updateAddress( AddressDTO dto){
        Address olsAddress = addressRepository.findAddressById(dto.getTeacher_id());
        if (olsAddress==null){
            throw new ApiException("address not found");
        }
        olsAddress.setArea(dto.getArea());
        olsAddress.setStreet(dto.getStreet());
        olsAddress.setBuildingNumber(dto.getBuildingNumber());

       addressRepository.save(olsAddress);

    }


}
