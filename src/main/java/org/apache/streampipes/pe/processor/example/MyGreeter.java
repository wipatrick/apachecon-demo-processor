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

import org.apache.streampipes.model.runtime.Event;
import org.apache.streampipes.wrapper.context.EventProcessorRuntimeContext;
import org.apache.streampipes.wrapper.routing.SpOutputCollector;
import org.apache.streampipes.wrapper.runtime.EventProcessor;

/**
 * Actual data processor application logic
 */
public class MyGreeter implements EventProcessor<MyGreeterParameters> {

  private String greeting;

  /**
   * onInvocation gets called upon pipeline start
   *
   * @param parameters
   * @param out
   * @param ctx
   */
  @Override
  public void onInvocation(MyGreeterParameters parameters,
                           SpOutputCollector out, EventProcessorRuntimeContext ctx) {
    this.greeting = parameters.getGreeting();
  }

  /**
   * onEvent is called on every event
   *
   * @param event event model containing actual event, used to retrieve certain values or add new fields
   * @param out output collector used to forward event
   */
  @Override
  public void onEvent(Event event, SpOutputCollector out) {

    /**
     * add new event field with greeting
     *
     * event.addField(String runtimeName, String value)
     *
     * runtimeName must match the one specified in the
     * DataProcessorDescription inside MyGreeterController
     *
     * ...
     * .outputStrategy(OutputStrategies.append(
     * 		PrimitivePropertyBuilder.create(
     * 				Datatypes.String, "greeting")
     * 		.build()))
     * 	...
     */
    event.addField("greeting", greeting);
    out.collect(event);
  }


  /**
   * onDetach is called upon pipeline stop
   */
  @Override
  public void onDetach() {

  }
}
