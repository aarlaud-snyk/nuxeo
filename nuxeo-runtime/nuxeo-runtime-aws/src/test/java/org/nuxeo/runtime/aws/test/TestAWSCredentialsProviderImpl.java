/*
 * (C) Copyright 2018 Nuxeo (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Remi Cattiau
 */
package org.nuxeo.runtime.aws.test;

import static org.junit.Assert.assertNotEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.runtime.aws.AWSConfigurationService;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.RuntimeFeature;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

@RunWith(FeaturesRunner.class)
@Features(RuntimeFeature.class)
@Deploy("org.nuxeo.runtime.aws")
@Deploy("org.nuxeo.runtime.aws:OSGI-INF/test-aws-config.xml")
public class TestAWSCredentialsProviderImpl {

    @Inject
    protected AWSConfigurationService service;

    @Test
    public void testCredentials() {
        assertNotEquals(DefaultAWSCredentialsProviderChain.class, service.getAWSCredentialsProvider().getClass());
    }

}
