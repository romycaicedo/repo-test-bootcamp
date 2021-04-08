package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dto.*;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.FiltersException;
import store.exceptions.StockOutOfBoundsException;
import store.service.ArticleService;

import java.io.FileNotFoundException;
import java.util.Map;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    //Metodo que permite traer articulos ya sea todos, por categoria o con filtros
    @GetMapping(value = "/api/v1/articles")
    public ResponseEntity getByFilter(@RequestParam Map<String,String> params) throws NoSuchMethodException, FiltersException {
        if (params.size() == 0) {
            return new ResponseEntity(service.getAll(), HttpStatus.OK);
        }
            Map<String, String> paths = params;
        if (paths.containsKey("category") && paths.size() == 1) {
            return new ResponseEntity(service.getByCategory(params.get("category")), HttpStatus.OK);
        } else
            return new ResponseEntity(service.getByFilters(params), HttpStatus.OK);

    }
    //Metodo para solicitud de compra
    @PostMapping("/api/v1/purchase-request")
    public ResponseEntity purchaseRequest(@RequestBody ArticlesDTO articleDTOList) throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException {
        PurchaseDTO dto = service.purchaseRequest(articleDTOList);
        service.addPurchase(dto);
        return new ResponseEntity(dto, HttpStatus.OK);

    }
    //Metodo para totalizar las compras, y modificar el stock de forma definitiva, en resources se encuetra la coleccion de postman para probar este metodo
    @GetMapping("/api/v1/cart-request")
    public ResponseEntity cartRequest() throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException {
        CartDTO cartDTO = service.cartRequest();
        service.modifyStock();
        return new ResponseEntity(cartDTO, HttpStatus.OK);

    }
    //Error para cuando no encuentra un articulo
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity badUrlSent(ArticleNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(404);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    //Error para cuando la busqueda por filtros no da resultado
    @ExceptionHandler(FiltersException.class)
    public ResponseEntity badFilters(FiltersException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(404);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    //Error para cuando no hay stock
    @ExceptionHandler(StockOutOfBoundsException.class)
    public ResponseEntity stockError(StockOutOfBoundsException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(404);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    //Error general para que devuelva una estructura definida en caso de que se estalle
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<StatusDTO> handleUnknownException(Exception e) {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage("Internal error, please contact API Spring Boot Challenge Admin");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
