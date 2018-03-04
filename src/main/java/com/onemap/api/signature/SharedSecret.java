package com.onemap.api.signature;

/**
 * 共享的签名密钥
 *
 * @author wang
 */
public class SharedSecret {

  private final String secret;

  public SharedSecret(String secret) {
    this.secret = secret;
  }

  /**
   * The consumer secret.
   *
   * @return The consumer secret.
   */
  public String getConsumerSecret() {
    return secret;
  }

}
