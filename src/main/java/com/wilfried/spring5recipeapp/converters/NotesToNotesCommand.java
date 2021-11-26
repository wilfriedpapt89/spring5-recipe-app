package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.NotesCommand;
import com.wilfried.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if(source == null)
        return null;
        else{
            final NotesCommand notesCommand = new NotesCommand();
            notesCommand.setId(source.getId());
            notesCommand.setRecipeNotes(source.getRecipeNotes());
            return notesCommand;
        }
    }
}
