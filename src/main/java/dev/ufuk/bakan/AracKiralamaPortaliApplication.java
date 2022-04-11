package dev.ufuk.bakan;

import dev.ufuk.bakan.entities.AdditionalService;
import dev.ufuk.bakan.entities.Vehicle;
import dev.ufuk.bakan.entities.VehicleAd;
import dev.ufuk.bakan.entities.enums.VehicleType;
import dev.ufuk.bakan.repositories.VehicleAdRepository;
import dev.ufuk.bakan.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dev.ufuk.bakan.entities.Company;
import dev.ufuk.bakan.entities.enums.City;
import dev.ufuk.bakan.repositories.CompanyRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.ufuk.bakan.repositories") 
public class AracKiralamaPortaliApplication {
	public static HashMap<Long, Double> authenticationTokens = new HashMap<>();
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AracKiralamaPortaliApplication.class, args);

		CompanyRepository companyRepository = applicationContext.getBean(CompanyRepository.class);
		VehicleRepository vehicleRepository = applicationContext.getBean(VehicleRepository.class);
		VehicleAdRepository vehicleAdRepository = applicationContext.getBean(VehicleAdRepository.class);

		// ENTITY DEBUGS ::::
		Company ufukRentACar = new Company("ufukrentacar","123456", City.Ankara);

		System.out.println(companyRepository.findAll());
		companyRepository.save(ufukRentACar);
		System.out.println(companyRepository.findAll());

		Vehicle passat = new Vehicle("Passat", VehicleType.PASSANGER);

		System.out.println(vehicleRepository.findAll());
		vehicleRepository.save(passat);
		System.out.println(vehicleRepository.findAll());

		System.out.println(vehicleAdRepository.findAll());
		VehicleAd passatAdFor7days = new VehicleAd(passat, 1000.0, Duration.ofDays(7), new ArrayList<AdditionalService>(), ufukRentACar );
		ufukRentACar.getVehicleAdList().add(passatAdFor7days);
		companyRepository.save(ufukRentACar);
		System.out.println(vehicleAdRepository.findAll());

		// CASCADE DEBUG ::::

		passat.setType(VehicleType.BUSINESS);
		vehicleRepository.save(passat);
		System.out.println(vehicleRepository.findAll());
		System.out.println(vehicleAdRepository.findAll());
	}

}
