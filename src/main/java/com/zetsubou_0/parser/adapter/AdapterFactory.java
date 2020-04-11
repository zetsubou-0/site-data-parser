package com.zetsubou_0.parser.adapter;

import com.zetsubou_0.parser.model.DataItem;

public interface AdapterFactory {
    <T extends DataItem> DataItemAdapter<T> createAdapter(Class<T> adapter);
}
