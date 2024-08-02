package com.vac.main.repositories.response;

import com.vac.main.constants.RepositoryStatus;

public record GenericRepositoryResponse(RepositoryStatus status, String message) {

}
