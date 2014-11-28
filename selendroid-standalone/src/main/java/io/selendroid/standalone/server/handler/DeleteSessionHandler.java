/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.standalone.server.handler;

import org.json.JSONException;

import io.selendroid.server.common.Response;
import io.selendroid.server.common.SelendroidResponse;
import io.selendroid.server.common.http.HttpRequest;
import io.selendroid.standalone.exceptions.AndroidDeviceException;
import io.selendroid.standalone.server.BaseSelendroidServerHandler;
import io.selendroid.standalone.server.model.SelendroidStandaloneDriver;

import java.util.logging.Logger;

public class DeleteSessionHandler extends BaseSelendroidServerHandler {
  private static final Logger log = Logger.getLogger(DeleteSessionHandler.class.getName());

  public DeleteSessionHandler(String mappedUri) {
    super(mappedUri);
  }

  @Override
  public Response handle(HttpRequest request) throws JSONException {
    log.info("delete session command");
    SelendroidStandaloneDriver driver = getSelendroidDriver(request);
    String sessionId = getSessionId(request);
    try {
      driver.stopSession(sessionId);
    } catch (AndroidDeviceException e) {
      log.severe("Error occured while stopping the emulator.");
    }

    return new SelendroidResponse(sessionId, "");
  }
}