package com.zetsubou_0.parser.adapter.impl;

import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.model.AbstractDataItem;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.ImageDataItem;

public class ImageAdapter implements DataItemAdapter<ImageDataItem> {

    @Override
    public <R extends DataItem> ImageDataItem adaptTo(R adaptable) {
        if (adaptable == null) {
            return null;
        }
        if (AbstractDataItem.class.isAssignableFrom(adaptable.getClass())) {
            final AbstractDataItem abstractDataItem = (AbstractDataItem) adaptable;
            return new ImageDataItem(abstractDataItem.getArticleNumber(), abstractDataItem.getImages());
        }
        return null;
    }
}
