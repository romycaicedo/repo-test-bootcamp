package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.ArticleDTO;
import store.dto.PurchaseDTO;
import store.dto.StatusDTO;
import store.dto.TicketDTO;
import store.respository.ArticleRepository;



import java.util.List;
import java.util.Map;


@Service
public class ArticleServiceImpl implements  ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
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

    public PurchaseDTO purchaseRequest(List<ArticleDTO> articleDTOList)  {
        PurchaseDTO purchase = new PurchaseDTO();
        TicketDTO ticketDTO = new TicketDTO();
        StatusDTO statusDTO = new StatusDTO();

        List<ArticleDTO> stock = articleRepository.stockQuery(articleDTOList);

            if(articleRepository.articleQuery(articleDTOList)!= null)
            {
                if(stock != null && stock.size() !=0 && stock.size() == articleDTOList.size()){
                    ticketDTO.setArticles(articleDTOList);
                    ticketDTO.setId(1);
                    double total = articleRepository.totalPurchase(articleDTOList);
                    ticketDTO.setTotal(total);
                    statusDTO.setCode(200);
                    statusDTO.setMessage("La solicitud se completó con exito");
                    purchase.setTicketDTO(ticketDTO);
                    purchase.setStatusDTO(statusDTO);
                    //articleRepository.modifyStock(articleDTOList);
                }
                else {
                    statusDTO.setCode(400);
                    if (stock == null || stock.size()==0) {
                        statusDTO.setMessage("No hay stock de los articulos ingresados");
                    purchase.setStatusDTO(statusDTO);
                    } else
                        statusDTO.setCode(400);
                    for (ArticleDTO dto : articleDTOList) {
                        if (!stock.contains(dto)) {
                            statusDTO.setMessage("El stock no es suficiente para el producto:" + dto.getName() + " las unidade actuiales son " + dto.getQuantity() + " porfavor modifica tu solicitud de compra");
                        }
                        break;

                    }

                    purchase.setStatusDTO(statusDTO);


                }

            } else {
                purchase.statusDTO.setCode(404);
                for (ArticleDTO dto : articleDTOList) {
                    if (articleRepository.articleQuery(articleDTOList).contains(dto)) {
                        purchase.statusDTO.setMessage("El producto " + dto.getName() + " no existe, porfavor modifca tu solicitud ");
                    }
                    break;
                }
            }
        return purchase;
    }




}
