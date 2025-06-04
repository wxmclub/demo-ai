package com.demo.ai.mem.graphiti.client.graphiti.daimon;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-04
 */
@Data
public class SearchQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("group_ids")
    private List<String> groupIds;

    private String query;

    @JsonProperty("max_facts")
    private Integer maxFacts;

}
