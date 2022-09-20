package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    public UnitOfMeasureToUnitOfMeasureCommand() {
    }

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {

        if (source == null) {
            return null;
        } else {
            final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
            uom.setId(source.getId());
            uom.setDescription(source.getDescription());
            return uom;
        }
    }
}
