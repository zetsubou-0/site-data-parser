package com.zetsubou_0.parser.adapter.impl;

import com.zetsubou_0.parser.adapter.AdapterFactory;
import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.model.DataItem;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class AdapterFactoryImpl implements AdapterFactory {

    @Inject
    private Map<Class, DataItemAdapter> categoryProcessors;

    @Override
    public <T extends DataItem> DataItemAdapter<T> createAdapter(Class<T> adapter) {
        return Optional.of(categoryProcessors.get(adapter))
                .orElse(null);
    }
}
