package com.sanrio_store.sanrio_store.service;

import com.sanrio_store.sanrio_store.dto.request.BookingRequest;
import com.sanrio_store.sanrio_store.dto.response.BookingResponse;
import com.sanrio_store.sanrio_store.enums.BookingEnum;
import com.sanrio_store.sanrio_store.projection.BookingProjection;

import java.util.List;

public interface BookingService {

    Integer saveBooking(BookingRequest bookingRequest);

    List<BookingResponse> getBooking();

     Integer cancelBooking(Integer id);

    List<BookingEnum> getBookingEnums();

    List<BookingProjection> getBookingById(Integer id);

    Integer updateBooking(BookingRequest bookingRequest);
}
