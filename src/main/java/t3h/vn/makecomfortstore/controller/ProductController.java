package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.ProductDto;
import t3h.vn.makecomfortstore.repository.CategoryRepository;
import t3h.vn.makecomfortstore.repository.ProductColorRepository;
import t3h.vn.makecomfortstore.repository.ProductSizeRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.ProductService;

import javax.validation.Valid;
import javax.xml.ws.Response;

@Controller
@RequestMapping("/management/product")
public class ProductController {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductColorRepository productColorRepo;

    @Autowired
    ProductSizeRepository productSizeRepo;

    @Autowired
    ProductService productService;

    //Get list product had enabled
    @GetMapping(value = "list")
    public String listProduct(Model model){
        ResponseObject response = productService.listProduct();
        model.addAttribute("productList", response.getData());
        return "admin/product/list-product.html";
    }

    //Create new product
    @GetMapping(value = "add-product")
    public String addProduct(Model model){
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categoryRepo.findListCategory());
        model.addAttribute("productSizeList", productSizeRepo.findListSize());
        model.addAttribute("productColorList", productColorRepo.findListProductColor());
        return "admin/product/create-product.html";
    }

    @PostMapping(value = "add-product/save")
    public String saveProduct(@Valid ProductDto productDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        model.addAttribute("categories", categoryRepo.findListCategory());
        model.addAttribute("productSizeList", productSizeRepo.findListSize());
        model.addAttribute("productColorList", productColorRepo.findListProductColor());

        if (bindingResult.hasErrors()){
            return "admin/product/create-product.html";
        }
        ResponseObject response = productService.saveProduct(productDto);
        if (response.getStatus().equals("failed")){
            if (response.getMessage().equalsIgnoreCase("Mã sản phẩm không được trùng lặp!")){
                bindingResult.rejectValue("productAlias", "error.productDto", response.getMessage());
            }
            if (response.getMessage().equalsIgnoreCase("Ảnh chính không được để trống!")){
                bindingResult.rejectValue("mainImage", "error.productDto-mainImage", response.getMessage());
            }
//            if (response.getMessage().equalsIgnoreCase("Ảnh phụ sản phẩm phải ít nhất 1 cái!")){
//                bindingResult.rejectValue("listExtraImage", "error.productDto-listExtraImage", response.getMessage());
//            }
            return "admin/product/create-product.html";
        }else{
            redirectAttributes.addAttribute("message", response.getMessage());
            return "redirect:/management";
        }
    }


    //Delete product by set productEnabled equal 0
    @RequestMapping(value = "delete-product/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        ResponseObject response = productService.deleteProduct(id);
        if (response.getStatus().equals("success")){
            redirectAttributes.addAttribute("message", "Xóa sản phẩm thành công!");
        }else{
            model.addAttribute("status", "failed");
            model.addAttribute("notify", response.getMessage());
        }
        return "redirect:/management/product/list";
    }

    //Update product
    @GetMapping(value = "edit-product/{id}")
    public String getProductById(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        ResponseObject response = productService.getProductById(id);

        if (response.getStatus().equals("failed")){
            redirectAttributes.addAttribute("message", "Sản phẩm không tồn tại!");
            return "redirect:/management/product/list";
        }else{
            model.addAttribute("productDto", response.getData());
            model.addAttribute("categories", categoryRepo.findListCategory());
            return "admin/product/detail-product.html";
        }
    }
}
