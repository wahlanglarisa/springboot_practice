package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.QualificationDao;
import com.larisa.dto.Qualification;
@Service
public class QualificationServiceImpl implements QualificationService{
    @Autowired
    private QualificationDao qualificationDao;
    @Override
    public List<Qualification> getQualifications() {
        // TODO Auto-generated method stub
        return qualificationDao.getQualifications();
    }

}
