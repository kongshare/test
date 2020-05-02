package nanfeng.upload;


import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import nanfeng.upload.config.OSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class NanfengSeviceImp {
    //支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/bmp");

    public String upload(MultipartFile file) {

        //验证文件的类型
        String contentType = file.getContentType();
        if (!suffixes.contains(contentType)) {
            throw new NanfengException(ExceptionResult.ORDER_NOT_FOUND);
        }
        //验证图片信息
        try {
            ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取本地的文件目录
        String imgPath = "F:\\software102\\nginx-1.12\\nginx-1.12.2\\html";
        File dir = new File(imgPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //进行上传
            //新建一个名字
                //进行截取原名字的后缀
        String originalFilename = file.getOriginalFilename();
        String s = originalFilename.substring(originalFilename.lastIndexOf("."));
                //使用随机工具类
        String string = UUID.randomUUID().toString()+s;
                //新文件的名字
        try {
            file.transferTo(new File(dir,string));
        } catch (IOException e) {
            e.printStackTrace();
            throw new NanfengException(ExceptionResult.FILE_UPLOAD_ERROR);
        }
        //返回结果

        return "http://image.leyou.com/" + string;
        
    }
    @Autowired
    private OSSProperties prop;

    @Autowired
    private OSS client;



    public Map<String, Object> signature() {
        try {
            long expireTime = prop.getExpireTime();
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, prop.getMaxFileSize());
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, prop.getDir());

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, Object> respMap = new LinkedHashMap<>();
            respMap.put("accessId", prop.getAccessKeyId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", prop.getDir());
            respMap.put("host", prop.getHost());
            respMap.put("expire", expireEndTime);
            return respMap;

        }catch (Exception e){
            throw new NanfengException(ExceptionResult.FILE_UPLOAD_ERROR);
        }

    }



}
