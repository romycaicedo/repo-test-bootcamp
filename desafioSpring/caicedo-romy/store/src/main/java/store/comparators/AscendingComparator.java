package store.comparators;

import store.dto.ArticleDTO;

import java.util.Comparator;

public class AscendingComparator implements Comparator<ArticleDTO> {
    @Override
    public int compare(ArticleDTO o1, ArticleDTO o2) {
        return o1.getName().compareTo(o2.getName());
    }


}
