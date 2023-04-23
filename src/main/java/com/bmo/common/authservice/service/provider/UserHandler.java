package com.bmo.common.authservice.service.provider;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.oauth2.Provider;
import com.fasterxml.jackson.core.type.TypeReference;

public interface UserHandler<T> {

  TypeReference<T> getProvidedUserType();

  Provider getProviderType();

  boolean isUserExists(T providedUser);

  T createUser(T providedUser);

  AuthToken generateAuthToken(T providedUser);
}
