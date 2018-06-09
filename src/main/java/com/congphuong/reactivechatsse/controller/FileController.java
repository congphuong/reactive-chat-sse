package com.congphuong.reactivechatsse.controller;

import com.congphuong.reactivechatsse.entity.File;
import com.congphuong.reactivechatsse.repository.FileRepository;
import com.congphuong.reactivechatsse.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;

@CrossOrigin
@RestController
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public Mono<File> uploadFile(@RequestPart(value = "file", required = true) FilePart file, ServerHttpRequest request) {
        File f = new File();
        String fileName = fileStorageService.storeFile(file);
        String host = request.getURI().getHost();
        String fileDownloadUri = UriComponentsBuilder.fromHttpRequest(request).replacePath("/files/")
                //.path("/downloadFile/")
                .path(fileName)
                .toUriString();

//        String fileDownloadUri = UriComponentsBuilder.fromUriString(host)
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
        f.setUrl(fileDownloadUri);

        return fileRepository.save(f);
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, ServerHttpRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);


        // Try to determine file's content type
        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//           // logger.info("Could not determine file type.");
//        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
