
package com.ggproject.gustavo.moviefeed.model;


import java.io.Serializable;

public class Rating implements Serializable{

    private String source;
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
