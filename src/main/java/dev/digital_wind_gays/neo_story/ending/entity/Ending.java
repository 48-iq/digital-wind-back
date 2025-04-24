package dev.digital_wind_gays.neo_story.ending.entity;

import dev.digital_wind_gays.neo_story.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "endings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ending {
    @Id
    private String id;

    @ManyToOne
    private User user;
}
