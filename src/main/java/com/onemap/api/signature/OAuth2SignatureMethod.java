package com.onemap.api.signature;

/**
 * 签名方法
 * @author wang
 */
public interface OAuth2SignatureMethod {

  /**
   * The name of the OAuth signature method.
   *
   * @return The name of the OAuth signature method.
   */
  String getName();

  /**
   * 签名
   * Sign the signature base string.
   *
   * @param signatureBaseString The signature base string to sign.
   * @return The signature.
   */
  String sign(String signatureBaseString);

  /**
   * 检验签名
   * Verify the specified signature on the given signature base string.
   *
   * @param signatureBaseString The signature base string.
   * @param signature The signature.
   */
  void verify(String signatureBaseString, String signature) throws InvalidSignatureException;
  
}
