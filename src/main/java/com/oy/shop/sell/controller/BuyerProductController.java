package com.oy.shop.sell.controller;

import com.oy.shop.sell.VO.ProductInfoVO;
import com.oy.shop.sell.VO.ProductVO;
import com.oy.shop.sell.VO.ResultVO;
import com.oy.shop.sell.entity.ProductCategory;
import com.oy.shop.sell.entity.ProductInfo;
import com.oy.shop.sell.service.CategoryService;
import com.oy.shop.sell.service.ProductService;
import com.oy.shop.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/10 15:18
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @Cacheable(cacheNames = "product",key = "123") 使用缓存
     * 作用是第一次访问的时候会将返回的数据存到缓存中，当第二次访问时，就会到缓存去匹配数据(依靠cacheNames寻找)，
     * 从而不走这块代码直接拿到返回结果，提高效率
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目(一次性查询)，java8的lamdba表达式
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList()));
        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVo = new ProductVO();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            //封装每个类目的多个商品Vo对象
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                //封装同type的商品List
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVo = new ProductInfoVO();
                    //使用BeanUtils封装属性进Vo，把第一个对象的值拷贝到第二个对象里面，根据字段名拷贝
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    //将数据add进去
                    productInfoVOList.add(productInfoVo);
                }
            }
            //把属于这个类目的商品数据都封装进去
            productVo.setProductInfoVOList(productInfoVOList);
            //将每一个类目都封装进ProductVo
            productVOList.add(productVo);
        }
        return ResultVOUtil.success(productVOList);
    }
}
