package com.tutorials.msbooking.service;

import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingRsModel createBooking(BookingRqModel request, String username);

    BookingRsModel updateBooking(BookingRqModel request, UUID id, String username);

    List<BookingRsModel> getAllBookings(String username);

    BookingRsModel getBookingByIdAndUser(UUID id, String username);

    BookingRsModel deleteBooking(UUID id, String username);

    List<BookingRsModel> deleteBatchBooking(UUID flightId);
}
