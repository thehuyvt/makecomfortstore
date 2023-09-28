package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.CategoryDto;
import t3h.vn.makecomfortstore.entities.CategoryEntity;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/management/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public String listCategory(Model model){
        ResponseObject response = categoryService.getCategoryList();
//        model.addAttribute("notify", response.getMessage());
        model.addAttribute("categoryList", response.getData());
        return "admin/category/list-category.html";
    }

    //add category
    @GetMapping("add-category")
    public String addCategoryForm(Model model){
        model.addAttribute("categoryDto", new CategoryDto());
        return "admin/category/create-category.html";
    }

    @PostMapping("add-category/save")
    public String saveCategory(@Valid CategoryDto categoryDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "admin/category/create-category.html";
        }

        ResponseObject response = categoryService.save(categoryDto);
        if (response.getStatus().equals("failed")){
            bindingResult.rejectValue("categoryName", "error.categoryDto", response.getMessage());
            return "admin/category/create-category.html";
        }
        redirectAttributes.addAttribute("message", response.getMessage());
        return "redirect:/management/category/list";
    }

    //edit category
    @GetMapping("edit-category/{id}")
    public String editCategory(@PathVariable Integer id, Model model){
        CategoryEntity categoryEntity = categoryService.getCategoryById(id);
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEntity, categoryDto);
        model.addAttribute("notify", "Lấy thông tin phân loại thành công!");
        model.addAttribute("categoryDto", categoryDto);
        return "admin/category/detail-category.html";
    }

    @PostMapping(value = "edit-category/save/{id}")
    public String updateCategory(@PathVariable Integer id,@Valid CategoryDto categoryDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes){
        categoryDto.setCategoryId(id);
        if(bindingResult.hasErrors()){
            return "admin/category/detail-category.html";
        }
        ResponseObject response = categoryService.updateCategory(categoryDto);
        if (response.getStatus().equals("failed")){
            bindingResult.rejectValue("categoryName", "error.categoryDto", response.getMessage());
            return "admin/category/detail-category.html";
        }
        redirectAttributes.addAttribute("message", response.getMessage());
//        model.addAttribute("notify", response.getMessage());
        return "redirect:/management/category/list";
    }

    @RequestMapping(value = "delete-category/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        ResponseObject response = categoryService.deleteCategory(id);
        redirectAttributes.addAttribute("message", response.getMessage());
        return "redirect:/management/category/list";
    }
}
