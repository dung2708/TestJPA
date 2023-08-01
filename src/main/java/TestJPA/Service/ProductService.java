package TestJPA.Service;

import TestJPA.Model.ProductDetailPropertyDetails;
import TestJPA.Model.ProductDetails;
import TestJPA.Model.responobject.Respon;
import TestJPA.Repository.ProductDetailPropertyDetailRepo;
import TestJPA.Repository.ProductDetailRepo;
import TestJPA.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductDetailPropertyDetailRepo productDetailPropertyDetailRepo;


    @Override
    public Respon<String> buyProductDetail(int productDetailId, int quantityBuy) {
        Optional<ProductDetails> productDetails = productDetailRepo.findById(productDetailId);
        Respon<String> respon = new Respon<>();
        if (productDetails.isEmpty()) {
            //Không tồn tại ID sản phẩm
            respon.setStatus(1);
            respon.setError("Không có sản phẩm có ID : " + productDetailId);
        } else {
            int currentquantity = productDetails.get().getQuantity();
            if (currentquantity == 0) {
                //Kiểm tra nếu hết hàng
                respon.setStatus(2);
                respon.setError("Hết hàng.");
            } else if (quantityBuy > currentquantity) {
                //Kiểm tra không đủ số lượng hàng
                respon.setStatus(2);
                respon.setError("Không đủ số lượng hàng");
            } else if (currentquantity >= quantityBuy) {
                // Có đủ hàng để mua
                respon.setStatus(3);
                respon.setError("Mua hàng thành công.");
                // cập nhật số lượng hàng trong CSDL
                List<ProductDetails> pdList = productDetailRepo.findAll();
                List<ProductDetails> parentNull = new ArrayList<>();
                List<ProductDetails> list5To16 = new ArrayList<>();
                List<ProductDetails> list17to40 = new ArrayList<>();
                List<ProductDetails> list44To47 = new ArrayList<>();
                int quantityOp = quantityBuy;
                for (ProductDetails pd : pdList) {
                    if (pd.getParentId() == null) {
                        parentNull.add(pd);
                    }
                    if (pd.getId() >= 5 && pd.getId() <= 16) {
                        list5To16.add(pd);
                    } else if (pd.getId() >= 17 && pd.getId() <= 40) {
                        list17to40.add(pd);
                    } else if (pd.getId() >= 44 && pd.getId() <= 47) {
                        list44To47.add(pd);
                    }
                }
                ProductDetails pd1 = productDetails.get();
                if (productDetailId >= 17 && productDetailId <= 40) {
                    pd1.setQuantity(pd1.getQuantity() - quantityOp);
                    list5To16.forEach(x -> {
                        if (pd1.getProductPropertyName().contains(x.getProductPropertyName())) {
                            x.setQuantity(x.getQuantity() - quantityOp);
                        }
                    });
                    parentNull.forEach(x ->{
                        if (pd1.getProductPropertyName().contains(x.getProductPropertyName())){
                            x.setQuantity(x.getQuantity() - quantityOp);
                        }
                    });
                } else if (productDetailId >= 5 && productDetailId <= 16) {
                    pd1.setQuantity(pd1.getQuantity() - quantityOp);
                    parentNull.forEach(x ->{
                        if (pd1.getProductPropertyName().contains(x.getProductPropertyName())){
                            x.setQuantity(x.getQuantity() - quantityOp);
                        }
                    });
                    int qtt = quantityBuy;
                    for (ProductDetails pd2 : list17to40) {
                        int quantity = pd2.getQuantity();
                        if (pd2.getProductPropertyName().contains(pd1.getProductPropertyName())){
                            if (qtt > quantity){
                                pd2.setQuantity(0);
                                qtt -= quantity;
                            }else{
                                pd2.setQuantity(quantity - qtt);
                                break;
                            }
                        }
                    }
                } else if (productDetailId >=1 && productDetailId <=4) {
                    pd1.setQuantity(pd1.getQuantity() - quantityOp);
                    int qtt = quantityBuy;
                    for (ProductDetails pd : list5To16) {
                        int quantity = pd.getQuantity();
                        if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())){
                            if (qtt > quantity){
                                pd.setQuantity(0);
                                qtt -= quantity;
                            }else{
                                pd.setQuantity(quantity - qtt);
                                break;
                            }
                        }
                    }
                    for (ProductDetails pd : list17to40) {
                        int quantity = pd.getQuantity();
                        if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())){
                            if (qtt > quantity){
                                pd.setQuantity(0);
                                qtt -= quantity;
                            }else{
                                pd.setQuantity(quantity - qtt);
                                break;
                            }
                        }
                    }
                } else if (productDetailId >=44 && productDetailId <=47) {
                    pd1.setQuantity(pd1.getQuantity() - quantityOp);
                    for (ProductDetails pd : parentNull) {
                        if (pd1.getProductPropertyName().contains(pd.getProductPropertyName())){
                            pd.setQuantity(pd.getQuantity()-quantityOp);
                        }
                    }
                } else if (productDetailId >=42 && productDetailId <=43) {
                    pd1.setQuantity(pd1.getQuantity() - quantityOp);
                    int qtt = quantityBuy;
                    for (ProductDetails pd : list44To47) {
                        int quantity = pd.getQuantity();
                        if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())){
                            if (qtt > pd.getQuantity()){
                                pd.setQuantity(0);
                                qtt -= quantity;
                            }else {
                                pd.setQuantity(quantity - qtt);
                                break;
                            }
                        }
                    }
                }
                productDetailRepo.saveAll(parentNull);
                productDetailRepo.saveAll(list5To16);
                productDetailRepo.saveAll(list17to40);
                productDetailRepo.saveAll(list44To47);
                productDetailRepo.save(pd1);
            }
        }
        return respon;
    }
}
