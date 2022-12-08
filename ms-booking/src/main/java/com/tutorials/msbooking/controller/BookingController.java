package com.tutorials.msbooking.controller;

import static com.tutorials.msbooking.util.Constants.BOOKINGS_URL;
import static com.tutorials.msbooking.util.Constants.PATH_ID;
import static com.tutorials.msbooking.util.Constants.REQUEST_LOG_FORMAT;
import static com.tutorials.msbooking.util.Constants.RESPONSE_LOG_FORMAT;

import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import com.tutorials.msbooking.model.ExtractJwtRsModel;
import com.tutorials.msbooking.service.BookingService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(BOOKINGS_URL)
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingRsModel> createBooking(Authentication auth,
                                                        @Valid @RequestBody BookingRqModel requestBody) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL, requestBody);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.createBooking(requestBody, username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<BookingRsModel> updateBooking(Authentication auth,
                                                        @Valid @RequestBody BookingRqModel requestBody,
                                                        @PathVariable UUID id) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL + PATH_ID, requestBody);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.updateBooking(requestBody, id, username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingRsModel>> getAllBookings(Authentication auth) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL, null);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.getAllBookings(username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<BookingRsModel> getBooking(Authentication auth, @PathVariable UUID id) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL + PATH_ID, null);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.getBookingByIdAndUser(id, username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<BookingRsModel> deleteBooking(Authentication auth, @PathVariable UUID id) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL + PATH_ID, null);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.deleteBooking(id, username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<List<BookingRsModel>> deleteBookings(@RequestParam(name = "flight-id") UUID flightId) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL + "?flight-id=" + flightId, null);

        var response = bookingService.deleteBatchBooking(flightId);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL + "?flight-id=" + flightId, response);
        return ResponseEntity.ok().body(response);
    }

}
