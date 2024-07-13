package by.andersen.chronology.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coordinates")
public class CoordinateEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Double latitude;
    
    @Column(nullable = false)
    private Double longitude;
}
