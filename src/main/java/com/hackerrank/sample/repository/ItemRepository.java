
package com.hackerrank.sample.repository;

import com.hackerrank.sample.model.Item;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, String> {
    @Transactional
    void deleteById(String id);

    /**
     * Retrieves a list of items that belong to the specified category.
     *
     * @param category the category of items to retrieve
     * @return a list of items that belong to the specified category
     */
    List<Item> findByCategory(String category);
}
