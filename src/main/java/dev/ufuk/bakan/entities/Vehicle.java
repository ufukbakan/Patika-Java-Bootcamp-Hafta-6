package dev.ufuk.bakan.entities;

import javax.persistence.*;

import dev.ufuk.bakan.entities.enums.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String name;
    @Enumerated(EnumType.STRING)
    @NonNull
    private VehicleType type;

}
