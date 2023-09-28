package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.ProductColorDto;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.ProductColorService;

import javax.validation.Valid;

@Controller
@RequestMapping("management/product-color")
public class ProductColorController {
    @Autowired
    ProductColorService colorService;

    @GetMapping("list")
    public String listColor(Model model){
        ResponseObject response = colorService.getListColor();
        if (response.getStatus().equals("success")){
            model.addAttribute("notify", response.getMessage());
            model.addAttribute("productColorList", response.getData());
            return "admin/productColor/list-color.html";
        }else{
            return "common/fragments.html";
        }
    }

    //Them color
    @GetMapping("add-color")
    public String createColor(Model model){
        model.addAttribute("productColorDto", new ProductColorDto());
        return "admin/productColor/create-color.html";
    }

    @PostMapping("add-color/save")
    public String saveColor(@Valid ProductColorDto productColorDto, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "admin/productColor/create-color.html";
        }
        ResponseObject response = colorService.save(productColorDto);
        if (response.getStatus().equals("failed")){
            model.addAttribute("error", response.getMessage());
            return "admin/productColor/create-color.html";
        }
        redirectAttributes.addAttribute("message", response.getMessage());
        return "redirect:/management/product-color/list";
    }

    //sửa color
    @GetMapping("edit-color/{id}")
    public String editColor(@PathVariable Integer id, Model model){
        ResponseObject response = colorService.getColorById(id);
        model.addAttribute("productColorDto", response.getData());
        model.addAttribute("notify", response.getMessage());
        return "admin/productColor/detail-color.html";
    }

    @PostMapping(value = "edit-color/save/{id}")
    public String editSave(@PathVariable Integer id,@Valid ProductColorDto productColorDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        productColorDto.setColorId(id);
        if (bindingResult.hasErrors()){
            return "admin/productColor/detail-color.html";
        }
        ResponseObject response = colorService.editColor(productColorDto);
        if (response.getStatus().equals("failed")){
            model.addAttribute("error", response.getMessage());
            return "admin/productColor/detail-color.html";
        }
        if (response.getStatus().equals("not found")){
            model.addAttribute("notify", response.getMessage());
            return "admin/productColor/detail-color.html";
        }

        redirectAttributes.addAttribute("message", response.getMessage());
        return "redirect:/management/product-color/list";
    }


    @RequestMapping(value = "delete-color/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteColor(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        ResponseObject response = colorService.deleteColor(id);
        redirectAttributes.addAttribute("message", response.getMessage());
        return "redirect:/management/product-color/list";
    }
}
