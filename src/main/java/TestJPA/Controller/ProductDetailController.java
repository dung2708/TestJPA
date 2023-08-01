package TestJPA.Controller;

import TestJPA.Model.ProductDetails;
import TestJPA.Model.responobject.Respon;
import TestJPA.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/productdetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @RequestMapping(value = "updatequantity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Respon<String> updateProductQuantity(@RequestParam int productDetailId,@RequestParam int addQuantity) {
        return productDetailService.updateProductQuantity(productDetailId, addQuantity);
    }

    @RequestMapping(value = "laythongtinsanphamdoicuoicung", method = RequestMethod.GET)
    public Respon<List<ProductDetails>> getAllProductDetailLast(@RequestParam int productDetailId) {
        return productDetailService.getAllProductDetailLast(productDetailId);
    }
}
