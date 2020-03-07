package fun.imcoder.cloud.controller;

import fun.imcoder.cloud.common.ResponseData;
import fun.imcoder.cloud.model.ImcoderFile;
import fun.imcoder.cloud.utils.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @GetMapping("/directory")
    private ResponseData<List<ImcoderFile>> directory(@RequestParam String path) throws IOException {
        return ResponseData.success(FileUtils.getFiles(path));
    }

    @GetMapping("/content")
    private ResponseData<String> content(@RequestParam String path) throws Exception {
        return ResponseData.success(FileUtils.getFileContent(path));
    }

    @PostMapping("/directory/create")
    private ResponseData createDirectory(@RequestBody ImcoderFile file) {
        return ResponseData.success(FileUtils.mkdir(file.getPath()));
    }

    @PostMapping("/create")
    private ResponseData createFile(@RequestBody ImcoderFile file) {
        return ResponseData.success(FileUtils.newFile(file.getPath()));
    }

    @PutMapping("/write")
    private ResponseData writeFile(@RequestBody ImcoderFile file) {
        return ResponseData.success(FileUtils.writeFile(file.getPath(), file.getContent()));
    }

    @DeleteMapping("/delete")
    private ResponseData delete(@RequestBody ImcoderFile file) {
        return ResponseData.success(FileUtils.deleteFile(file.getPath()));
    }

}
