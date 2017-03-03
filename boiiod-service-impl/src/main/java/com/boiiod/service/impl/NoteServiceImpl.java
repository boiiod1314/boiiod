package com.boiiod.service.impl;

import com.boiiod.mapper.NoteMapper;
import com.boiiod.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

    private NoteMapper noteMapper;

    public NoteMapper getNoteMapper() {
        return this.noteMapper;
    }

    @Autowired
    public void setNoteMapper(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
}