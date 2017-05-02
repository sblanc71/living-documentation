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
package ch.ifocusit.telecom.domain;

import ch.ifocusit.livingdoc.annotations.Glossary;
import ch.ifocusit.telecom.domain.access.Access;
import ch.ifocusit.telecom.domain.access.CallAccess;
import ch.ifocusit.telecom.domain.access.SmsAccess;
import ch.ifocusit.telecom.domain.common.AbstractDomain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.YearMonth;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Facture *mensuel*.
 * [NOTE]
 * Généré pas le système à la date anniversaire du contrat + 1 jour.
 *
 * @since 2017-03-01
 */
@Glossary
public class Bill extends AbstractDomain {

    private YearMonth month;

    private Contract contract;

    private Set<Access> accesses = new HashSet<>();

    public Contract getContract() {
        return contract;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public Set<Access> getAccesses() {
        return Collections.unmodifiableSet(accesses);
    }

    public boolean addAccess(final Access access) {
        return this.accesses.add(access);
    }

    public boolean removeAccess(final Access access) {
        return this.accesses.remove(access);
    }

    public BigDecimal getBillAmount() {
        return accesses.stream().map(access -> access.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // tag::calculateCallsDuration[]
    public Duration getTotalCallsDuration() {
        return accesses.stream().filter(CallAccess.class::isInstance).map(CallAccess.class::cast)
                .map(callAccess -> callAccess.getDuration()).reduce(Duration.ZERO, Duration::plus);
    }
    // end::calculateCallsDuration[]

    public long getNbCalls() {
        return accesses.stream().filter(CallAccess.class::isInstance).count();
    }

    public long getNbSms() {
        return accesses.stream().filter(SmsAccess.class::isInstance).count();
    }
}
