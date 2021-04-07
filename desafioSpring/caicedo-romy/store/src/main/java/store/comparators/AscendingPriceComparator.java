package store.comparators;

import store.dto.ArticleDTO;

import java.util.Comparator;

public class AscendingPriceComparator implements Comparator<ArticleDTO> {
    @Override
    public int compare(ArticleDTO o1, ArticleDTO o2) {
        Double price1 = Double.parseDouble(o1.getPrice().replace("$", "").replace(".",""));
        Double price2 = Double.parseDouble(o2.getPrice().replace("$", "").replace(".",""));

        return price2.compareTo(price1);
    }


}

