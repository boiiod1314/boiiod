package com.boiiod.service.impl;

import com.boiiod.mapper.UniteMapper;
import com.boiiod.service.UniteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("uniteService")
public class UniteServiceImpl implements UniteService {

    private UniteMapper uniteMapper;


    public UniteMapper getUniteMapper() {
        return this.uniteMapper;
    }

    @Autowired
    public void setUniteMapper(UniteMapper uniteMapper) {
        this.uniteMapper = uniteMapper;
    }
}