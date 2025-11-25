package com.hackerrank.sample.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {

  @Id
  private String id;
  private String name;
  private String url;
  private String description;
  @Column(name = "price", nullable = false)
  private Double price;
  @Column(name = "rating", nullable = false)
  private Double rating;
  @Column(name = "category", nullable = false)
  private String category;
  @ElementCollection
  @CollectionTable(name = "ITEM_CHARACTERISTICS", joinColumns = @JoinColumn(name = "item_id"))
  private List<Characteristic> characteristics;
}
