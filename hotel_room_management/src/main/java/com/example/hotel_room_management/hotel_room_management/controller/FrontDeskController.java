
package com.example.hotel_room_management.hotel_room_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotel_room_management.hotel_room_management.model.Room;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.GetCountByRoomStatus;
import com.example.hotel_room_management.hotel_room_management.service.RoomService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class FrontDeskController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/frontDesk/dashboard/{roomStatus}")
    public String frontdeskDashboardPage(Model model,@PathVariable("roomStatus") String roomStatus,HttpServletRequest httpServletRequest ) {
        List<Room> rooms=roomService.findByStatus(roomStatus);
        model.addAttribute("requestURI", httpServletRequest.getRequestURI());
        GetCountByRoomStatus occupiedRoomStatus=roomService.getCountByRoomStatus("Occupied");
        GetCountByRoomStatus availableRoomStatus=roomService.getCountByRoomStatus("Available");
        model.addAttribute("rooms", rooms);
        model.addAttribute("occRooms", occupiedRoomStatus);
        model.addAttribute("avRooms", availableRoomStatus);
        model.addAttribute("roomStatus", roomStatus);
        return "frontdesk_dashboard";
    }
    
}
