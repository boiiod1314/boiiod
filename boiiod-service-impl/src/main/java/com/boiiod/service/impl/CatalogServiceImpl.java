package com.boiiod.service.impl;

import com.boiiod.mapper.CatalogMapper;
import com.boiiod.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    private CatalogMapper catalogMapper;


    public CatalogMapper getCatalogMapper() {
        return this.catalogMapper;
    }

    @Autowired
    public void setCatalogMapper(CatalogMapper catalogMapper) {
        this.catalogMapper = catalogMapper;
    }
}