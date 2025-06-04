package com.demo.ai.mem.graphiti.client.graphiti.daimon;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-04
 */
@Data
public class GraphitiMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    private String uuid;

    private String name;

    @JsonProperty("role_type")
    private String roleType;

    private String role;

    private Date timestamp;

    @JsonProperty("source_description")
    private String sourceDescription;

}
