package org.ssischoolbackend.model;

import java.util.List;

public class Room {
    private Long id;
    private String name;
    private int capacity;
    private List<Integer> materielIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Integer> getMaterielIds() {
        return materielIds;
    }

    public void setMaterielIds(List<Integer> materielIds) {
        this.materielIds = materielIds;
    }
}
