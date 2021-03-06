package hbi.core.HapProject.dto;

/**
 * Created by jason on 2016/12/18.
 */
public class ColumnsInfo {
    private String title;
    private String name;
    private String type = "string";
    private int width = 80;
    private String align;

    public ColumnsInfo() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getAlign() {
        return this.align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}
