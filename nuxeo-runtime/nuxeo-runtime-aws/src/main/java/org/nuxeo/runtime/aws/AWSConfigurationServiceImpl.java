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
package org.nuxeo.runtime.aws;

import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

public class AWSConfigurationServiceImpl extends DefaultComponent implements AWSConfigurationService {

    private AWSConfigurationDescriptor configuration;

    public boolean hasContributionCredentials() {
        return configuration != null;
    }

    @Override
    public AWSCredentialsProvider getAWSCredentialsProvider() {
        if (hasContributionCredentials()) {
            return new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(configuration.getAWSAccessKeyId(), configuration.getAWSSecretKey()));
        }
        return new DefaultAWSCredentialsProviderChain();
    }

    @Override
    public void registerContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
        if ("configuration".equals(extensionPoint)) {
            configuration = (AWSConfigurationDescriptor) contribution;
        }
    }

    @Override
    public void unregisterContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
        configuration = null;
    }

}
