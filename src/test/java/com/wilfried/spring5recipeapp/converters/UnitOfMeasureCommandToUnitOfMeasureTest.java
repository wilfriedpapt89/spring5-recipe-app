package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {


    private static final Long LONG_ID = 1L;
    private static final String DESCRIPTION = "description";
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testWithEmptyObject() {
        assertNotNull(unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(LONG_ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);
        UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);
        assertNotNull(unitOfMeasureCommand);
        assertEquals(LONG_ID, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}