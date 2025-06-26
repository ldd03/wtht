package com.ncwu.domain;

/**
 * ClassName: Item
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/9 0:36
 * @Version 1.0
 */
public class Item {
    private int value;
    private String label;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Item{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
