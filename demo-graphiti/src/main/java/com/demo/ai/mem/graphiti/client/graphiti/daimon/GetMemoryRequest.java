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
public class GetMemoryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("max_facts")
    private Integer maxFacts;

    @JsonProperty("center_node_uuid")
    private String centerNodeUuid;

    private List<GraphitiMessage> messages;

}
