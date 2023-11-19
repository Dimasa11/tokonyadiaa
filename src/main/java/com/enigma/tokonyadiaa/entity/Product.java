package com.enigma.tokonyadiaa.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "m_product")
public class Product {

    @Id
    @GenericGenerator(strategy = "uuid2" ,name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices;
}
