package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    UnitOfMeasureServiceImpl unitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {

        //Given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasures.add(unitOfMeasure);


        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasures.add(unitOfMeasure2);

        UnitOfMeasure unitOfMeasure3 = new UnitOfMeasure();
        unitOfMeasure3.setId(3L);
        unitOfMeasures.add(unitOfMeasure3);

        Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //When
        Set<UnitOfMeasureCommand> commands = unitOfMeasureService.listAllUoms();

        assertEquals(3, commands.size());
    }
}