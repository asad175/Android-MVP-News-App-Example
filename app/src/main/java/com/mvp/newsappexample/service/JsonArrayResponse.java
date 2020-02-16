package com.mvp.newsappexample.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/*This class is to get all Object type response*/
public class JsonArrayResponse<T> {

    @SerializedName("status")
    private String status;

    @SerializedName("num_results")
    private int numResults;

    @SerializedName("results")
    private List<T> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
