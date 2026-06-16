package com.example.student.service;

import java.util.List;

import com.example.student.model.ClassTime;
import com.example.student.model.wrapper.GetAvailClassTime;

public interface ClassTimeService {
    public List<GetAvailClassTime> getAvailClassTimes(String day,long br_id,long semester);

}
