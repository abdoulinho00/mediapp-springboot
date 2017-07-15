package com.aelbardai.diet.repository;

import com.aelbardai.diet.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for entity MenuItem.class
 */
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long>{
}
