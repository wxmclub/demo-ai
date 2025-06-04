package com.demo.ai.mem.graphiti.client.graphiti.api;

import com.demo.ai.mem.graphiti.client.graphiti.daimon.AddMessagesRequest;
import com.demo.ai.mem.graphiti.client.graphiti.daimon.GetMemoryRequest;
import com.demo.ai.mem.graphiti.client.graphiti.daimon.GraphitiProtocol;
import com.demo.ai.mem.graphiti.client.graphiti.daimon.SearchQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * http://localhost:8000/docs
 *
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-04
 */
@HttpExchange
public interface IGraphitiServerApi {

    @PostExchange(value = "/messages")
    GraphitiProtocol addMessages(@RequestBody AddMessagesRequest request);

    @PostExchange(value = "/get-memory")
    GraphitiProtocol getMemory(@RequestBody GetMemoryRequest request);

    @PostExchange(value = "/search")
    GraphitiProtocol search(@RequestBody SearchQuery request);

}
