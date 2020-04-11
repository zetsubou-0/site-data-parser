package com.zetsubou_0.parser.adapter.impl;

import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.model.AbstractDataItem;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.PriceDataItem;

public class PriceAdapter implements DataItemAdapter<PriceDataItem> {

    @Override
    public <R extends DataItem> PriceDataItem adaptTo(R adaptable) {
        if (adaptable == null) {
            return null;
        }
        if (AbstractDataItem.class.isAssignableFrom(adaptable.getClass())) {
            final AbstractDataItem abstractDataItem = (AbstractDataItem) adaptable;
            return new PriceDataItem(abstractDataItem.getArticleNumber(), abstractDataItem.getPrice());
        }
        return null;
    }
}
