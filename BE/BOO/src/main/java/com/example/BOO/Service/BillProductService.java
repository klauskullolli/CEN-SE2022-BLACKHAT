package com.example.BOO.Service;

import com.example.BOO.DTO.BillProductDTO;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                "group by bp.product_name ;"  ;
        Query queryRes = entityManager.createNativeQuery(query) ;
        List<Object[]> result  = queryRes.getResultList() ;

        for (Object[] obj  : result){
            billProductDTOs.add(new BillProductDTO((String) obj[0], (String) obj[1], (Integer)  obj[2], (Double) obj[3], (String)  obj[4] ));
        }

        return billProductDTOs ;
    }


    public List<BillProductDTO> query(){
        List<BillProductDTO> billProductDTOs = new ArrayList<>() ;
        String query =  "select p.id, bp.product_name, sum(bp.amount), sum(bp.amount * bp.price), c.name from bill_product bp \n" +
                "inner join bill b on b.id = bp.bill_id \n" +
                "inner join product p on bp.product_id = p.id\n" +
                "inner join  category c on p.category_id = c.id\n" +
                "group by bp.product_name ;"  ;
        Query queryRes = entityManager.createNativeQuery(query) ;
        List<Object[]> result  = queryRes.getResultList() ;

        for (Object[] obj  : result){
            billProductDTOs.add(new BillProductDTO((String) obj[0], (String) obj[1], (Integer)  obj[2], (Double) obj[3], (String)  obj[4] ));
        }

        return billProductDTOs ;
    }

    @Test
    private void test(){
        List<BillProductDTO> billProductDTOs = query() ;

        for (BillProductDTO bp: billProductDTOs ){
            System.out.println(bp);
        }
    }

}
