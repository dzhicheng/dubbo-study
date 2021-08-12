package com.dongzhic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Peter on 11/12 012.
 */
@Data
public class ProductEntry implements Serializable {

    private String id;

    private long price;

    private String name;
    /**
     * 上下架
     */
    private int status;

}
