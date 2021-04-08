package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.ArticleDTO;
import store.dto.PurchaseDTO;
import store.dto.StatusDTO;
import store.dto.TicketDTO;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.StockOutOfBoundsException;
import store.respository.ArticleRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ArticleServiceImpl implements  ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    List<ArticleDTO> stock = new ArrayList<>();
    int counter = 1;

    public List<ArticleDTO> getAll(){
        return articleRepository.getAll();
    }

    @Override
    public List<ArticleDTO> getByCategory(String category) {
        return articleRepository.getByCategory(category);

    }
    @Override
    public List<ArticleDTO> getByFilters(Map<String,String> params)  {
        return articleRepository.getByFilters(params);
    }

    public PurchaseDTO purchaseRequest(List<ArticleDTO> articleDTOList) throws ArticleNotFoundException, StockOutOfBoundsException {
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
                //articleRepository.modifyStock(articleDTOList);
            } else {
                throw new StockOutOfBoundsException("Los articulos solcitados se encuentran agotados");

            }

        return purchase;
    }

    private void validateStock( List<ArticleDTO> articleDTOList) throws  StockOutOfBoundsException {
        for(int i = 0; i<articleDTOList.size();i++) {
            if (stock.get(i).getQuantity() < articleDTOList.get(i).getQuantity()) {
                throw new StockOutOfBoundsException("El Articulo " + articleDTOList.get(i).getName() + " cuenta solo con " + stock.get(i).getQuantity() + " unidades , por favor modifica tu pedido" );
            }
        }
    }




}
