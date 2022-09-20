package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import com.wilfried.spring5recipeapp.dto.UnitOfMeasureDto;
import com.wilfried.spring5recipeapp.mapper.UnitOfMeasureMapper;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        Iterable<UnitOfMeasure> results = unitOfMeasureRepository.findAll();

        List<UnitOfMeasureDto> unitOfMeasureDtos = new ArrayList<>();
        UnitOfMeasureMapper unitOfMeasureMapper = UnitOfMeasureMapper.INSTANCE;

        for (UnitOfMeasure uom : results) {
            unitOfMeasureDtos.add(unitOfMeasureMapper.unitOfMeasureToUnitOfMeasureDto(uom));
        }

        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert).peek(uomcmd -> {
                })
                .collect(Collectors.toSet());
    }
}
