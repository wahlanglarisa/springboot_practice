package com.example.hotel_room_management.hotel_room_management.model.wrapper;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveGuestDetails {
    private Date checkInDate;
    private Date checkOutDate;
    private String guestName;
    private Long numberOfOccupants;
    private Long phoneNo;
    private String email;
}
