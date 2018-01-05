package com.company.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 25.12.2017.
 */
public class AnimationDTO {

    public String type;
    public List<String> params;

    public AnimationDTO() {
        type = "";
        params = new ArrayList<>();
    }

    public AnimationDTO(String type, List<String> params) {
        this.type = type;
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
