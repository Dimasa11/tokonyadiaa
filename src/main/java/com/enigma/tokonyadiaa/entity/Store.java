package com.enigma.tokonyadiaa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;


@Table(name = "m_store")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GenericGenerator(strategy = "uuid2" ,name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    @Column(name = "no_siup" , unique = true , nullable = false)
    private String noSiup;
    @Column(name = "name" , length = 50 , nullable = false)
    private String name;
    @Column(name = "address", length = 100, nullable = true)
    private  String address;
    @Column(name = "phone" , unique = true, nullable = false)
    private String phone;
}
