package com.bookstore.shop.domain.item;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EB")
@Data
public class Ebook extends Item{
}
