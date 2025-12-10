package com.example.hotel_room_management.hotel_room_management.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
      @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime checkInDate;
     @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime checkOutDate;
    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;
    private Long numberOfOccupants;

}
