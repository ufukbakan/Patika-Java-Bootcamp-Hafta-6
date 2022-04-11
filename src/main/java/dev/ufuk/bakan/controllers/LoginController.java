package dev.ufuk.bakan.controllers;

import dev.ufuk.bakan.AracKiralamaPortaliApplication;
import dev.ufuk.bakan.controllers.DTOs.LoginDTO;
import dev.ufuk.bakan.entities.Company;
import dev.ufuk.bakan.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO){
        Optional<Company> found = companyRepository.findByNameAndPassword(loginDTO.getCompanyName(), loginDTO.getPassword());
        if(found.isPresent()){
            double authNumber = Math.floor(Math.random()*Math.pow(10,18));
            AracKiralamaPortaliApplication.authenticationTokens.put(found.get().getId(), authNumber);
            return new ResponseEntity<String>(String.format("%018d", authNumber), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed to login", HttpStatus.UNAUTHORIZED);
    }

    //@RequestHeader Map<String, String> headers

}
