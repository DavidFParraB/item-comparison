package com.hackerrank.sample.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "model")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Model implements Serializable {

  @Id
  private String id;
  private String name;
  private String url;
  private String description;
  private Double price;
  private String rating;
  private String category;
  @ElementCollection
  @CollectionTable(name = "MODEL_CHARACTERISTICS", joinColumns = @JoinColumn(name = "model_id"))
  private List<Characteristic> characteristics;
}
