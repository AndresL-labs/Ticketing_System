package com.example.ticketing_system.mapper;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    Venue toEntity(RequestVenueDTO venueDTO);
    ResponseVenueDTO toDTO(Venue venue);
    List<ResponseVenueDTO> toDTOList(List<Venue> venues);
}
