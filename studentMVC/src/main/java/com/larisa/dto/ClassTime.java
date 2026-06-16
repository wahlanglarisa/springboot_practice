package com.larisa.dto;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassTime {
	private UUID id;
    @JsonFormat(pattern = "hh:mm a")

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
    @JsonFormat(pattern = "hh:mm a")

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
}
