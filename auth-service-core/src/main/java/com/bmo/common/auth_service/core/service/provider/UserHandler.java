package com.bmo.common.auth_service.core.service.provider;

import com.bmo.common.auth_service.core.model.oauth2.Provider;
import com.bmo.common.auth_service.model.AuthToken;
import com.fasterxml.jackson.core.type.TypeReference;

public interface UserHandler<T> {

  TypeReference<T> getProvidedUserType();

  Provider getProviderType();

  boolean isUserExists(T providedUser);

  T createUser(T providedUser);

  AuthToken generateAuthToken(T providedUser);
}
