package br.com.santander.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.santander.challenge.helper.ExcelHelper;
import br.com.santander.challenge.message.ResponseMessage;
import br.com.santander.challenge.service.impl.UploadFileXlsxServiceImpl;

@RestController
@RequestMapping("v1/api")
public class UploadFileXlsxController {

  @Autowired
  UploadFileXlsxServiceImpl fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (ExcelHelper.hasExcelFormat(file)) {
    	
      try {
    	  
        fileService.save(file);

        message = "Upload realizado com sucesso: " + file.getOriginalFilename();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        
      } catch (Exception e) {
        
    	message = "Nao foi possivel realizar o upload do arquivo: " + file.getOriginalFilename();
        
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
      
    }

    message = "Tente fazer o upload do arquivo mais tarde!";
    
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

}
