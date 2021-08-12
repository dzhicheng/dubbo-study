package com.dongzhic.service;

import com.dongzhic.entity.ProductEntry;

public interface ProductService {

    ProductEntry getDetail(String id);

    ProductEntry modify(ProductEntry product);

    boolean status(String id, boolean upDown);

}
