package com.bmo.common.authservice.service.provider;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.oauth2.ProviderType;
import com.fasterxml.jackson.core.type.TypeReference;

public interface UserHandler<T> {

  TypeReference<T> getProvidedUserType();

  ProviderType getProviderType();

  boolean isUserExists(T providedUser);

  T createUser(T providedUser);

  AuthToken generateAuthToken(T providedUser);
}
