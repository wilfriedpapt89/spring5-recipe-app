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
        System.out.println("Start service ");
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        System.out.println("hashcode of uomTouomCommand " + unitOfMeasureToUnitOfMeasureCommand.hashCode());
        Iterable<UnitOfMeasure> results = unitOfMeasureRepository.findAll();
//        System.out.println("=========== results " + results.toString());
//        System.out.println("=========== results " + results.toString());
//        System.out.println("=========== results " + results.toString());
        List<UnitOfMeasureDto> unitOfMeasureDtos = new ArrayList<>();
        UnitOfMeasureMapper unitOfMeasureMapper = UnitOfMeasureMapper.INSTANCE;
        System.out.println(" ========= unitOfMeasureMapper " + unitOfMeasureMapper.hashCode());
        System.out.println("Size of result " + StreamSupport.stream(results.spliterator(), false).count());
        for (UnitOfMeasure uom : results) {
            System.out.println("result ++++++ " + uom);
            unitOfMeasureDtos.add(unitOfMeasureMapper.unitOfMeasureToUnitOfMeasureDto(uom));
        }
        StreamSupport.stream(results.spliterator(),false).forEach(System.out::println);
        unitOfMeasureDtos.forEach(System.out::println);


        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert).peek(uomcmd -> {
                    System.out.println("uomcmd id " + uomcmd.getId());
                    System.out.println("uomcmd descr " + uomcmd.getDescription());
                })
                .collect(Collectors.toSet());
    }
}
