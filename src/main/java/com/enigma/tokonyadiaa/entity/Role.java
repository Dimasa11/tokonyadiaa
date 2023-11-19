package com.enigma.tokonyadiaa.entity;

import com.enigma.tokonyadiaa.entity.constanst.ERole;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_role")
public class Role {


    @Id
    @GenericGenerator(strategy = "uuid2" ,name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "role_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole eRole;
}
