package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.NotesCommand;
import com.wilfried.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long LONG_ID = 1L;
    public static final String RECIPE_NOTE = "Description";
    NotesToNotesCommand notesToNotesCommand;

    @Test
    void testWithEmptyObject() {
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(notesToNotesCommand.convert(null));
    }

    @BeforeEach
    void setUp() {
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(LONG_ID);
        notes.setRecipeNotes(RECIPE_NOTE);
        NotesCommand notesCommand = notesToNotesCommand.convert(notes);

        assertNotNull(notesCommand);
        assertEquals(LONG_ID, notesCommand.getId());
        assertEquals(RECIPE_NOTE, notesCommand.getRecipeNotes());
    }
}