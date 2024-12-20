package com.microstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Orders{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false,insertable=false, updatable=false)
    private int user_id;

    @Column(name = "status", nullable=false)
    private String status;

    @Column(name = "total", nullable=false)
    private int total;

    @Column(name = "created_at", nullable=false)
    private Date created_at;

}
