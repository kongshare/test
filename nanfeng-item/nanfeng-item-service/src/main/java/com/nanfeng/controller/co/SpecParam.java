package com.nanfeng.controller.co;

import com.nanfeng.TbSpecParamDTO;
import com.nanfeng.service.co.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SpecParam {

    @Autowired
    SpecService specService;
    /*
   查询规格参数
    */
    @GetMapping("/spec/params")
    public ResponseEntity<List<TbSpecParamDTO>> findSpecParamByGid(@RequestParam(name = "gid",required = false) Long gid,
                                                                   @RequestParam(name = "cid",required = false) Long cid) {
        return ResponseEntity.status(200).body(specService.findSpecPaamByGid(gid, cid));
    }
}
