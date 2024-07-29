package org.ssischoolbackend.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parent {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
