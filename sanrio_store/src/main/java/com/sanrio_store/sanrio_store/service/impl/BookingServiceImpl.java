package com.sanrio_store.sanrio_store.service.impl;

import com.sanrio_store.sanrio_store.dto.request.BookingRequest;
import com.sanrio_store.sanrio_store.dto.response.BookingResponse;
import com.sanrio_store.sanrio_store.entity.BookingEntity;
import com.sanrio_store.sanrio_store.entity.CustomerEntity;
import com.sanrio_store.sanrio_store.entity.ProductEntity;
import com.sanrio_store.sanrio_store.enums.BookingEnum;
import com.sanrio_store.sanrio_store.exception.ResourceNotFoundException;
import com.sanrio_store.sanrio_store.mapper.BookingMapper;
import com.sanrio_store.sanrio_store.projection.BookingProjection;
import com.sanrio_store.sanrio_store.repo.BookingRepo;
import com.sanrio_store.sanrio_store.repo.CustomerRepo;
import com.sanrio_store.sanrio_store.repo.ProductRepo;
import com.sanrio_store.sanrio_store.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    private final BookingRepo bookingRepo;
    private final BookingMapper bookingMapper;


    public BookingServiceImpl(ProductRepo productRepo, CustomerRepo customerRepo, BookingRepo bookingRepo, BookingMapper bookingMapper) {
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Integer saveBooking(BookingRequest bookingRequest) {
        ProductEntity productEntity = productRepo.findById(bookingRequest.getItemId()).orElseThrow(()-> new ResourceNotFoundException("Invalid item id"));
        CustomerEntity customerEntity = customerRepo.findById(bookingRequest.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Invalid customer id"));
        if(productEntity.getAvailableQuantity()<bookingRequest.getQuantity()){
            throw new ResourceNotFoundException("Available quantity is less than request quantity");
        }
        try{
            Double totalPrice = productEntity.getPrice()*bookingRequest.getQuantity();
            productEntity.setAvailableQuantity(productEntity.getAvailableQuantity()-bookingRequest.getQuantity());
            productRepo.save(productEntity);
            BookingEntity bookingEntity = BookingEntity.builder()
                    .status(BookingEnum.PENDING)
                    .quantity(bookingRequest.getQuantity())
                    .customerEntity(customerEntity)
                    .productEntity(productEntity)
                    .totalPrice(totalPrice)
                    .build();
            return bookingRepo.save(bookingEntity).getId();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    @Override
    public List<BookingResponse> getBooking() {
        List<BookingResponse> bookingList = bookingMapper.getAll();
        return bookingList;
    }

    @Override
    public Integer cancelBooking(Integer id) {
        BookingEntity bookingEntity = bookingRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid booking id"));
        ProductEntity productEntity = productRepo.findById(bookingEntity.getProductEntity().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid item id"));
        try {
            if (bookingEntity.getStatus().equals(BookingEnum.PENDING)) {
                bookingEntity.setStatus(BookingEnum.CANCEL);
                bookingRepo.save(bookingEntity);
                productEntity.setAvailableQuantity(productEntity.getAvailableQuantity() + bookingEntity.getQuantity());
                productRepo.save(productEntity);
            } else {
                throw new ResourceNotFoundException("Booking can not be canceled");
            }
        }
        catch (Exception ex){
            throw ex;
        }
        return null;
    }

    @Override
    public List<BookingEnum> getBookingEnums() {
        return Arrays.stream(BookingEnum.values()).toList();
    }

    @Override
    public List<BookingProjection> getBookingById(Integer id) {
        BookingEntity bookingEntity = bookingRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid booking id"));
        return bookingRepo.getBookingEntitiesById(bookingEntity.getId());
    }

    @Override
    public Integer updateBooking(BookingRequest bookingRequest) {
        BookingEntity bookingEntity = bookingRepo.findById(bookingRequest.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid booking id"));
        if(bookingEntity.getStatus().equals(BookingEnum.COMPLETED)){
            throw new ResourceNotFoundException("Booking  status already completed so can not update");
        }
        ProductEntity productEntity = productRepo.findById(bookingEntity.getProductEntity().getId()).orElseThrow(()->new ResourceNotFoundException("Item not found"));
        try{
            if(bookingEntity.getStatus().equals(BookingEnum.CANCEL)){
                if(bookingRequest.getStatus().compareTo(BookingEnum.CANCEL.name())==0){
                    throw new ResourceNotFoundException("Booking status already canceled.");
                }else{
                    if(productEntity.getAvailableQuantity()<bookingEntity.getQuantity()){
                        throw new ResourceNotFoundException("Available quantity is less than request quantity");
                    }
                    productEntity.setAvailableQuantity(productEntity.getAvailableQuantity()-bookingEntity.getQuantity());
                    productRepo.save(productEntity);
                    bookingEntity.setStatus(BookingEnum.valueOf(bookingRequest.getStatus()));
                    bookingRepo.save(bookingEntity);
                }
            }else{
                if(bookingRequest.getStatus().compareTo(BookingEnum.CANCEL.name())==0){
                    productEntity.setAvailableQuantity(productEntity.getAvailableQuantity()+bookingEntity.getQuantity());
                    productRepo.save(productEntity);
                }
                bookingEntity.setStatus(BookingEnum.valueOf(bookingRequest.getStatus()));
                bookingRepo.save(bookingEntity);
            }
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return bookingEntity.getId();
    }


}
