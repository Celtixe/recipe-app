package com.recipe.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepo;
	private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;
		
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepo,
			UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
		this.unitOfMeasureRepo = unitOfMeasureRepo;
		this.uomToUomCommand = uomToUomCommand;
	}



	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		
		return StreamSupport.stream(unitOfMeasureRepo.findAll()
				.spliterator(),false)
				.map(uomToUomCommand::convert)
				.collect(Collectors.toSet());
	}

}
