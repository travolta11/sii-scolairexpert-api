package org.ssischoolbackend.controller;

import org.ssischoolbackend.dto.RoomDto;
import org.ssischoolbackend.model.Room;
import org.ssischoolbackend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<RoomDto> roomDtos = roomService.getAllRooms().stream()
                .map(RoomDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createRoom(@RequestBody RoomDto roomDto) {
        Room room = RoomDto.toEntity(roomDto);
        Long id = roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(RoomDto.fromEntity(room));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        Room room = RoomDto.toEntity(roomDto);
        room.setId(id);
        roomService.updateRoom(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
