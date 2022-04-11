package dev.ufuk.bakan.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ufuk.bakan.entities.enums.City;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private long id;
    @NonNull // mark as required
    @Column(unique = true) // company name is unique
    private String name;
    @NonNull
    @ToString.Exclude
    @JsonIgnore
    private String password;
    @NonNull
    @Enumerated(value = EnumType.STRING)
    private City city;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude // since every vehicle ad has company field too, prevent infinite company.tostring->vehiclead.tostring->company.tostring-> loop
    @JsonIgnore // same for json response
    List<VehicleAd> vehicleAdList = new ArrayList<>();
}
