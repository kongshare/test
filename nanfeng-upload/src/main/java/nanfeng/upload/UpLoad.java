package nanfeng.upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping
public class UpLoad {
    @Autowired
    NanfengSeviceImp nanfengSevice;

    @PostMapping("/image")
    public ResponseEntity<String> upload(@RequestParam(name = "file") MultipartFile file) {
        String url = nanfengSevice.upload(file);
        return ResponseEntity.status(200).body(nanfengSevice.upload(file));
    }

    @GetMapping("/signature")
    public ResponseEntity<Map<String, Object>> signature() {

        return ResponseEntity.ok(nanfengSevice.signature());
    }

}
