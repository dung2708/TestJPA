package TestJPA.Service;

import TestJPA.Model.ProductDetails;
import TestJPA.Model.responobject.Respon;

import java.util.List;

public interface IProductDetailService {
    public Respon<String> updateProductQuantity(int productDetailId, int newQuantity);

    public Respon<List<ProductDetails>> getAllProductDetailLast(int productDetailId);
}
