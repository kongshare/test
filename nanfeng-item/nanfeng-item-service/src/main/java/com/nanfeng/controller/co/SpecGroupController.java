package com.nanfeng.controller.co;


import com.nanfeng.TbSpecGroupDTO;
import com.nanfeng.TbSpecParamDTO;
import com.nanfeng.pojo.TbSpecGroup;
import com.nanfeng.pojo.TbSpecParam;
import com.nanfeng.service.co.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SpecGroupController {

    @Autowired
    SpecService specService;
    /*
    查询规格组
     */
    @GetMapping("/spec/groups/of/category")
    public ResponseEntity<List<TbSpecGroupDTO>> findByCidSpecGroup(@RequestParam("id") Long cid) {

        specService.findByCidSpecGroup(cid);
        return ResponseEntity.status(200).body(specService.findByCidSpecGroup(cid));
    }

    /*
    新增规格组
    接受json数据
     */

    @PostMapping("/spec/group")
    public ResponseEntity<Void> insertSpecGroup(@RequestBody TbSpecGroup groupdto) {
        Boolean b = specService.insertSpecGroup(groupdto);


        return ResponseEntity.status(200).build();
    }
}
