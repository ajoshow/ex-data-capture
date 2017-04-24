package com.ajoshow.playground.repository;

import com.ajoshow.playground.domain.entity.AdContentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andychu on 2017/4/23.
 */
// simple CRUD jpa
@Repository
public class AdContentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(AdContentEntity entity){
        entityManager.persist(entity);
    }

    public void update(AdContentEntity entity){
        entityManager.merge(entity);
    }

    public AdContentEntity getAdContentEntityById(int id){
        return entityManager.find(AdContentEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<AdContentEntity> findAdContentEntityByTitle(String title){
        if(StringUtils.isNotBlank(title)){
            title = "%" + title + "%";
        }
        StringBuilder query = new StringBuilder();
        query.append(" FROM ");
        query.append(AdContentEntity.class.getSimpleName());
        query.append(" WHERE title LIKE :title");
        return entityManager.createQuery(query.toString())
                .setParameter("title", title)
                .getResultList();
    }

    public void delete(int id){
        AdContentEntity entity = getAdContentEntityById(id);
        if(entity != null){
            entityManager.remove(entity);
        }
    }
}
