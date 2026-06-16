
package com.example.student.model.wrapper;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAvailClassTime {
     private long id;
    @DateTimeFormat(pattern = "HH:mm") // 24-hour format
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm") // 24-hour format
    private LocalTime endTime;
}
