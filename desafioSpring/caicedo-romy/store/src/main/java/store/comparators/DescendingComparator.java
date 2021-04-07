package store.comparators;

import store.dto.ArticleDTO;

import java.util.Comparator;

public class DescendingComparator  implements Comparator<ArticleDTO> {
    @Override
    public int compare(ArticleDTO o1, ArticleDTO o2) {
        return o2.getName().compareTo(o1.getName());
    }


}

