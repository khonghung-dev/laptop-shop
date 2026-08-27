package com.khonghung.laptopshop.controller.admin;

import com.khonghung.laptopshop.domain.Product;
import com.khonghung.laptopshop.domain.User;
import com.khonghung.laptopshop.service.ProductService;
import com.khonghung.laptopshop.service.UploadServices;
import com.khonghung.laptopshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadServices uploadServices;

    public ProductController(ProductService productService, UploadServices uploadServices) {
        this.productService = productService;
        this.uploadServices = uploadServices;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
                                    BindingResult newProductBindingResult,
                                    @RequestParam("hungkhongFile") MultipartFile file) {

        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        if(newProductBindingResult.hasErrors()){
            return "admin/product/create";
        }
        String image = this.uploadServices.handleSaveUploadFile(file, "product");
        product.setImage(image);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product products = this.productService.getAllProductById(id).orElse(null);
        model.addAttribute("products", products);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/{id}/edit")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        Product products = this.productService.getAllProductById(id).orElse(null);
        model.addAttribute("id", id);
        model.addAttribute("updateProduct", products);
        return "admin/product/update";
    }

    @PostMapping(value = "/admin/product/{id}/edit")
    public String updateProductPage(Model model,
                                    @ModelAttribute("updateProduct") @Valid Product updateProduct,
                                    BindingResult updateProductBindingResult, @PathVariable long id,
                                    @RequestParam("hungkhongFile") MultipartFile file) {
        if (updateProductBindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "admin/product/update";
        }
        Product currentProduct = this.productService.getAllProductById(id).orElse(null);
        if (currentProduct != null) {
            currentProduct.setName(updateProduct.getName());
            currentProduct.setShortDesc(updateProduct.getShortDesc());
            currentProduct.setDetailDesc(updateProduct.getDetailDesc());
            currentProduct.setFactory(updateProduct.getFactory());
            currentProduct.setTarget(updateProduct.getTarget());
            currentProduct.setPrice(updateProduct.getPrice());
            currentProduct.setQuantity(updateProduct.getQuantity());
            if (!file.isEmpty()) {
                String oldImage = currentProduct.getImage();
                String newImage = this.uploadServices.handleSaveUploadFile(file, "product");
                currentProduct.setImage(newImage);
                this.uploadServices.handleDeleteFile(oldImage, "product");
            }
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}/delete")
    public String deleteProductPage(Model model, @PathVariable long id) {
        Product products = this.productService.getAllProductById(id).orElse(null);
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @GetMapping("/admin/product/{id}/delete/success")
    public String deleteProductSuccess(Model model, @PathVariable long id) {
        Product product = this.productService.getAllProductById(id).orElse(null);
        if (product != null) {
            this.uploadServices.handleDeleteFile(product.getImage(), "product");
            this.productService.deleteProductById(id);
        }
        return "redirect:/admin/product";
    }

}
