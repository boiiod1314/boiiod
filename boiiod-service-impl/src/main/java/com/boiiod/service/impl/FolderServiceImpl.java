package com.boiiod.service.impl;

import com.boiiod.mapper.FolderMapper;
import com.boiiod.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("folderService")
public class FolderServiceImpl implements FolderService {

    private FolderMapper folderMapper;


    public FolderMapper getFolderMapper() {
        return this.folderMapper;
    }

    @Autowired
    public void setFolderMapper(FolderMapper folderMapper) {
        this.folderMapper = folderMapper;
    }
}