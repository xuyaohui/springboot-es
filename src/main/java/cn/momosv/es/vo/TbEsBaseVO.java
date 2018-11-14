package cn.momosv.es.vo;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/9/27 14:41
 **/
public class TbEsBaseVO {
    private String indices;
    private String type;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndices() {
        return indices;
    }

    public void setIndices(String indices) {
        this.indices = indices;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
