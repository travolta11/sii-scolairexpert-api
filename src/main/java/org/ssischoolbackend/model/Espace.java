package org.ssischoolbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Espace {
    private Long id;
    private String name;

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

	public static Espace baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Espace espace = new Espace();
        espace.setId(resultSet.getLong("espace_id"));
        espace.setName(resultSet.getString("espace_name"));
        return espace;
    }

}
