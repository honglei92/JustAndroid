package com.boco.whl.lib.fusioncharts.bean;

import java.util.List;

public class MultiSeriesData {
    private Categories categories;
    private List<SerieData> dataset;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<SerieData> getDataset() {
        return dataset;
    }

    public void setDataset(List<SerieData> dataset) {
        this.dataset = dataset;
    }

}
