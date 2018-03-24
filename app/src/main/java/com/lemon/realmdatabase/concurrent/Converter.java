package com.lemon.realmdatabase.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     <code>S</code> as Supplier Type
 *     <code>R</code> as Return Type
 * </p>
 * Created by lemon on 3/17/2018.
 */

@SuppressWarnings("ALL")
public interface Converter<S,R> {
    default List<R> convert(List<S> items){
        List<R> rList=new ArrayList<>();
        for(S s:items)
            rList.add(convert(s));
        return rList;
    }

    R convert(S item);
}
