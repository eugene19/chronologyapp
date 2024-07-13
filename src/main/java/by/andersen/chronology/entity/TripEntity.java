package by.andersen.chronology.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "trips")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordinateEntity> coordinates;

    @Column(nullable = false)
    private String owner;

    @ElementCollection
    private List<String> guests;
}