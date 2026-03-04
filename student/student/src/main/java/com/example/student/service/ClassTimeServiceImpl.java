package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.ClassTime;
import com.example.student.model.wrapper.GetAvailClassTime;
import com.example.student.repository.ClassTimeRepository;

@Service
public class ClassTimeServiceImpl implements ClassTimeService{
    @Autowired
    private ClassTimeRepository classTimeRepository;
    @Override
    public List<GetAvailClassTime> getAvailClassTimes(String day,long br_id,long semester) {
        // TODO Auto-generated method stub
        return classTimeRepository.getAvailableClassTime(day,br_id,semester);
    }

}
