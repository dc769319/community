package cc.charles.community.controller;

import cc.charles.community.config.CustomSetting;
import cc.charles.community.dto.FileUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName FileController
 * @description
 * @date 2020/5/16 下午4:15
 * @since 1.8
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private CustomSetting setting;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public FileUploadDTO upload(
            @RequestParam(value = "editormd-image-file") MultipartFile file,
            HttpServletRequest request
    ) {
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        //判断文件是否为空
        fileUploadDTO.setSuccess(0);
        if (file.isEmpty()) {
            fileUploadDTO.setMessage("文件为空");
            return fileUploadDTO;
        }
        String fileName = file.getOriginalFilename();
        String uploadPath = setting.getUploadPathDir() + fileName;
        log.info("uploadPath: " + uploadPath);
        File destFile = new File(uploadPath);
        if (!destFile.getParentFile().exists()) {
            if (!destFile.getParentFile().mkdirs()) {
                fileUploadDTO.setMessage("上传目录创建失败");
                return fileUploadDTO;
            }
        }
        //TODO:检查Content-Type
        //检查文件后缀是否合法
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        List<String> suffixList = setting.getSuffixLimit();
        if (!suffixList.contains(suffixName)) {
            fileUploadDTO.setMessage("文件后缀不合法");
            return fileUploadDTO;
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            log.error("upload failed", e);
            fileUploadDTO.setMessage("图片上传失败");
            return fileUploadDTO;
        }
        //图片url
        String savedUrl = "/file/reader/" + fileName;
        fileUploadDTO.setSuccess(1);
        fileUploadDTO.setMessage("上传成功");
        fileUploadDTO.setUrl(savedUrl);
        return fileUploadDTO;
    }

    /**
     * 读取文件系统文件，并输出到浏览器端
     *
     * @param fileName 文件名
     * @return 文件实体
     */
    @GetMapping("/reader/{fileName}")
    public ResponseEntity reader(@PathVariable(value = "fileName") String fileName) {
        String filePath = setting.getUploadPathDir() + fileName;
        log.info("uploadPath: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        FileSystemResource resource = new FileSystemResource(filePath);
        return ResponseEntity.ok(resource);
    }
}
