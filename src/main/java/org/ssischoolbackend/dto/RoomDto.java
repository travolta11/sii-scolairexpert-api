package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.ssischoolbackend.model.Room;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String name;
    private int capacity;
    private List<Integer> materielIds;

    private final static ModelMapper modelMapper = new ModelMapper();

    public static RoomDto fromEntity(Room room) {
        return modelMapper.map(room, RoomDto.class);
    }

    public static Room toEntity(RoomDto roomDto) {
        return modelMapper.map(roomDto, Room.class);
    }
}
