package com.tutorials.msbooking.repository;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.enums.FlightStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByExternalIdAndUserAndActiveTrue(UUID id, User user);

    List<Booking> findAllByUserAndActiveTrue(User user);

    List<Booking> findAllByFlight_ExternalIdAndActiveTrue(UUID flightId);

    List<Booking> findAllByFlight_StatusAndActiveTrue(FlightStatus flightStatus);

    default List<Booking> findAllByFlightId(UUID flightId) {
        return findAllByFlight_ExternalIdAndActiveTrue(flightId);
    }

    default List<Booking> findAllByFlightStatus(FlightStatus flightStatus) {
        return findAllByFlight_StatusAndActiveTrue(flightStatus);
    }

    default Optional<Booking> findByIdAndUser(UUID id, User user) {
        return findByExternalIdAndUserAndActiveTrue(id, user);
    }

    @Modifying
    @Query(" UPDATE Booking b SET b.active = false WHERE b.flight.id = :flightId")
    void updateBookingActiveFalse(UUID flightId);
}
