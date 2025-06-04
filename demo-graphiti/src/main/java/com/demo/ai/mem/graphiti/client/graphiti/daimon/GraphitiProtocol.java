package com.demo.ai.mem.graphiti.client.graphiti.daimon;

import java.io.Serializable;

import lombok.Data;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-04
 */
@Data
public class GraphitiProtocol<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;

    private String message;

}