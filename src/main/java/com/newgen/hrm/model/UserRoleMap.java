package com.newgen.hrm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "user_role")
@Entity
public class UserRoleMap {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long roleId;
    private Long userId;
}
