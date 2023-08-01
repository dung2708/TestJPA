package TestJPA.Service;

import TestJPA.Model.ProductDetails;
import TestJPA.Model.responobject.Respon;
import TestJPA.Repository.ProductDetailPropertyDetailRepo;
import TestJPA.Repository.ProductDetailRepo;
import TestJPA.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductDetailPropertyDetailRepo productDetailPropertyDetailRepo;

    @Override
    public Respon<String> updateProductQuantity(int productDetailId, int addQuantity) {
        Optional<ProductDetails> productDetails = productDetailRepo.findById(productDetailId);
        Respon<String> respon = new Respon<>();
        if (productDetails.isEmpty()) {
            respon.setStatus(1);
            respon.setError("Không tồn tại sản phẩm này.");
            return respon;
        } else {
            // cập nhật số lượng hàng trong CSDL
            List<ProductDetails> pdList = productDetailRepo.findAll();
            List<ProductDetails> parentNull = new ArrayList<>();
            List<ProductDetails> list5To16 = new ArrayList<>();
            List<ProductDetails> list17to40 = new ArrayList<>();
            List<ProductDetails> list44To47 = new ArrayList<>();
            int quantityOp = addQuantity;
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
                pd1.setQuantity(pd1.getQuantity() + quantityOp);
                list5To16.forEach(x -> {
                    if (pd1.getProductPropertyName().contains(x.getProductPropertyName())) {
                        x.setQuantity(x.getQuantity() + quantityOp);
                    }
                });
                parentNull.forEach(x -> {
                    if (pd1.getProductPropertyName().contains(x.getProductPropertyName())) {
                        x.setQuantity(x.getQuantity() + quantityOp);
                    }
                });
            } else if (productDetailId >= 5 && productDetailId <= 16) {
                pd1.setQuantity(pd1.getQuantity() + quantityOp);
                parentNull.forEach(x -> {
                    if (pd1.getProductPropertyName().contains(x.getProductPropertyName())) {
                        x.setQuantity(x.getQuantity() + quantityOp);
                    }
                });
                int qtt = addQuantity;
                for (ProductDetails pd2 : list17to40) {
                    int quantity = pd2.getQuantity();
                    if (pd2.getProductPropertyName().contains(pd1.getProductPropertyName())) {
                        pd2.setQuantity(quantity + qtt);
                        break;
                    }
                }
            } else if (productDetailId >= 1 && productDetailId <= 4) {
                pd1.setQuantity(pd1.getQuantity() + quantityOp);
                int qtt = addQuantity;
                for (ProductDetails pd : list5To16) {
                    int quantity = pd.getQuantity();
                    if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())) {
                        pd.setQuantity(quantity + qtt);
                        break;
                    }
                }
                for (ProductDetails pd : list17to40) {
                    int quantity = pd.getQuantity();
                    if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())) {
                        pd.setQuantity(quantity + qtt);
                        break;
                    }
                }
            } else if (productDetailId >= 44 && productDetailId <= 47) {
                pd1.setQuantity(pd1.getQuantity() + quantityOp);
                for (ProductDetails pd : parentNull) {
                    if (pd1.getProductPropertyName().contains(pd.getProductPropertyName())) {
                        pd.setQuantity(pd.getQuantity() + quantityOp);
                    }
                }
            } else if (productDetailId >= 42 && productDetailId <= 43) {
                pd1.setQuantity(pd1.getQuantity() + quantityOp);
                int qtt = addQuantity;
                for (ProductDetails pd : list44To47) {
                    int quantity = pd.getQuantity();
                    if (pd.getProductPropertyName().contains(pd1.getProductPropertyName())) {
                        pd.setQuantity(quantity + qtt);
                        break;
                    }
                }
            }
            respon.setStatus(3);
            respon.setError("Cập nhật thành công");
            productDetailRepo.saveAll(parentNull);
            productDetailRepo.saveAll(list5To16);
            productDetailRepo.saveAll(list17to40);
            productDetailRepo.saveAll(list44To47);
            productDetailRepo.save(pd1);
        }
        return respon;
    }

    @Override
    public Respon<List<ProductDetails>> getAllProductDetailLast(int productDetailId) {
        Optional<ProductDetails> productDetails = productDetailRepo.findById(productDetailId);
        Respon<List<ProductDetails>> respon = new Respon<List<ProductDetails>>();
        if (productDetails.isEmpty()) {
            respon.setStatus(1);
            respon.setError("Không tồn tại sản phẩm này.");
            return respon;
        } else {
            List<ProductDetails> pdList = productDetailRepo.findAll();
            ProductDetails proDet = productDetails.get();
            List<ProductDetails> list17to40 = new ArrayList<>();
            List<ProductDetails> list44To47 = new ArrayList<>();
            for (ProductDetails pd : pdList) {
                if (pd.getId() >= 17 && pd.getId() <= 40) {
                    list17to40.add(pd);
                } else if (pd.getId() >= 44 && pd.getId() <= 47) {
                    list44To47.add(pd);
                }
            }
            List<ProductDetails> productDetailsList = new ArrayList<>();
            if (productDetailId >= 17 && productDetailId <= 40) {
                productDetailsList.add(proDet);
            } else if (productDetailId >= 44 && productDetailId <= 47) {
                productDetailsList.add(proDet);
            } else if (productDetailId >= 5 && productDetailId <= 16) {
                for (ProductDetails pd : list17to40) {
                    if (pd.getProductPropertyName().contains(proDet.getProductPropertyName())) {
                        productDetailsList.add(pd);
                    }
                }
            } else if (productDetailId >= 1 && productDetailId <= 4) {
                for (ProductDetails pd : list17to40) {
                    if (pd.getProductPropertyName().contains(proDet.getProductPropertyName())) {
                        productDetailsList.add(pd);
                    }
                }
            } else if (productDetailId >= 42 && productDetailId <= 43) {
                for (ProductDetails pd : list44To47) {
                    if (pd.getProductPropertyName().contains(proDet.getProductPropertyName())) {
                        productDetailsList.add(pd);
                    }
                }
            }
            respon.setStatus(3);
            respon.setError("Thông tin đời cuối cùng của sản phẩm có ProductDetailId là : " + productDetailId);
            respon.setData(productDetailsList);
        }
        return respon;
    }
}
