package com.nanfeng.controller.co;


import com.nanfeng.TbSkuDTO;
import com.nanfeng.TbSpuDTO;
import com.nanfeng.TbSpuDetailDTO;
import com.nanfeng.service.impl.TbSpecParamServiceImpl;
import com.nanfeng.service.impl.TbSpuDetailServiceImpl;
import com.nanfeng.service.impl.TbSpuServiceImpl;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import com.share.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GoodsController {

    /*
    分页查询spu的信息
     */

    @Autowired
    TbSpuServiceImpl spu;
    @GetMapping("spu/page")
    public ResponseEntity<PageResult> findPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                               @RequestParam(required = false, defaultValue = "10") Integer rows,
                                               @RequestParam(required = false ,name = "key") String key,
                                               @RequestParam(required = false,name = "saleable") Boolean saleable) {

        return ResponseEntity.status(200).body(spu.findPage(page, rows, key, saleable));

    }

    /*
    新增商品
        要保存三张表
        spu
        spudetail
        sku
     */
    @PostMapping("/goods")
    public ResponseEntity<Void> insert(@RequestBody TbSpuDTO s) {
        Boolean b = spu.insert(s);
        if (!b) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }
        return ResponseEntity.status(200).build();
    }

    /*
    商品上下架
    spu
    sku不显示
     */
    @PutMapping("/spu/saleable")
    public ResponseEntity<Void> saleable(@RequestParam(name = "id") Long id,
                                         @RequestParam(name = "saleable") Boolean saleable) {
        Boolean b=spu.saleable(id, saleable);

        return ResponseEntity.ok().build();
    }

    /*
    商品数据回显 spudetail
     */
    @GetMapping("spu/detail")
    public ResponseEntity<TbSpuDetailDTO> findSpuDetail(@RequestParam(name = "id") Long id) {

        return ResponseEntity.ok(spu.findSpuDetail(id));
    }

    /*
    sku具体的单个商品
     */
    @GetMapping("/sku/of/spu")
    public ResponseEntity<List<TbSkuDTO>> findByIdSku(@RequestParam(name = "id")Long id) {
        return ResponseEntity.ok().body(spu.findByIdSku(id));

    }

    /*
    修改商品
    修改需要三张表
    spu
    sku
    spudetailspu和sputertail是一对一的关系
     */
    @PutMapping("/goods")
    public ResponseEntity<Void> update(@RequestBody TbSpuDTO tbSpuDTO) {

        spu.nanfenpdate(tbSpuDTO);
        return ResponseEntity.ok().build();

    }

}
