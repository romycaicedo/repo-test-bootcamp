package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.*;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.FiltersException;
import store.exceptions.StockOutOfBoundsException;
import store.respository.ArticleRepository;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements  ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    List<ArticleDTO> stock = new ArrayList<>();
    List<PurchaseDTO> purchaseDTOList1 = new ArrayList<>();

    int counter = 1;
    //Metodo que trae todos los registros
    @Override
    public List<ArticleDTO> getAll(){
        return articleRepository.getAll();
    }
    //Metodo que trae productos por categoria
    @Override
    public List<ArticleDTO> getByCategory(String category) {
        return articleRepository.getByCategory(category);

    }
    //Método que trae registros por filtro
    @Override
    public List<ArticleDTO> getByFilters(Map<String,String> params) throws FiltersException {
        List<ArticleDTO> dto = articleRepository.getByFilters(params);
        if(dto.size()==0)
            throw new FiltersException("Los filtros utilizados no arrojan resultados, porfavor intenta con otros");
        return dto;
    }

    //Metodo para procesar una compra
    public PurchaseDTO purchaseRequest(ArticlesDTO articlesDTO) throws ArticleNotFoundException, StockOutOfBoundsException, FileNotFoundException {
        List<ArticleDTO> articleDTOList = articlesDTO.getArticles();
        PurchaseDTO purchase = new PurchaseDTO();
        TicketDTO ticketDTO = new TicketDTO();
        StatusDTO statusDTO = new StatusDTO();


            for(int i= 0; i<articleDTOList.size();i++){
                stock.add(articleRepository.getById(articleDTOList.get(i).getProductId()));

        }


            if (stock.size() != 0 || stock != null) {
                validateStock(articleDTOList);
                ticketDTO.setArticles(articleDTOList);
                ticketDTO.setId(counter);
                double total = articleRepository.totalPurchase(articleDTOList);
                ticketDTO.setTotal(total);
                statusDTO.setCode(200);
                statusDTO.setMessage("La solicitud se completó con exito");
                purchase.setTicket(ticketDTO);
                purchase.setStatus(statusDTO);
                counter++;



            } else {
                throw new StockOutOfBoundsException("Los articulos solcitados se encuentran agotados");

            }




        return purchase;
    }
        @Override
        public void modifyStock() throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException {
            for (int i = 0; i<stock.size()/2;i++) {
                articleRepository.modifyStock(stock.get(i));
            }
        }


    //Metodo que permite que la compra realizada quede guardada durante la ejecución
    @Override
        public List<PurchaseDTO> addPurchase(PurchaseDTO purchase){
            purchaseDTOList1.add(purchase);

         return purchaseDTOList1;
    }
    //Metodo que valida si el stock existente y el solicitado es valida, si asi es resta lo que se pidio y devuelve como quedaria el stock en caso de confirmar la compra, si no lanza excepcion
    private void validateStock( List<ArticleDTO> articleDTOList) throws  StockOutOfBoundsException {
        for(int i = 0; i<articleDTOList.size();i++) {
            if (stock.get(i).getQuantity() < articleDTOList.get(i).getQuantity()) {
                throw new StockOutOfBoundsException("El Articulo " + articleDTOList.get(i).getName() + " cuenta solo con " + stock.get(i).getQuantity() + " unidades , por favor modifica tu pedido" );
            }
            else
                stock.get(i).setQuantity(stock.get(i).getQuantity()-articleDTOList.get(i).getQuantity());
        }
    }
    //Metodo para cuando solicitan un carrito que seria la confirmacion de la compra
    public CartDTO cartRequest() throws FileNotFoundException, ArticleNotFoundException {
        CartDTO cartDTO = new CartDTO();
        List<Double> precios = new ArrayList<>();
        double totalCart = 0;
       if(counter>=1){
               cartDTO.setPurchase(purchaseDTOList1);
           for (int i =0; i<purchaseDTOList1.size();i++){
               precios.add(purchaseDTOList1.get(i).getTicket().getTotal());
           }
           for(int i= 0; i<precios.size();i++){
               totalCart = totalCart + precios.get(i);
           }
       }
        cartDTO.setTotal(totalCart);


        return cartDTO;

    }


}
