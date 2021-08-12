package com.dongzhic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 11/12 012.
 */
@Data
public class OrderEntry implements Serializable {

    private String id;

    private long money;

    private String userId;

    private int status = 0;

    private List<ProductEntry> productList = new ArrayList<>();

}
