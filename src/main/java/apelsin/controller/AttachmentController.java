package apelsin.controller;

import apelsin.entity.Attachment;
import apelsin.payload.ApiResponse;
import apelsin.repository.AttachmentContentRepository;
import apelsin.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    public static final String uploadDirectory = "upload";
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

//    db un
//    @PostMapping("/upload")
//    public ApiResponse saveToDb(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file != null) {
//            Attachment attachment = new Attachment();
//            attachment.setName(file.getOriginalFilename());
//            attachment.setSize(file.getSize());
//            attachment.setType(file.getContentType());
//
//            Attachment save = attachmentRepository.save(attachment);
//
//            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setAttachment(save);
//            attachmentContent.setBytes(file.getBytes());
//
//            attachmentContentRepository.save(attachmentContent);
//            return new ApiResponse("Saved !", true, attachment);
////            byte[] bytes = file.getBytes();
////            String name = file.getName();
////            String contentType = file.getContentType();
////            String originalFilename = file.getOriginalFilename();
//        }
//        return new ApiResponse("Error upload file!", false);
//    }
//
//    @GetMapping("/download/{id}")
//    public void getFromDb(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
//            AttachmentContent attachmentContent = optionalAttachmentContent.get();
//            if (optionalAttachmentContent.isPresent()) {
//
//                response.setContentType(attachment.getType());
//                response.setHeader("Content-Disposition", attachment.getName() + "/:" + attachment.getSize());
//                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
//            }
//        }
//    }


    //file system
//    @PostMapping("/uploadSystem")
//    public String uploadSystem(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file != null) {
//            String originalFilename = file.getOriginalFilename();
//            Attachment attachment = new Attachment();
//            attachment.setName(originalFilename);
//            attachment.setSize(file.getSize());
//            attachment.setType(file.getContentType());
//            attachment.setName(originalFilename);
////            String[] split = originalFilename.split("\\.");
////            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
////            attachment.setName(name);
//            attachmentRepository.save(attachment);
//            Path path = Paths.get(uploadDirectory + "/" + originalFilename);
//            Files.copy(file.getInputStream(), path);
//            return "File downloaded Id:" + attachment.getId();
//        }
//        return "Error";
//    }


    @GetMapping("downloadSystem/{id}")
    public void getSystem(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();

        response.setContentType(attachment.getType());
        response.setHeader("Content-Disposition", attachment.getName());

        //systemdan topishi
        FileInputStream fileInputStream = new FileInputStream(new File(uploadDirectory + "/" + attachment.getName()));
        //copy qilib response
        FileCopyUtils.copy(fileInputStream, response.getOutputStream());
    }

    @PostMapping("/multiple")
    public ApiResponse saveMultiple(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                String originalFilename = file.getOriginalFilename();
                Attachment attachment = new Attachment();
                attachment.setName(originalFilename);
                attachment.setSize(file.getSize());
                attachment.setType(file.getContentType());
                attachment.setName(originalFilename);

                attachmentRepository.save(attachment);
                Path path = Paths.get(uploadDirectory + "/" + originalFilename);
                Files.copy(file.getInputStream(), path);
            } else {
                return new ApiResponse("Xato", false);
            }
        }
        return new ApiResponse("Saved!", true);
    }
}

