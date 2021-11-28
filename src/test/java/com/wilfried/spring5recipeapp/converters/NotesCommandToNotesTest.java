package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.NotesCommand;
import com.wilfried.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long LONG_ID = 1L;
    public static final String RECIPE_NOTE = "Description";
    NotesCommandToNotes notesCommandToNotes;

    @Test
    void testWithEmptyObject() {
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(notesCommandToNotes.convert(null));
    }

    @BeforeEach
    void setUp() {
        notesCommandToNotes = new NotesCommandToNotes();
    }

    @Test
    void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(LONG_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTE);
        Notes notes = notesCommandToNotes.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(LONG_ID, notes.getId());
        assertEquals(RECIPE_NOTE, notes.getRecipeNotes());
    }
}