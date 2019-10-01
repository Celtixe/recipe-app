package com.recipe.services;

import java.util.Set;

import com.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
