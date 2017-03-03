package com.boiiod.service.impl;

import com.boiiod.mapper.NoteDetailMapper;
import com.boiiod.service.NoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noteDetailService")
public class NoteDetailServiceImpl implements NoteDetailService {

    private NoteDetailMapper noteDetailMapper;


    public NoteDetailMapper getNoteDetailMapper() {
        return this.noteDetailMapper;
    }

    @Autowired
    public void setNoteDetailMapper(NoteDetailMapper noteDetailMapper) {
        this.noteDetailMapper = noteDetailMapper;
    }
}