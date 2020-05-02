package com.nanfeng.controller.co;




import com.nanfeng.TbBrandDTO;

import com.nanfeng.service.TbBrandService;

import com.nanfeng.service.impl.TbCategoryBrandServiceImpl;

import com.share.vo.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    TbBrandService tbBrandService;
    @GetMapping("/page")
    public ResponseEntity<PageResult<TbBrandDTO>> findpage(
                                                @RequestParam(required = false) String key,
                                               @RequestParam(required = false,defaultValue = "1") Integer page,
                                               @RequestParam(required = false,defaultValue = "10") Integer rows,
                                               @RequestParam(required = false) String sortBy,
                                               @RequestParam(required = false) Boolean desc) {
       return ResponseEntity.status(200).body(tbBrandService.findBrandPagr(key,page,rows,sortBy,desc));

    }

    //新增品牌
    /*
    注意事项  1.品牌和分类有关系表   增加品牌需要操作两张表
     */
    @PostMapping()
    public ResponseEntity<Void> insert(TbBrandDTO tbBrandDTO,
                                       @RequestParam(name = "cids") List<Long> cids) {
        Object insert = tbBrandService.insert(tbBrandDTO, cids);

        return ResponseEntity.status(200).build();
    }

    /*根据分类id获取品牌信息

     */
    @Autowired
    TbCategoryBrandServiceImpl tbBrandCategory;


    public ResponseEntity<List<TbBrandDTO>> findByCid(Long id) {

      return ResponseEntity.status(200).body(tbBrandCategory.findByCid(id));
  }

    /*
    根据分类id查询品牌第二种方式
     */
    @GetMapping("/of/category")
    public ResponseEntity<List<TbBrandDTO>> findByCi(Long id) {
     return ResponseEntity.ok().body(tbBrandService.f(id))  ;
    }

}
