package com.example.BOO.Service;

import com.example.BOO.DTO.BillProductDTO;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillProductService {

    @PersistenceContext
    EntityManager entityManager ;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");



    public List<BillProductDTO> query(String productName, String categoryName, String from , String to){
        List<BillProductDTO> billProductDTOs = new ArrayList<>() ;
        String query =  "select p.id, bp.product_name, sum(bp.amount), sum(bp.amount * bp.price), c.name from bill_product bp \n" +
                "inner join bill b on b.id = bp.bill_id \n" +
                "inner join product p on bp.product_id = p.id\n" +
                "inner join  category c on p.category_id = c.id\n" +
                "where " ;
//                "group by bp.product_name ;"  ;
        List<String> criteria = new ArrayList<>();
        if (productName !=null && !productName.isEmpty()  ){
            String nameCriteria= String.format("bp.product_name = '%s' ", productName);
            criteria.add(nameCriteria) ;
        }

        if (categoryName !=null && !categoryName.isEmpty()  ){
            String categoryCriteria= String.format("c.name = '%s' ", categoryName);
            criteria.add(categoryCriteria) ;
        }

        if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
            String dateCriteria = String.format("b.created_time >= '%s' and b.created_time <= '%s' ", from, to);
            criteria.add(dateCriteria) ;
        }
        else if (to != null && !to.isEmpty()){
            String dateCriteria = String.format("b.created_time >= '%s' ", to);
            criteria.add(dateCriteria) ;
        }

        else if (from != null && !from.isEmpty()){
            String dateCriteria = String.format("b.created_time >= '%s' ", from);
            criteria.add(dateCriteria) ;
        }
        else{
            Date now = new Date() ;
            String dateCriteria = String.format("b.created_time >= '%s' ", formatter.format(now));
            criteria.add(dateCriteria) ;

        }

        String criteriaString = String.join(" and ", criteria) ;

        query += criteriaString + "\n group by bp.product_name ;" ;

        Query queryRes = entityManager.createNativeQuery(query) ;
        List<Object[]> result  = queryRes.getResultList() ;

        for (Object[] obj  : result){
            billProductDTOs.add(new BillProductDTO((Integer) obj[0], (String) obj[1], (BigDecimal)  obj[2], (Double) obj[3], (String)  obj[4] ));
        }

        return billProductDTOs ;
    }



}
