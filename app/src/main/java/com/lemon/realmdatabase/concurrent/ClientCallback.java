package com.lemon.realmdatabase.concurrent;



import com.lemon.realmdatabase.utility.util.Item;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public interface ClientCallback {
    int PREPARED=400;

    void onPrepareCallback(List<Item> items);
}
