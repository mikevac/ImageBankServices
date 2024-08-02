package com.vac.main.services.response;

import com.vac.main.constants.ServiceStatus;

public record GenericServiceResponse(

        ServiceStatus status,
        String message

) {
}
