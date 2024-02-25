package com.sanrio_store.sanrio_store.projection;

import com.sanrio_store.sanrio_store.enums.BookingEnum;

public interface BookingProjection {
    Integer getQuantity();

    String getCategoryName();

    BookingEnum getStatus();

    String getEmail();

    Integer getId();

    Double getTotalPrice();

    String getFullName();

    String getItemName();
}
