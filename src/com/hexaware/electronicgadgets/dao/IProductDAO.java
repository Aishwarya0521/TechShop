package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Product;
import java.util.List;

public interface IProductDAO {
    void addProduct(Product product);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
