package dev.ufuk.bakan.repositories;

import dev.ufuk.bakan.entities.VehicleAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleAdRepository extends JpaRepository<VehicleAd, Long> {
}
