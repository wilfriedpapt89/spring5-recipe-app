package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

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
        System.out.println("Start service ");
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        System.out.println("hashcode of uomTouomCommand " + unitOfMeasureToUnitOfMeasureCommand.hashCode());
       return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert).peek(uomcmd -> {
                   System.out.println("uomcmd id " + uomcmd.getId());
                   System.out.println("uomcmd descr " + uomcmd.getDescription());
               })
                .collect(Collectors.toSet());
    }
}
