package com.rezdy.lunch.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name= "ingredient")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient implements Comparable<Ingredient>{

    @Id
    @Column(name = "TITLE")
    private String title;

    @Column(name= "BEST_BEFORE")
    private LocalDate bestBefore;
    
    @Column(name= "USE_BY")
    private LocalDate useBy;
    
    
    @ManyToMany(mappedBy = "ingredients")
    @JsonManagedReference
    private Set<Recipe> likes;


	@Override
	public int compareTo(Ingredient arg0) {
		return this.bestBefore.compareTo(arg0.bestBefore);
	}

    
    
}
