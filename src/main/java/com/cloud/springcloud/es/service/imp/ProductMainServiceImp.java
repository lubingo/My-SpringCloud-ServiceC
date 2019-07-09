package com.cloud.springcloud.es.service.imp;

import com.cloud.springcloud.es.dao.ProductMainDao;
import com.cloud.springcloud.es.entity.ProductMain;
import com.cloud.springcloud.es.service.ProductMainService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/20 9:50
 * @since
 */
@Service
public class ProductMainServiceImp implements ProductMainService {

    @Autowired
    private ProductMainDao productMainDao ;

    @Override
    public ProductMain saveOrUpdate(ProductMain productMain) {
       return productMainDao.save(productMain);
    }

    @Override
    public void saveAll(List<ProductMain> list) {
         productMainDao.saveAll(list);
    }

    @Override
    public void delete(ProductMain ProductMain) {
          productMainDao.delete(ProductMain);
    }

    @Override
    public  ProductMain queryById(Long id) {
        return productMainDao.findById(id).get();
    }

    @Override
    public List<ProductMain> query(int pageIndex, int size, String[] names, String[] value ,String[] weight)  {
        if(!(names.length == weight.length && weight.length ==value.length)){
            try {
                throw new Exception("ames.length != weight.length != value.length");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Pageable pageable =   PageRequest.of(pageIndex,size);
        NativeSearchQueryBuilder nativeSearchQueryBuilder =new NativeSearchQueryBuilder().withPageable(pageable) ;

        for (int i = 0; i <names.length ; i++) {
            QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery(names[i],value[i]));
            ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(Float.parseFloat(weight[i])) ;
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(queryBuilder,scoreFunctionBuilder ) ;
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }

        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        return  productMainDao.search(searchQuery).getContent();
    }


}
