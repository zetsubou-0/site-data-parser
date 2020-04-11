package com.zetsubou_0.parser.adapter;

import com.zetsubou_0.parser.model.DataItem;

public interface DataItemAdapter<T extends DataItem> {
    <R extends DataItem> T adaptTo(R adaptable);
}
