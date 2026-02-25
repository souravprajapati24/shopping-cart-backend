package com.sourav.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
    private String fileType;

    @Lob // long obj=ject
    private Blob image;
    private String downloadUrl;

    @ManyToOne // many images belongs to one product
    @JoinColumn(name = "product_id")  // used to define the forigen key relation of product
    private Product product;
}
