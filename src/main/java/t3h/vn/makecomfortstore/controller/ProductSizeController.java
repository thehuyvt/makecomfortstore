package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.ProductSizeDto;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.ProductSizeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/management/product-size")
public class ProductSizeController {

    @Autowired
    ProductSizeService productSizeService;

    //Lay danh sach size
    @GetMapping("list")
    public String sizeList(Model model){
        ResponseEntity<ResponseObject> productSizeList =  productSizeService.getSizeList();
        model.addAttribute("productSizes", productSizeList.getBody().getData());
        return "admin/productSize/list-size.html";
    }

    //Tạo size
    @GetMapping("add-size")
    public String createSize(Model model){
        model.addAttribute("productSizeDto", new ProductSizeDto());
        return "admin/productSize/create-size.html";
    }

    @PostMapping("add-size/save")
    public String saveSize(@Valid ProductSizeDto productSizeDto, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "admin/productSize/create-size.html";
        }
        ResponseEntity<ResponseObject> response = productSizeService.save(productSizeDto);
        redirectAttributes.addAttribute("message", response.getBody().getMessage());
        model.addAttribute("notify", response.getBody().getMessage());
        return "redirect:/management/product-size/list";
    }

    //Chinh sua Size
    @GetMapping("edit-size/{id}")
    public String editSize(@PathVariable Integer id, Model model){
        ResponseEntity<ResponseObject> response = productSizeService.getSizeById(id);
        model.addAttribute("productSizeDto", response.getBody().getData());
        model.addAttribute("notify", response.getBody().getMessage());
        return "admin/productSize/detail-size.html";
    }

    @PostMapping("edit-size/save/{id}")
    public String editSizeSave(@PathVariable Integer id, @Valid ProductSizeDto productSizeDto,
                               BindingResult bindingResult ,RedirectAttributes redirectAttributes, Model model){
        productSizeDto.setSizeId(id);
        if (bindingResult.hasErrors()){
            return "admin/productSize/detail-size.html";
        }
        ResponseEntity<ResponseObject> response = productSizeService.editSave(productSizeDto);
        if (response.getBody().getData() == null){
            model.addAttribute("notify", response.getBody().getMessage());
            return "admin/productSize/detail-size.html";
        }else{
            redirectAttributes.addAttribute("message", response.getBody().getMessage());
            return "redirect:/management/product-size/list";
        }

    }

    //xoa kich cỡ
    @RequestMapping(value = "delete-size/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteSize(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        ResponseEntity<ResponseObject> response =  productSizeService.deleteSize(id);
        redirectAttributes.addAttribute("message", response.getBody().getMessage());
        return "redirect:/management/product-size/list";
    }
}
