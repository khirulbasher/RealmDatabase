package com.lemon.realmdatabase.entity.converter;

import com.lemon.realmdatabase.concurrent.AbstractConverter;
import com.lemon.realmdatabase.entity.Person;
import com.lemon.realmdatabase.utility.util.Item;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class PersonConverter extends AbstractConverter<Person,Item> {

    @Override
    public Item convert(Person item) {
        return new Item(item.getId(),item.getName(),item.getEmail());
    }
}
