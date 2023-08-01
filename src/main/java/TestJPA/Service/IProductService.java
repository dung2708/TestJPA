package TestJPA.Service;

import TestJPA.Model.responobject.Respon;

public interface IProductService {
    public Respon<String> buyProductDetail(int productDetailId, int quantity);
}
