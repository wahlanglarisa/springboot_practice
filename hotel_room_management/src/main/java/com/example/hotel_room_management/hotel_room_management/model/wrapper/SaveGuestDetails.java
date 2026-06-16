package com.example.hotel_room_management.hotel_room_management.model.wrapper;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveGuestDetails {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    private String guestName;
    private Long numberOfOccupants;
    private Long phoneNo;
    private String email;
}
