/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.cn.group.internal.service;

import org.apache.fineract.cn.group.api.v1.domain.GroupDefinition;
import org.apache.fineract.cn.group.ServiceConstants;
import org.apache.fineract.cn.group.internal.mapper.GroupDefinitionMapper;
import org.apache.fineract.cn.group.internal.repository.GroupDefinitionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupDefinitionService {

  private final Logger logger;
  private final GroupDefinitionRepository groupDefinitionRepository;

  @Autowired
  public GroupDefinitionService(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
                                final GroupDefinitionRepository groupDefinitionRepository) {
    super();
    this.logger = logger;
    this.groupDefinitionRepository = groupDefinitionRepository;
  }

  public Boolean groupDefinitionExists(final String identifier) {
    return this.groupDefinitionRepository.existsByIdentifier(identifier);
  }

  public Optional<GroupDefinition> findByIdentifier(final String identifier) {
    return this.groupDefinitionRepository.findByIdentifier(identifier).map(GroupDefinitionMapper::map);
  }

  public List<GroupDefinition> fetchAllGroupDefinitions() {
    return this.groupDefinitionRepository.findAll()
        .stream()
        .map(GroupDefinitionMapper::map)
        .collect(Collectors.toList());
  }
}
