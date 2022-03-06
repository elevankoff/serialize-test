package fortest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author elevankoff
 */
public class SerializationTestClass implements Serializable {
    private String words;
    private List<String> list;
    private Map<String, Integer> map;
    private Integer integer;
    private Float floatNumber;

    public SerializationTestClass() {
        this.words = "";
        this.list = Collections.emptyList();
        this.map = Collections.emptyMap();
        this.integer = 0;
        this.floatNumber = 0f;
    }

    public SerializationTestClass(
            String words,
            List<String> list,
            Map<String, Integer> map,
            Integer integer,
            Float floatNumber)
    {
        this.words = words;
        this.list = list;
        this.map = map;
        this.integer = integer;
        this.floatNumber = floatNumber;
    }

    public String getWords() {
        return words;
    }

    public List<String> getList() {
        return list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public Integer getInteger() {
        return integer;
    }

    public Float getFloatNumber() {
        return floatNumber;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setFloatNumber(Float floatNumber) {
        this.floatNumber = floatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializationTestClass that = (SerializationTestClass) o;
        return Objects.equals(words, that.words)
                && Objects.equals(list, that.list)
                && Objects.equals(map, that.map)
                && Objects.equals(integer, that.integer)
                && Objects.equals(floatNumber, that.floatNumber);
    }
}
