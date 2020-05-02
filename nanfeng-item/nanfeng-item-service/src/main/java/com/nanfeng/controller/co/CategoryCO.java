package com.nanfeng.controller.co;


import com.nanfeng.TbCategoryDtO;
import com.nanfeng.service.TbCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryCO {

    @Autowired
    TbCategoryService tbCategoryService;
    @GetMapping("/of/parent")
    public ResponseEntity<List<TbCategoryDtO>> findByPid(@RequestParam("pid") Long pid) {

        return ResponseEntity.ok(tbCategoryService.findByParentId(pid));

    }

    @GetMapping("/of/parent/p/{pid}")
    public List findByPid(@PathVariable("pid") long pid) {
        return tbCategoryService.findByParentId(pid);
    }
}
