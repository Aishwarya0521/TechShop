package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.ProductDAOImpl;
import com.hexaware.electronicgadgets.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDAOImpl productDAO = new ProductDAOImpl();

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProduct(int productId) {
        return productDAO.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }
}
