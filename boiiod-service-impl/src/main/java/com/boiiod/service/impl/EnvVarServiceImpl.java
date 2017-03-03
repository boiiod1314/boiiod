package com.boiiod.service.impl;

import com.boiiod.mapper.EnvVarMapper;
import com.boiiod.service.EnvVarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("envVarService")
public class EnvVarServiceImpl implements EnvVarService {

    private EnvVarMapper envVarMapper;


    public EnvVarMapper getEnvVarMapper() {
        return this.envVarMapper;
    }

    @Autowired
    public void setEnvVarMapper(EnvVarMapper envVarMapper) {
        this.envVarMapper = envVarMapper;
    }
}