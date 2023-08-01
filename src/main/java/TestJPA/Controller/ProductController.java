package TestJPA.Controller;

import TestJPA.Model.responobject.Respon;
import TestJPA.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "buyproduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Respon<String> buyProductDetail(@RequestParam int productDetailId,@RequestParam int quantityBuy) {
        return productService.buyProductDetail(productDetailId, quantityBuy);
    }
}
