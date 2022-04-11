package dev.ufuk.bakan.controllers;

import dev.ufuk.bakan.controllers.DTOs.SignupDTO;
import dev.ufuk.bakan.entities.Company;
import dev.ufuk.bakan.entities.enums.City;
import dev.ufuk.bakan.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody @Valid SignupDTO signupDTO){
        if(companyRepository.findAllByName(signupDTO.getName()).size() > 0) {
            return new ResponseEntity<String>("Company already exists", HttpStatus.CONFLICT);
        }else{
            companyRepository.save(new Company(signupDTO.getName(), signupDTO.getPassword(), City.valueOf(signupDTO.getCity())));
            return new ResponseEntity<String>("Successfully signed up", HttpStatus.OK);
        }
    }

}
