package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.service.ArticleService;

import java.util.Map;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;
    @GetMapping(value = "/api/v1/articles", params = "")
    public ResponseEntity getAll(){
        return  new ResponseEntity(service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/api/v1/articles")
    public ResponseEntity getByFilter(@RequestParam Map<String,String> params) throws NoSuchMethodException {
        Map<String,String> paths = params;
        if(paths.containsKey("category")&& paths.size() ==1){
            return  new ResponseEntity(service.getByCategory(params.get("category")), HttpStatus.OK);
        }else
            return new ResponseEntity(service.getByFilters(params),HttpStatus.OK);

    }


}
