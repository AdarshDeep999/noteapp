package com.flexira.notesapp.Service;

import com.flexira.notesapp.Model.Note;
import com.flexira.notesapp.Repository.NoteRepository;
import com.flexira.notesapp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoteServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;

    public NoteServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNote() {
        Note note = new Note();
        note.setTitle("Test");
        note.setContent("Testing");
        note.setUsername("Tester");

        when(userRepository.existsByUsername("Tester")).thenReturn(true);
        when(noteRepository.save(note)).thenReturn(note);

        Note saved = noteService.createNote(note);

        assertEquals("Test", saved.getTitle());
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testGetUserNotes(){
        Note note1= new Note();
        note1.setTitle("T1");
        note1.setContent("Test1");
        note1.setUsername("Tester1");

        Note note2= new Note();
        note2.setTitle("T2");
        note2.setContent("Test2");
        note2.setUsername("Tester1");

        when(userRepository.existsByUsername("Tester1")).thenReturn(true);
        when(noteRepository.findByUsername("Tester1")).thenReturn(List.of(note1, note2));

        List<Note> savedNotes = noteService.getUserNote("Tester1"); //or we should check size of list or any random field
        assertEquals(List.of(note1,note2),savedNotes);
        verify(noteRepository, times(1)).findByUsername("Tester1");


    }
}
