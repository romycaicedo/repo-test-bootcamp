package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dto.ArticleDTO;
import store.service.ArticleService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;


    @GetMapping(value = "/api/v1/articles")
    public ResponseEntity getByFilter(@RequestParam Map<String,String> params) throws NoSuchMethodException {
        if (params.size() == 0) {
            return new ResponseEntity(service.getAll(), HttpStatus.OK);
        }
            Map<String, String> paths = params;
        if (paths.containsKey("category") && paths.size() == 1) {
            return new ResponseEntity(service.getByCategory(params.get("category")), HttpStatus.OK);
        } else
            return new ResponseEntity(service.getByFilters(params), HttpStatus.OK);

    }
    @PostMapping("/api/v1/purchase-request")
    public ResponseEntity purchaseRequest(@RequestBody List<ArticleDTO> articleDTOList) throws FileNotFoundException {
        return new ResponseEntity(service.purchaseRequest(articleDTOList), HttpStatus.OK);

    }


}
