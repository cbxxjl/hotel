package com.cbxjl.hotel.system.controller;

import com.cbxjl.hotel.common.domain.R;
import com.cbxjl.hotel.common.exception.BusinessException;
import com.cbxjl.hotel.common.utils.FileUtils;
import com.cbxjl.hotel.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * 文件上传控制器
 *
 * @author : cbxjl
 * @date : 2024/3/13 16:55
 */
@RestController
@Slf4j
@RequestMapping("/hotel/upload")
public class UploadController {
    @Value("${default.uploadPicture}")
    private String uploadPath;

    @PostMapping("/picture")
    public R<String> uploadPicture(@RequestBody MultipartFile file) throws IOException {
        //然后转换为base64返回给前端
        String base64 = FileUtils.fileToBase64(file);
        return R.ok("操作成功", base64);
    }
}
