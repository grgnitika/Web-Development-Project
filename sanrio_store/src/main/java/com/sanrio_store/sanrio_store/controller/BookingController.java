package com.sanrio_store.sanrio_store.controller;

import com.sanrio_store.sanrio_store.dto.request.BookingRequest;
import com.sanrio_store.sanrio_store.entity.UserEntity;
import com.sanrio_store.sanrio_store.helper.ApiResponse;
import com.sanrio_store.sanrio_store.service.BookingService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    public BookingController(BookingService bookingService, ApiResponse apiResponse){
        this.bookingService = bookingService;
        this.apiResponse = apiResponse;
    }
    private final ApiResponse apiResponse;
    @PostMapping
    public ResponseEntity<Map<String,Object>> saveBooking(@RequestBody @Valid BookingRequest bookingRequest){
        return apiResponse.successResponse("Booking Successful",true,null,bookingService.saveBooking(bookingRequest));
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getBooking(Authentication authentication){
        UserEntity userEntity=(UserEntity) authentication.getPrincipal();
        return apiResponse.successResponse("Booking list fetched successfully",true,null,bookingService.getBooking());
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Map<String,Object>> cancelBooking(@PathVariable Integer id){
        return apiResponse.successResponse("Booking canceled successfully",true,null,bookingService.cancelBooking(id));
    }

    @GetMapping("/getEnums")
    public ResponseEntity<Map<String,Object>> getBookingEnums(){
        return apiResponse.successResponse("Booking enums fetched successfully",true,null,bookingService.getBookingEnums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getBookingById(@PathVariable Integer id){
        return apiResponse.successResponse("Booking item fetched successfully",true,null,bookingService.getBookingById(id));
    }

    @PutMapping
    public ResponseEntity<Map<String,Object>> updateBooking(@RequestBody BookingRequest bookingRequest){
        return apiResponse.successResponse("Booking has been updated successfully successfully",true,null,bookingService.updateBooking(bookingRequest));
    }

}
