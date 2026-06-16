package com.example.hotel_room_management.hotel_room_management.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCountByRoomStatus {
    private Long count;
    private String status;
}
