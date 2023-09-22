package com.humantalent.domain.mapper;

import com.humantalent.domain.dto.PersonDto;
import com.humantalent.domain.model.person.Person;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface PersonMapperConfig {
    void mapPerson(Person person, @MappingTarget PersonDto personDto);
}
