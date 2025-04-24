package dev.digital_wind_gays.neo_story.ending.repository;

import dev.digital_wind_gays.neo_story.ending.entity.Ending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndingRepository extends JpaRepository<Ending, String> {

    @Query("SELECT e FROM Ending e WHERE e.user.id = :userId")
    List<Ending> findByUserId(String userId);
}
