package dev.ufuk.bakan.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class VehicleAd {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    private Vehicle vehicle;
    @NonNull
    private double price;
    @NonNull
    Duration validForDuration;
    @CreationTimestamp
    LocalDateTime creationTime;
    /* POST CONTROLLER İSTEĞİ ÇOK MALİYETLİ OLDUĞU İÇİN KALDIRDIM :
    @ElementCollection
    @NonNull
    List<LocalDate> availableDays;
     */
    @NonNull
    @ElementCollection
    @LazyCollection(value = LazyCollectionOption.FALSE) // prevents simultaneous eager fetch bag
    List<AdditionalService> additionalServiceList;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    Company owner;
}
