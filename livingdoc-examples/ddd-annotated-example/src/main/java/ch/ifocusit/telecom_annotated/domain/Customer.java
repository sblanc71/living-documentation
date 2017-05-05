/*
 * Living Documentation
 *
 * Copyright (C) 2017 Focus IT
 *
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
package ch.ifocusit.telecom_annotated.domain;

import ch.ifocusit.livingdoc.annotations.Glossary;
import ch.ifocusit.telecom_annotated.domain.common.AbstractDomain;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer of the telecom service
 */
@Glossary(id = 300)
public class Customer extends AbstractDomain {

    /**
     * Name of the customer.
     */
    @Glossary(id = 301)
    private String name;

    private Set<Contract> contracts = new HashSet<>();
}
