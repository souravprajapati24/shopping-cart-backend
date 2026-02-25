package com.sourav.shoppingcart.controller;
import com.sourav.shoppingcart.Exception.ResourceNotFoundException;
import com.sourav.shoppingcart.dto.ImageDto;
import com.sourav.shoppingcart.model.Image;
import com.sourav.shoppingcart.response.ApiResponse;
import com.sourav.shoppingcart.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final ImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files , @RequestParam Long productId){
        try {
            List<ImageDto> imageDtos = imageService.saveImage(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload Successful !" , imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed !",e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1,(int)image.getImage().length()));  // to get the binary image data from the blob image
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\""+image.getFileName()+"\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId , @RequestBody MultipartFile file){
        try {
            Image image = imageService.getImageById(imageId);
            if(image!=null){
                imageService.updateImage(file,imageId);
                return ResponseEntity.ok(new ApiResponse("Update Successful !" , null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Update Failed", HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try {
            Image image = imageService.getImageById(imageId);
            if(image!=null){
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Delete Success !" , null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete Failed", HttpStatus.INTERNAL_SERVER_ERROR));

    }

}

























