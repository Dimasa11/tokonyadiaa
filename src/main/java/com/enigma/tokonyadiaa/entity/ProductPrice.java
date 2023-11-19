package com.enigma.tokonyadiaa.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_product_price")
@Builder(toBuilder = true)
public class ProductPrice {

    @Id
    @GenericGenerator(strategy = "uuid2" ,name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "stock",nullable = false,length = 255)
    private Integer stock;

    @Column(name = "price",nullable = false,length = 255)
    private Long price;

    @Column(name = "isActive",nullable = false,length = 255)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "product_id")//KITA MEMBUAT FOREIGN KEY
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")//KITA MEMBUAT FOREIGN KEY
    private  Store store;
}
