/*
 * Copyright 2016 Lime - HighTech Solutions s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.getlime.rest.api.errorhandling;

import io.getlime.rest.api.model.entity.ErrorModel;
import io.getlime.rest.api.model.base.PowerAuthApiResponse;
import io.getlime.rest.api.security.exception.PowerAuthAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a default exception handler for the demo server.
 *
 * @author Petr Dvorak
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Handle PowerAuthAuthenticationException exceptions.
     * @param request Request that was processed while the exception was raised.
     * @param exception Exception instance.
     * @return Error response.
     */
    @ExceptionHandler(value = PowerAuthAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody PowerAuthApiResponse<ErrorModel> handleUnauthorizedException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        ErrorModel error = new ErrorModel("ERR_UNAUTHENTICATED", "Authentication failed");
        return new PowerAuthApiResponse<>("ERROR", error);
    }

    /**
     * Handle Exception exceptions.
     * @param request Request that was processed while the exception was raised.
     * @param exception Exception instance.
     * @return Error response.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody PowerAuthApiResponse<ErrorModel> handleException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        ErrorModel error = new ErrorModel("ERR_GENERIC", exception.getMessage());
        return new PowerAuthApiResponse<>("ERROR", error);
    }

}