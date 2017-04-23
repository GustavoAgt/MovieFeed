
package com.ggproject.gustavo.moviefeed.model;


import java.io.Serializable;

public class Rating implements Serializable{

    private String Source;
    private String Value;

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
