package br.com.project.tmg.utils;

import java.util.ArrayList;
import java.util.List;

public class RandomValuesUtils {


    private List<Integer> values = new ArrayList<>();

    public RandomValuesUtils(Integer value) {
        for (int i = 0; i < value.toString().length(); i++){
            var v = Integer.parseInt(value.toString().substring(i, i +1));
            if(v > 0) values.add(v);
        }
    }

    public Integer getOneValue(Integer index) {
        var size = values.size();

        if (index >= 0) {
            var i = index;
            while(i >= size) {
                i = (i % size == 0) ? (i / size) : 0;
            }
            return values.get(i);
        }else
           return values.get(0);

    }

}
