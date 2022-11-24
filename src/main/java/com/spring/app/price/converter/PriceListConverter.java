package com.spring.app.price.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class PriceListConverter implements AttributeConverter<List<Double>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<Double> doubles) {
        List<String> priceList = new ArrayList<>();
        for (Double d : doubles)
            priceList.add(Double.toString(d));

        return String.join(SPLIT_CHAR, priceList);
    }

    @Override
    public List<Double> convertToEntityAttribute(String list) {
        String[] arrayList = list.split(SPLIT_CHAR);
        List<Double> priceList = new ArrayList<>();

        for (String p: arrayList) {
            priceList.add(Double.parseDouble(p));
        }

        return priceList;
    }
}
