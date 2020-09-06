/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.streampipes.pe.processor.example;

import org.apache.streampipes.model.DataProcessorType;
import org.apache.streampipes.model.graph.DataProcessorDescription;
import org.apache.streampipes.model.graph.DataProcessorInvocation;
import org.apache.streampipes.sdk.builder.PrimitivePropertyBuilder;
import org.apache.streampipes.sdk.builder.ProcessingElementBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.apache.streampipes.sdk.helpers.EpRequirements;
import org.apache.streampipes.sdk.helpers.Labels;
import org.apache.streampipes.sdk.helpers.OutputStrategies;
import org.apache.streampipes.sdk.helpers.*;
import org.apache.streampipes.sdk.utils.Assets;
import org.apache.streampipes.sdk.utils.Datatypes;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventProcessor;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventProcessingDeclarer;

public class MyGreeterController extends StandaloneEventProcessingDeclarer<MyGreeterParameters> {

	private static final String GREETING_KEY = "greeting-key";

	/**
	 * Data processor model description containing input requirements, user configurations and the used output strategy
	 *
	 * @return DataProcessorDescription
	 */
	@Override
	public DataProcessorDescription declareModel() {
		return ProcessingElementBuilder.create("org.apache.streampipes.pe.processor.greeter")
				/**
				 * Assets (docs, icon) and locales can be found under resources and the <id> for this data processor
				 * (see above)
				 */
				.withAssets(Assets.DOCUMENTATION, Assets.ICON)
				.withLocales(Locales.EN)
				.category(DataProcessorType.ENRICH)
				/**
				 * input stream requirements
				 *
				 * defining input event stream requirements here, i.e. in order for this processor to work, it
				 * relies on certain properties such as a numeric value or a certain domain property (latitude)
				 * to be part of any of the event fields.
				 *
				 * here: we don't expect the input event stream to contain anything, thus we indicate it using
				 * .requiredProperty(EpRequirements.anyProperty())...
				 */
				.requiredStream(
						StreamRequirementsBuilder
								.create()
								.requiredProperty(EpRequirements.anyProperty())
								.build())
				/**
				 * user configuration (static property)
				 *
				 * this processor uses a textual user-defined input to be appended to the input event stream
				 */
				.requiredTextParameter(Labels.withId(GREETING_KEY))
				/**
				 * output strategy
				 *
				 * the SDK offers various output strategies (keep, fixed, custom, etc). Here, we simply append the given
				 * user-defined greeting as a new event field called "greeting" to the input event stream. As part of
				 * the model description, we describe how the output event schema looks like.
				 */
				.outputStrategy(OutputStrategies.append(
						PrimitivePropertyBuilder.create(
								Datatypes.String, "greeting")
								.build()))
				.build();
	}

	/**
	 * here we receive the payload containing needed information from the StreamPipes backend.
	 *
	 * @param graph contains information about transport layer (protocol, formats, topics) etc.
	 * @param extractor provides access to user configurations such as greeting message
	 * @return
	 */
	@Override
	public ConfiguredEventProcessor<MyGreeterParameters> onInvocation
				(DataProcessorInvocation graph, ProcessingElementParameterExtractor extractor) {

		String greeting = extractor.singleValueParameter(GREETING_KEY, String.class);
		MyGreeterParameters params = new MyGreeterParameters(graph, greeting);

		return new ConfiguredEventProcessor<>(params, MyGreeter::new);
	}
}
