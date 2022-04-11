package dev.ufuk.bakan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ufuk.bakan.entities.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    public Optional<Company> findByNameAndPassword(String name, String password);
    public List<Company> findAllByName(String name);
}
