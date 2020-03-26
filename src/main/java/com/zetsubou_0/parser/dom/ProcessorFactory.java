package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.Type;

public interface ProcessorFactory {
    DataItemProcessor create(Type type);
}
