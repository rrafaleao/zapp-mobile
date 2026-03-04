package com.zappshop.app.data.repository;

import com.zappshop.app.data.local.SessionManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<SessionManager> sessionProvider;

  public AuthRepository_Factory(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(sessionProvider.get());
  }

  public static AuthRepository_Factory create(Provider<SessionManager> sessionProvider) {
    return new AuthRepository_Factory(sessionProvider);
  }

  public static AuthRepository newInstance(SessionManager session) {
    return new AuthRepository(session);
  }
}
